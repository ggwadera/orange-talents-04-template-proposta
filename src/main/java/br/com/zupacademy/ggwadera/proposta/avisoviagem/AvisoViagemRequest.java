package br.com.zupacademy.ggwadera.proposta.avisoviagem;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.util.RequestInfo;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

  @NotBlank private final String destino;

  @NotNull @Future private final LocalDate dataTermino;

  public AvisoViagemRequest(String destino, LocalDate dataTermino) {
    this.destino = destino;
    this.dataTermino = dataTermino;
  }

  public String getDestino() {
    return destino;
  }

  public LocalDate getDataTermino() {
    return dataTermino;
  }

  public AvisoViagem toModel(Cartao cartao, RequestInfo requestInfo) {
    return new AvisoViagem(cartao, destino, dataTermino, requestInfo);
  }
}
