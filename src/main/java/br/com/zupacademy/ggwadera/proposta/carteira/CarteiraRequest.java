package br.com.zupacademy.ggwadera.proposta.carteira;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CarteiraRequest {

  @NotBlank @Email private final String email;

  @NotNull private final TipoCarteira carteira;

  public CarteiraRequest(String email, TipoCarteira carteira) {
    this.email = email;
    this.carteira = carteira;
  }

  public String getEmail() {
    return email;
  }

  public TipoCarteira getCarteira() {
    return carteira;
  }

  public Carteira toModel(UUID id, Cartao cartao) {
    return new Carteira(id, carteira, cartao);
  }
}
