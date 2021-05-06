package br.com.zupacademy.ggwadera.proposta.proposta;

import br.com.zupacademy.ggwadera.proposta.util.error.ApiErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.function.Function;

public class DocumentoJaSolicitadoValidator implements Validator {

  private final Function<String, Boolean> validatorFunction;

  public DocumentoJaSolicitadoValidator(Function<String, Boolean> validatorFunction) {
    this.validatorFunction = validatorFunction;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return NovaPropostaRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) return;
    NovaPropostaRequest request = (NovaPropostaRequest) target;
    if (validatorFunction.apply(request.getDocumento())) {
      throw new ApiErrorException(
          HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta para este documento");
    }
  }
}
