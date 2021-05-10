package br.com.zupacademy.ggwadera.proposta.cartao.bloqueio;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
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

  @Column(nullable = false)
  private String ip;

  @Column(nullable = false)
  private String userAgent;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Cartao cartao;

  @Deprecated
  public Bloqueio() {}

  public Bloqueio(String ip, String userAgent, Cartao cartao) {
    this.ip = ip;
    this.userAgent = userAgent;
    this.cartao = cartao;
  }

  public UUID getId() {
    return id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public String getIp() {
    return ip;
  }

  public Cartao getCartao() {
    return cartao;
  }
}
