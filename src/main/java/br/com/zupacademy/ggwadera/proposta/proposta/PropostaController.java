package br.com.zupacademy.ggwadera.proposta.proposta;

import br.com.zupacademy.ggwadera.proposta.proposta.avaliacao.AvaliacaoClient;
import br.com.zupacademy.ggwadera.proposta.proposta.avaliacao.AvaliacaoRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

  private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

  private final PropostaRepository propostaRepository;
  private final AvaliacaoClient avaliacaoClient;

  @Autowired
  public PropostaController(
      PropostaRepository propostaRepository, AvaliacaoClient avaliacaoClient) {
    this.propostaRepository = propostaRepository;
    this.avaliacaoClient = avaliacaoClient;
  }

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(new DocumentoJaSolicitadoValidator(propostaRepository::existsByDocumento));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<Void> novaProposta(@RequestBody @Valid NovaPropostaRequest request) {
    final Proposta proposta = propostaRepository.save(request.toModel());
    try {
      avaliacaoClient.avaliacaoFinanceira(new AvaliacaoRequest(proposta));
      proposta.setEstado(EstadoProposta.ELEGIVEL);
    } catch (FeignException e) {
      // se a proposta não for elegível, serviço retorna status 422 estourando exceção
      if (e.status() == 422) proposta.setEstado(EstadoProposta.NAO_ELEGIVEL);
      else throw e;
    }
    propostaRepository.save(proposta);
    logger.info(proposta.toString());
    final URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(proposta.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }
}
