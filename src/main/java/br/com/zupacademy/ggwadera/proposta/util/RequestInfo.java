package br.com.zupacademy.ggwadera.proposta.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.servlet.http.HttpServletRequest;

@Embeddable
public class RequestInfo {

  @Column(nullable = false)
  private String ip;

  @Column(nullable = false)
  private String userAgent;

  @Deprecated
  public RequestInfo() {}

  public RequestInfo(HttpServletRequest request) {
    this.ip = request.getRemoteAddr();
    this.userAgent = request.getHeader("User-Agent");
  }

  public String getIp() {
    return ip;
  }

  public String getUserAgent() {
    return userAgent;
  }
}
