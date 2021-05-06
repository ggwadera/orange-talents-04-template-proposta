package br.com.zupacademy.ggwadera.proposta.proposta.avaliacao;

public class AvaliacaoResponse {
  private final String documento;
  private final String nome;
  private final ResultadoSolicitacao resultadoSolicitacao;
  private final String idProposta;

  public AvaliacaoResponse(
      String documento, String nome, ResultadoSolicitacao resultadoSolicitacao, String idProposta) {
    this.documento = documento;
    this.nome = nome;
    this.resultadoSolicitacao = resultadoSolicitacao;
    this.idProposta = idProposta;
  }

  public String getDocumento() {
    return documento;
  }

  public String getNome() {
    return nome;
  }

  public ResultadoSolicitacao getResultadoSolicitacao() {
    return resultadoSolicitacao;
  }

  public String getIdProposta() {
    return idProposta;
  }

  @Override
  public String toString() {
    return "AvaliacaoResponse{"
        + "documento='"
        + documento
        + '\''
        + ", nome='"
        + nome
        + '\''
        + ", resultadoSolicitacao='"
        + resultadoSolicitacao
        + '\''
        + ", idProposta='"
        + idProposta
        + '\''
        + '}';
  }
}
