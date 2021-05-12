package br.com.zupacademy.ggwadera.proposta.cartao;

import br.com.zupacademy.ggwadera.proposta.util.error.ApiErrorException;
import org.springframework.http.HttpStatus;

public class CartaoUtils {

  public static Cartao findByIdOrThrow(CartaoRepository repository, String id) {
    return repository
        .findById(id)
        .orElseThrow(
            () ->
                new ApiErrorException(
                    HttpStatus.NOT_FOUND, "Não foi encontrando um cartão com o id especificado"));
  }
}
