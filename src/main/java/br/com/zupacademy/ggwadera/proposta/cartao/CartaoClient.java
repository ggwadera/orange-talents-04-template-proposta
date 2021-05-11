package br.com.zupacademy.ggwadera.proposta.cartao;

import br.com.zupacademy.ggwadera.proposta.cartao.bloqueio.RespostaBloqueio;
import br.com.zupacademy.ggwadera.proposta.cartao.bloqueio.SolicitacaoBloqueio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "cartoes", url = "${servicos.contas}")
public interface CartaoClient {

  @GetMapping("/cartoes")
  CartaoResponse getCartao(@RequestParam("idProposta") UUID idProposta);

  @PostMapping("/cartoes/{id}/bloqueios")
  RespostaBloqueio bloqueioCartao(
      @PathVariable("id") String id, @RequestBody SolicitacaoBloqueio body);
}
