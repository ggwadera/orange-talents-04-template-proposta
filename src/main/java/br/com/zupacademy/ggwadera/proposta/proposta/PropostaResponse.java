package br.com.zupacademy.ggwadera.proposta.proposta;

import java.math.BigDecimal;
import java.util.UUID;

public class PropostaResponse {

  private final UUID id;
  private final String idCartao;
  private final String documento;
  private final String email;
  private final String nome;
  private final String endereco;
  private final BigDecimal salario;
  private final EstadoProposta estado;

  public PropostaResponse(Proposta proposta) {
    this.id = proposta.getId();
    this.idCartao = proposta.getIdCartao();
    this.documento = proposta.getDocumento();
    this.email = proposta.getEmail();
    this.nome = proposta.getNome();
    this.endereco = proposta.getEndereco();
    this.salario = proposta.getSalario();
    this.estado = proposta.getEstado();
  }

  public UUID getId() {
    return id;
  }

  public String getIdCartao() {
    return idCartao;
  }

  public String getDocumento() {
    return documento;
  }

  public String getEmail() {
    return email;
  }

  public String getNome() {
    return nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public EstadoProposta getEstado() {
    return estado;
  }
}
