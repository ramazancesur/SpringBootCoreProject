package com.svlada.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by ramazancesur on 16/06/2017.
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
  @Enumerated(EnumType.STRING)
  @Column(name = "ROLE", insertable = false, updatable = false)
  protected Role role;
  @EmbeddedId
  Id id = new Id();

  public Role getRole() {
    return role;
  }

  @Embeddable
  public static class Id implements Serializable {
    private static final long serialVersionUID = 1322120000551624359L;

    @Column(name = "APP_USER_ID")
    protected Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    protected Role role;

    public Id() {
    }

    public Id(Long userId, Role role) {
      this.userId = userId;
      this.role = role;
    }
  }
}