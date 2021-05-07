package br.com.zupacademy.ggwadera.proposta.biometria;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import br.com.zupacademy.ggwadera.proposta.cartao.CartaoRepository;
import br.com.zupacademy.ggwadera.proposta.util.error.ApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class BiometriaController {

  private final CartaoRepository cartaoRepository;
  private final BiometriaRepository biometriaRepository;

  @Autowired
  public BiometriaController(
      CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
    this.cartaoRepository = cartaoRepository;
    this.biometriaRepository = biometriaRepository;
  }

  @PostMapping("/cartoes/{id}/biometria")
  public ResponseEntity<Void> novaBiometria(
      @PathVariable String id,
      @RequestBody @Valid NovaBiometriaRequest request,
      UriComponentsBuilder builder) {
    Cartao cartao =
        cartaoRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ApiErrorException(
                        HttpStatus.NOT_FOUND, "Não foi encontrado um cartão com este id"));
    Biometria biometria = biometriaRepository.save(request.toModel(cartao));
    URI uri = builder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
}
