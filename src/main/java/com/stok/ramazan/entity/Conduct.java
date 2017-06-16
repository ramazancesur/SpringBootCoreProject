package com.stok.ramazan.entity;

import com.stok.ramazan.helper.EnumUtil.ContactTipi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CONDUCT")
public class Conduct extends BaseEntity {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  @Column(name = "CONDUCT_TIPI")
  private ContactTipi contactType;

  @Column(name = "TEL_NO")
  private String telNo;

  public ContactTipi getContactType() {
    return contactType;
  }

  public void setContactType(ContactTipi contactType) {
    this.contactType = contactType;
  }

  public String getTelNo() {
    return telNo;
  }

  public void setTelNo(String telNo) {
    this.telNo = telNo;
  }
}
