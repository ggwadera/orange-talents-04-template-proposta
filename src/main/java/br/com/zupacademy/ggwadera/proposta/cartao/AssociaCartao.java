package br.com.zupacademy.ggwadera.proposta.cartao;

import br.com.zupacademy.ggwadera.proposta.proposta.EstadoProposta;
import br.com.zupacademy.ggwadera.proposta.proposta.Proposta;
import br.com.zupacademy.ggwadera.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociaCartao {

  private final Logger logger = LoggerFactory.getLogger(AssociaCartao.class);
  private final PropostaRepository propostaRepository;
  private final CartaoClient cartaoClient;

  @Autowired
  public AssociaCartao(PropostaRepository propostaRepository, CartaoClient cartaoClient) {
    this.propostaRepository = propostaRepository;
    this.cartaoClient = cartaoClient;
  }

  @Scheduled(fixedDelay = 5000)
  @Transactional
  public void associarCartao() {
    logger.info("Verificando cartões para propostas");
    List<Proposta> propostasParaSalvar =
        propostaRepository.findByIdCartaoIsNullAndEstadoIs(EstadoProposta.ELEGIVEL).parallelStream()
            .peek(
                proposta -> {
                  try {
                    CartaoResponse cartao = cartaoClient.getCartao(proposta.getId());
                    proposta.setIdCartao(cartao.getId());
                    logger.info("Proposta id={} atualizada com cartão", proposta.getId());
                  } catch (FeignException e) {
                    logger.info("Proposta id={} ainda não tem cartão", proposta.getId());
                  }
                })
            .collect(
                Collectors.filtering(
                    proposta -> proposta.getIdCartao() != null, Collectors.toList()));
    propostaRepository.saveAll(propostasParaSalvar);
  }
}
