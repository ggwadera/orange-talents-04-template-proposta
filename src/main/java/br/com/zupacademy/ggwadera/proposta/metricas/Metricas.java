package br.com.zupacademy.ggwadera.proposta.metricas;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class Metricas {

  private final MeterRegistry meterRegistry;
  private final Counter counterPropostaCriada;

  public Metricas(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    this.counterPropostaCriada = this.meterRegistry.counter("proposta_criada");
  }

  public void incrementaCounterPropostaCriada() {
    this.counterPropostaCriada.increment();
  }
}
