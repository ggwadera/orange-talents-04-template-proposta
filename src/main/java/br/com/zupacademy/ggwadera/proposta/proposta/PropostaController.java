package br.com.zupacademy.ggwadera.proposta.proposta;

import br.com.zupacademy.ggwadera.proposta.metricas.Metricas;
import br.com.zupacademy.ggwadera.proposta.proposta.avaliacao.AvaliacaoClient;
import br.com.zupacademy.ggwadera.proposta.proposta.avaliacao.AvaliacaoRequest;
import feign.FeignException;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

  private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

  private final PropostaRepository propostaRepository;
  private final AvaliacaoClient avaliacaoClient;
  private final Metricas metricas;
  private final Tracer tracer;

  @Autowired
  public PropostaController(
      PropostaRepository propostaRepository,
      AvaliacaoClient avaliacaoClient,
      Metricas metricas,
      Tracer tracer) {
    this.propostaRepository = propostaRepository;
    this.avaliacaoClient = avaliacaoClient;
    this.metricas = metricas;
    this.tracer = tracer;
  }

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(new DocumentoJaSolicitadoValidator(propostaRepository::existsByDocumento));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PropostaResponse> buscaProposta(@PathVariable UUID id) {
    Proposta proposta =
        propostaRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma proposta com id=" + id));
    return ResponseEntity.ok(new PropostaResponse(proposta));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<Void> novaProposta(@RequestBody @Valid NovaPropostaRequest request) {
    tracer.activeSpan().setTag("user.email", request.getEmail());
    final Proposta proposta = propostaRepository.save(request.toModel());
    try {
      avaliacaoClient.avaliacaoFinanceira(new AvaliacaoRequest(proposta));
      proposta.setEstado(EstadoProposta.ELEGIVEL);
    } catch (FeignException e) {
      // se a proposta não for elegível, serviço retorna status 422 estourando exceção
      if (e.status() == 422) proposta.setEstado(EstadoProposta.NAO_ELEGIVEL);
      else throw e;
    }
    propostaRepository.save(proposta);
    logger.info(proposta.toString());
    final URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(proposta.getId())
            .toUri();
    metricas.incrementaCounterPropostaCriada();
    return ResponseEntity.created(location).build();
  }
}
