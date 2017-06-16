package com.svlada.entity;

/**
 * Created by ramazancesur on 16/06/2017.
 */
public enum Role {
  ADMIN, PREMIUM_MEMBER, MEMBER;

  public String authority() {
    return "ROLE_" + this.name();
  }
}