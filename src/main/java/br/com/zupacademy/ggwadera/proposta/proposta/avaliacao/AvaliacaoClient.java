package br.com.zupacademy.ggwadera.proposta.proposta.avaliacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "avaliacaoFinanceira", url = "${servicos.analise}")
public interface AvaliacaoClient {

  @PostMapping("/solicitacao")
  AvaliacaoResponse avaliacaoFinanceira(AvaliacaoRequest request);
}
