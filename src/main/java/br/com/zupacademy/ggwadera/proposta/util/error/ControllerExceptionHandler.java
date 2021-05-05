package br.com.zupacademy.ggwadera.proposta.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handle(MethodArgumentNotValidException exception) {
    return new ApiError(
        exception.getBindingResult().getFieldErrors().stream()
            .map(
                fieldError ->
                    String.format(
                        "Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList()));
  }
}
