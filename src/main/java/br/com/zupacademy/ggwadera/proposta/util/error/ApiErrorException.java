package br.com.zupacademy.ggwadera.proposta.util.error;

import org.springframework.http.HttpStatus;

public class ApiErrorException extends RuntimeException {
  private final HttpStatus status;
  private final String reason;

  public ApiErrorException(HttpStatus status, String reason) {
    super(reason);
    this.status = status;
    this.reason = reason;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getReason() {
    return reason;
  }
}
