package br.com.zupacademy.ggwadera.proposta.avisoviagem;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoClient;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoRepository;
import br.com.zupacademy.ggwadera.proposta.util.RequestInfo;
import br.com.zupacademy.ggwadera.proposta.util.error.ApiErrorException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AvisoViagemController {

  private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

  private final AvisoViagemRepository avisoViagemRepository;
  private final CartaoRepository cartaoRepository;
  private final CartaoClient cartaoClient;

  @Autowired
  public AvisoViagemController(
      AvisoViagemRepository avisoViagemRepository,
      CartaoRepository cartaoRepository,
      CartaoClient cartaoClient) {
    this.avisoViagemRepository = avisoViagemRepository;
    this.cartaoRepository = cartaoRepository;
    this.cartaoClient = cartaoClient;
  }

  @PostMapping("/cartoes/{id}/viagem")
  public ResponseEntity<Void> novoAvisoViagem(
      @PathVariable String id,
      @RequestBody @Valid AvisoViagemRequest dto,
      HttpServletRequest request) {
    Cartao cartao =
        cartaoRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ApiErrorException(
                        HttpStatus.NOT_FOUND, "Não foi encontrado um cartão com este id"));
    try {
      cartaoClient.avisoViagem(cartao.getId(), dto);
    } catch (FeignException e) {
      throw new ApiErrorException(e.status(), "falha ao processar aviso viagem");
    }
    logger.info(
        "Novo aviso viagem, cartão={}, destino={}, dataTermino={}",
        cartao.getId(),
        dto.getDestino(),
        dto.getValidoAte());
    avisoViagemRepository.save(dto.toModel(cartao, new RequestInfo(request)));
    return ResponseEntity.ok().build();
  }
}
