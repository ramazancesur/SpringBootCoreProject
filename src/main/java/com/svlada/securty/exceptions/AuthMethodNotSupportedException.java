package com.svlada.securty.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created by ramazancesur on 17/06/2017.
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
  private static final long serialVersionUID = 3705043083010304496L;

  public AuthMethodNotSupportedException(String msg) {
    super(msg);
  }
}
