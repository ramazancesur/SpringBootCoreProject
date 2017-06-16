package com.stok.ramazan.securty.controller;

import com.stok.ramazan.securty.JwtTokenUtil;
import com.stok.ramazan.securty.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

  @Value("Authorization")
  private String tokenHeader;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  @RequestMapping(value = "user", method = RequestMethod.GET)
  public JwtUser getAuthenticatedUser(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
    return user;
  }

}
