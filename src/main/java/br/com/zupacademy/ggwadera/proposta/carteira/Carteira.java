package br.com.zupacademy.ggwadera.proposta.carteira;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;

import javax.persistence.*;
import java.util.UUID;

@Table(
    indexes = {
      @Index(name = "IDX_CARTEIRA_tipo_UNQ", columnList = "tipo, cartao_id", unique = true)
    })
@Entity
public class Carteira {

  @Id private UUID id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoCarteira tipo;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Cartao cartao;

  @Deprecated
  public Carteira() {}

  public Carteira(UUID id, TipoCarteira tipo, Cartao cartao) {
    this.id = id;
    this.tipo = tipo;
    this.cartao = cartao;
  }

  public UUID getId() {
    return id;
  }

  public TipoCarteira getTipo() {
    return tipo;
  }

  public Cartao getCartao() {
    return cartao;
  }
}
