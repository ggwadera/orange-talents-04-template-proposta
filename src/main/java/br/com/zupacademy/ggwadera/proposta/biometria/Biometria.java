package br.com.zupacademy.ggwadera.proposta.biometria;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Biometria {
  @Id @GeneratedValue private UUID id;

  @Lob
  @Column(nullable = false)
  private byte[] fingerprint;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Cartao cartao;

  @Deprecated
  public Biometria() {}

  public Biometria(byte[] fingerprint, Cartao cartao) {
    this.fingerprint = fingerprint;
    this.cartao = cartao;
  }

  public UUID getId() {
    return id;
  }

  public byte[] getFingerprint() {
    return fingerprint;
  }

  public Cartao getCartao() {
    return cartao;
  }
}
