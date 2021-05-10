package br.com.zupacademy.ggwadera.proposta.cartao;

import br.com.zupacademy.ggwadera.proposta.proposta.Proposta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cartao {

  @Id private String id;

  @Column(nullable = false)
  private LocalDateTime emitidoEm;

  @Column(nullable = false)
  private String titular;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusCartao status;

  @OneToOne(mappedBy = "cartao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Proposta proposta;

  @Deprecated
  public Cartao() {}

  public Cartao(String id, LocalDateTime emitidoEm, String titular, Proposta proposta) {
    this.id = id;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.proposta = proposta;
    this.status = StatusCartao.ATIVO;
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

  public Boolean bloquear() {
    if (status == StatusCartao.BLOQUEADO) return false;
    status = StatusCartao.BLOQUEADO;
    return true;
  }
}
