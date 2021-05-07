package br.com.zupacademy.ggwadera.proposta.biometria;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class NovaBiometriaRequest {

  @NotBlank private String fingerprint;

  public String getFingerprint() {
    return fingerprint;
  }

  public void setFingerprint(String fingerprint) {
    this.fingerprint = fingerprint;
  }

  public Biometria toModel(Cartao cartao) {
    return new Biometria(
        Base64.getDecoder().decode(fingerprint.getBytes(StandardCharsets.UTF_8)), cartao);
  }
}
