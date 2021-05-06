package br.com.zupacademy.ggwadera.proposta.proposta.avaliacao;

import br.com.zupacademy.ggwadera.proposta.proposta.Proposta;

public class AvaliacaoRequest {
  private final String documento;
  private final String nome;
  private final String idProposta;

  public AvaliacaoRequest(Proposta proposta) {
    this.documento = proposta.getDocumento();
    this.nome = proposta.getNome();
    this.idProposta = proposta.getId().toString();
  }

  public String getDocumento() {
    return documento;
  }

  public String getNome() {
    return nome;
  }

  public String getIdProposta() {
    return idProposta;
  }
}
