package br.com.zupacademy.ggwadera.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

  private final PropostaRepository propostaRepository;

  @Autowired
  public PropostaController(PropostaRepository propostaRepository) {
    this.propostaRepository = propostaRepository;
  }

  @PostMapping
  public ResponseEntity<Void> novaProposta(@RequestBody @Valid NovaPropostaRequest request) {
    Proposta proposta = propostaRepository.save(request.toModel());
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(proposta.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }
}
