package br.com.zupacademy.ggwadera.proposta.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
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

  @ExceptionHandler(ApiErrorException.class)
  public ResponseEntity<ApiError> handle(ApiErrorException exception) {
    ApiError error = new ApiError(List.of(exception.getReason()));
    return ResponseEntity.status(exception.getStatus()).body(error);
  }
}
