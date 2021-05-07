package br.com.zupacademy.ggwadera.proposta.cartao;

import br.com.zupacademy.ggwadera.proposta.proposta.Proposta;

import java.time.LocalDateTime;
import java.util.UUID;

public class CartaoResponse {
  private final String id;
  private final LocalDateTime emitidoEm;
  private final String titular;
  private final UUID idProposta;

  public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, UUID idProposta) {
    this.id = id;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.idProposta = idProposta;
  }

  public CartaoResponse(Cartao cartao) {
    this.id = cartao.getId();
    this.emitidoEm = cartao.getEmitidoEm();
    this.titular = cartao.getTitular();
    this.idProposta = cartao.getProposta().getId();
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

  public UUID getIdProposta() {
    return idProposta;
  }

  public Cartao toModel(Proposta proposta) {
    return new Cartao(id, emitidoEm, titular, proposta);
  }
}
