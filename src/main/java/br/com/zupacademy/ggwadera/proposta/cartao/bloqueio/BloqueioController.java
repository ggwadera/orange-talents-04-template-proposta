package br.com.zupacademy.ggwadera.proposta.cartao.bloqueio;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoClient;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoRepository;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoUtils;
import br.com.zupacademy.ggwadera.proposta.util.RequestInfo;
import br.com.zupacademy.ggwadera.proposta.util.error.ApiErrorException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
public class BloqueioController {

  private final CartaoRepository cartaoRepository;
  private final BloqueioRepository bloqueioRepository;
  private final CartaoClient cartaoClient;

  @Autowired
  public BloqueioController(
      CartaoRepository cartaoRepository,
      BloqueioRepository bloqueioRepository,
      CartaoClient cartaoClient) {
    this.cartaoRepository = cartaoRepository;
    this.bloqueioRepository = bloqueioRepository;
    this.cartaoClient = cartaoClient;
  }

  @PostMapping("/cartoes/{id}/bloqueio")
  @Transactional
  public ResponseEntity<Void> bloqueiaCartao(@PathVariable String id, HttpServletRequest request) {
    Cartao cartao = CartaoUtils.findByIdOrThrow(cartaoRepository, id);
    boolean cartaoFoiBloqueado;
    try {
      RespostaBloqueio response =
          cartaoClient.bloqueioCartao(cartao.getId(), new SolicitacaoBloqueio());
      cartaoFoiBloqueado = response.getResultado() == ResultadoBloqueio.BLOQUEADO;
    } catch (FeignException e) {
      cartaoFoiBloqueado = false;
    } catch (Exception e) {
      throw new ApiErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar requisição");
    }
    if (cartaoFoiBloqueado && !cartao.bloquear()) {
      throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado");
    }
    bloqueioRepository.save(new Bloqueio(new RequestInfo(request), cartao));
    return ResponseEntity.ok().build();
  }
}
