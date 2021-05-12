package br.com.zupacademy.ggwadera.proposta.carteira;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoClient;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoRepository;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoUtils;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CarteiraController {

  private final Logger logger = LoggerFactory.getLogger(CarteiraController.class);
  private final CarteiraRepository carteiraRepository;
  private final CartaoRepository cartaoRepository;
  private final CartaoClient cartaoClient;

  @Autowired
  public CarteiraController(
      CarteiraRepository carteiraRepository,
      CartaoRepository cartaoRepository,
      CartaoClient cartaoClient) {
    this.carteiraRepository = carteiraRepository;
    this.cartaoRepository = cartaoRepository;
    this.cartaoClient = cartaoClient;
  }

  @PostMapping("/cartoes/{id}/carteiras")
  public ResponseEntity<Void> adicionaCarteira(
      @PathVariable String id,
      @RequestBody @Valid CarteiraRequest request,
      UriComponentsBuilder builder) {
    Cartao cartao = CartaoUtils.findByIdOrThrow(cartaoRepository, id);
    if (carteiraRepository.existsByTipoAndCartao(request.getCarteira(), cartao)) {
      throw new ApiErrorException(
          HttpStatus.UNPROCESSABLE_ENTITY,
          "Já existe uma carteira desse tipo associada ao cartão solicitado");
    }
    try {
      ResultadoCarteira resultado = cartaoClient.adicionaCarteira(id, request);
      Carteira carteira = carteiraRepository.save(request.toModel(resultado.getId(), cartao));
      logger.info("Nova carteira id={} tipo={}", carteira.getId(), carteira.getTipo());
      URI uri = builder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
      return ResponseEntity.created(uri).build();
    } catch (FeignException e) {
      logger.error("status={} erro={}", e.status(), e.contentUTF8());
      throw new ApiErrorException(HttpStatus.BAD_GATEWAY, "Erro ao adicionar carteira");
    }
  }
}
