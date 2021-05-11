package br.com.zupacademy.ggwadera.proposta.cartao.bloqueio;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.util.RequestInfo;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Bloqueio {
  @Id @GeneratedValue private UUID id;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Embedded private RequestInfo requestInfo;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Cartao cartao;

  @Deprecated
  public Bloqueio() {}

  public Bloqueio(RequestInfo requestInfo, Cartao cartao) {
    this.requestInfo = requestInfo;
    this.cartao = cartao;
  }

  public UUID getId() {
    return id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public Cartao getCartao() {
    return cartao;
  }

  public RequestInfo getRequestInfo() {
    return requestInfo;
  }
}
