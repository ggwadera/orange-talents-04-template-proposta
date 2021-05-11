package br.com.zupacademy.ggwadera.proposta.avisoviagem;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.util.RequestInfo;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AvisoViagem {

  @Id @GeneratedValue private UUID id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Cartao cartao;

  @Column(nullable = false)
  private String destino;

  @Column(nullable = false)
  private LocalDate validoAte;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Embedded private RequestInfo requestInfo;

  @Deprecated
  public AvisoViagem() {}

  public AvisoViagem(Cartao cartao, String destino, LocalDate validoAte, RequestInfo requestInfo) {
    this.cartao = cartao;
    this.destino = destino;
    this.validoAte = validoAte;
    this.requestInfo = requestInfo;
  }

  public UUID getId() {
    return id;
  }

  public Cartao getCartao() {
    return cartao;
  }

  public String getDestino() {
    return destino;
  }

  public LocalDate getValidoAte() {
    return validoAte;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public RequestInfo getRequestInfo() {
    return requestInfo;
  }
}
