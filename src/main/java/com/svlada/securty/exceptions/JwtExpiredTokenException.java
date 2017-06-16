package com.svlada.securty.exceptions;

import com.svlada.securty.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by ramazancesur on 17/06/2017.
 */
public class JwtExpiredTokenException extends AuthenticationException {
  private static final long serialVersionUID = -5959543783324224864L;

  private JwtToken token;

  public JwtExpiredTokenException(String msg) {
    super(msg);
  }

  public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
    super(msg, t);
    this.token = token;
  }

  public String token() {
    return this.token.getToken();
  }
}



