package br.com.zupacademy.ggwadera.proposta.proposta;

import br.com.zupacademy.ggwadera.proposta.util.validation.CpfOuCnpj;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

  @NotBlank @CpfOuCnpj private final String documento;

  @NotBlank @Email private final String email;

  @NotBlank private final String nome;

  @NotBlank private final String endereco;

  @NotNull @Positive private final BigDecimal salario;

  public NovaPropostaRequest(
      String documento, String email, String nome, String endereco, BigDecimal salario) {
    this.documento = documento;
    this.email = email;
    this.nome = nome;
    this.endereco = endereco;
    this.salario = salario;
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

  public Proposta toModel() {
    return new Proposta(documento, email, nome, endereco, salario);
  }
}
