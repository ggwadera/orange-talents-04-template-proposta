package br.com.zupacademy.ggwadera.proposta.util.error;

import java.util.Collection;

public class ApiError {

  private Collection<String> mensagens;

  public ApiError(Collection<String> mensagens) {
    this.mensagens = mensagens;
  }

  public Collection<String> getMensagens() {
    return mensagens;
  }
}
