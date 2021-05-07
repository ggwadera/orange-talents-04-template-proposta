package br.com.zupacademy.ggwadera.proposta.proposta;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(
    name = "Proposta",
    indexes = {
      @Index(name = "idx_proposta_documento_unq", columnList = "documento", unique = true)
    })
@Entity
public class Proposta {

  @Id @GeneratedValue private UUID id;

  @Column(nullable = false, unique = true)
  private String documento;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String endereco;

  @Column(nullable = false)
  private BigDecimal salario;

  @Enumerated(EnumType.STRING)
  private EstadoProposta estado;

  @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  private Cartao cartao;

  @Deprecated
  public Proposta() {}

  public Proposta(
      String documento, String email, String nome, String endereco, BigDecimal salario) {
    this.documento = documento;
    this.email = email;
    this.nome = nome;
    this.endereco = endereco;
    this.salario = salario;
  }

  public UUID getId() {
    return id;
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

  public Cartao getCartao() {
    return cartao;
  }

  public void setCartao(Cartao cartao) {
    this.cartao = cartao;
  }

  public EstadoProposta getEstado() {
    return estado;
  }

  public void setEstado(EstadoProposta estado) {
    this.estado = estado;
  }

  @Override
  public String toString() {
    return "Proposta("
        + "id = "
        + id
        + ", "
        + "documento = "
        + documento
        + ", "
        + "email = "
        + email
        + ", "
        + "nome = "
        + nome
        + ", "
        + "endereco = "
        + endereco
        + ", "
        + "salario = "
        + salario
        + ", "
        + "estado = "
        + estado
        + ")";
  }
}
