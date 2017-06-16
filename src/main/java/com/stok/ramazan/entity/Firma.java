package com.stok.ramazan.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FIRMA")
public class Firma extends BaseEntity {

  @Column(name = "FIRM_ADI")
  private String firmaAdi;
  @Column(name = "FIRMA_LOGO_YOLU")
  private String firmaLogoYolu;

  @OneToOne
  @JoinColumn(name = "FIRM_USER")
  private User user;

  @OneToOne
  @JoinColumn(name = "USER_ADRES")
  private Address adress;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "FIRMA_CONDUCT_ID")
  private List<Conduct> lstConduct;

  public String getFirmaAdi() {
    return firmaAdi;
  }

  public void setFirmaAdi(String firmaAdi) {
    this.firmaAdi = firmaAdi;
  }

  public String getFirmaLogoYolu() {
    return firmaLogoYolu;
  }

  public void setFirmaLogoYolu(String firmaLogoYolu) {
    this.firmaLogoYolu = firmaLogoYolu;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Address getAdress() {
    return adress;
  }

  public void setAdress(Address adress) {
    this.adress = adress;
  }

  public List<Conduct> getLstConduct() {
    return lstConduct;
  }

  public void setLstConduct(List<Conduct> lstConduct) {
    this.lstConduct = lstConduct;
  }
}