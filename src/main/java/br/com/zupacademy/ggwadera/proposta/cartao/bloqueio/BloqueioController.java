package br.com.zupacademy.ggwadera.proposta.cartao.bloqueio;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoRepository;
import br.com.zupacademy.ggwadera.proposta.util.error.ApiErrorException;
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

  @Autowired
  public BloqueioController(
      CartaoRepository cartaoRepository, BloqueioRepository bloqueioRepository) {
    this.cartaoRepository = cartaoRepository;
    this.bloqueioRepository = bloqueioRepository;
  }

  @PostMapping("/cartoes/{id}/bloqueio")
  @Transactional
  public ResponseEntity<Void> bloqueiaCartao(@PathVariable String id, HttpServletRequest request) {
    Cartao cartao =
        cartaoRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ApiErrorException(
                        HttpStatus.NOT_FOUND, "Não foi encontrado um cartão com este id"));
    if (!cartao.bloquear()) {
      throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado");
    }
    bloqueioRepository.save(
        new Bloqueio(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao));
    return ResponseEntity.ok().build();
  }
}
