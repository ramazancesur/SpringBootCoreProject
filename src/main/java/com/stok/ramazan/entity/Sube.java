package com.stok.ramazan.entity;

import com.stok.ramazan.helper.EnumUtil.FirmaTipi;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBE")
public class Sube extends BaseEntity {
  private static final long serialVersionUID = 1L;
  @Column(name = "FIRM_ADI")
  private String firmaAdi;
  @Column(name = "FIRMA_TIPI")
  private FirmaTipi firmaTipi;
  @OneToOne
  @JoinColumn(name = "SUBE_ADRES")
  private Address adress;
  @ManyToOne
  @JoinColumn(name = "FIRMA", nullable = true)
  private Firma firma;
  @ManyToOne
  @JoinColumn(name = "USTLENICI_FIRMA", nullable = true)
  private UstleniciFirma ustleniciFirma;

  @OneToMany
  @Column(name = "SUBE_CONDUCT")
  private List<Conduct> lstConduct;

  public String getFirmaAdi() {
    return firmaAdi;
  }

  public void setFirmaAdi(String firmaAdi) {
    this.firmaAdi = firmaAdi;
  }

  public Address getAdress() {
    return adress;
  }

  public void setAdress(Address adress) {
    this.adress = adress;
  }

  public FirmaTipi getFirmaTipi() {
    return firmaTipi;
  }

  public void setFirmaTipi(FirmaTipi firmaTipi) {
    this.firmaTipi = firmaTipi;
  }

  public Firma getFirma() {
    return firma;
  }

  public void setFirma(Firma firma) {
    this.firma = firma;
  }

  public UstleniciFirma getUstleniciFirma() {
    return ustleniciFirma;
  }

  public void setUstleniciFirma(UstleniciFirma ustleniciFirma) {
    this.ustleniciFirma = ustleniciFirma;
  }

  public List<Conduct> getLstConduct() {
    return lstConduct;
  }

  public void setLstConduct(List<Conduct> lstConduct) {
    this.lstConduct = lstConduct;
  }

}