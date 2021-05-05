package br.com.zupacademy.ggwadera.proposta.proposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

  private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

  private final PropostaRepository propostaRepository;

  @Autowired
  public PropostaController(PropostaRepository propostaRepository) {
    this.propostaRepository = propostaRepository;
  }

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(new DocumentoJaSolicitadoValidator(propostaRepository::existsByDocumento));
  }

  @PostMapping
  public ResponseEntity<Void> novaProposta(@RequestBody @Valid NovaPropostaRequest request) {
    Proposta proposta = propostaRepository.save(request.toModel());
    logger.info("Nova proposta {}", proposta);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(proposta.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }
}
