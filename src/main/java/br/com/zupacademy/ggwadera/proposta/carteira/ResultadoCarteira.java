package br.com.zupacademy.ggwadera.proposta.carteira;

import java.util.UUID;

public class ResultadoCarteira {
  private final String resultado;
  private final UUID id;

  public ResultadoCarteira(String resultado, UUID id) {
    this.resultado = resultado;
    this.id = id;
  }

  public String getResultado() {
    return resultado;
  }

  public UUID getId() {
    return id;
  }
}
