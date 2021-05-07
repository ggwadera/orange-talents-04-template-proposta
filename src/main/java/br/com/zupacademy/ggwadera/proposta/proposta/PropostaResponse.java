package br.com.zupacademy.ggwadera.proposta.proposta;

import br.com.zupacademy.ggwadera.proposta.cartao.CartaoResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class PropostaResponse {

  private final UUID id;
  private final CartaoResponse cartao;
  private final String documento;
  private final String email;
  private final String nome;
  private final String endereco;
  private final BigDecimal salario;
  private final EstadoProposta estado;

  public PropostaResponse(Proposta proposta) {
    this.id = proposta.getId();
    this.cartao = new CartaoResponse(proposta.getCartao());
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

  public CartaoResponse getCartao() {
    return cartao;
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
