package br.com.zupacademy.ggwadera.proposta.cartao;

import br.com.zupacademy.ggwadera.proposta.proposta.Proposta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cartao {

  @Id private String id;

  private LocalDateTime emitidoEm;

  private String titular;

  @OneToOne(mappedBy = "cartao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Proposta proposta;

  @Deprecated
  public Cartao() {}

  public Cartao(String id, LocalDateTime emitidoEm, String titular, Proposta proposta) {
    this.id = id;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.proposta = proposta;
  }

  public String getId() {
    return id;
  }

  public LocalDateTime getEmitidoEm() {
    return emitidoEm;
  }

  public String getTitular() {
    return titular;
  }

  public Proposta getProposta() {
    return proposta;
  }
}
