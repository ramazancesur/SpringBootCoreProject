package com.stok.ramazan.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MUSTERI")
public class Musteri extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(name = "SOYADI")
  private String soyadi;
  @Column(name = "ADI")
  private String adi;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "MUSTERI_CONTACT")
  private List<Conduct> lstConduct;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "MUSTERI_ADRES")
  private List<Address> lstAddress;

  @OneToMany
  @JoinColumn(name = "MUSTERI_KEFIL", nullable = true)
  private List<Kefil> lstKefil;

  public String getSoyadi() {
    return soyadi;
  }

  public void setSoyadi(String soyadi) {
    this.soyadi = soyadi;
  }

  public String getAdi() {
    return adi;
  }

  public void setAdi(String adi) {
    this.adi = adi;
  }

  public List<Conduct> getLstConduct() {
    return lstConduct;
  }

  public void setLstConduct(List<Conduct> lstConduct) {
    this.lstConduct = lstConduct;
  }

  public List<Address> getLstAddress() {
    return lstAddress;
  }

  public void setLstAddress(List<Address> lstAddress) {
    this.lstAddress = lstAddress;
  }

  public List<Kefil> getLstKefil() {
    return lstKefil;
  }

  public void setLstKefil(List<Kefil> lstKefil) {
    this.lstKefil = lstKefil;
  }
}