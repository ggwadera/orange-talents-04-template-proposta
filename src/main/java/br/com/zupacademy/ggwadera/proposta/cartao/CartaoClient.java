package br.com.zupacademy.ggwadera.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cartoes", url = "${servicos.contas}")
public interface CartaoClient {

  @GetMapping("/cartoes?idProposta={idProposta}")
  CartaoResponse getCartao(@PathVariable("idProposta") UUID idProposta);
}
