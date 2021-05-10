package br.com.zupacademy.ggwadera.proposta.cartao.bloqueio;

public class RespostaBloqueio {

  private final ResultadoBloqueio resultado;

  public RespostaBloqueio(ResultadoBloqueio resultado) {
    this.resultado = resultado;
  }

  public ResultadoBloqueio getResultado() {
    return resultado;
  }
}
