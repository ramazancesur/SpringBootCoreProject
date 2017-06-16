package com.stok.ramazan.entity;

import com.stok.ramazan.helper.EnumUtil.UnitType;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MALZEME")
public class Product extends BaseEntity {
  @Column(name = "ADI")
  private String productName;
  @Column(name = "ACIKLAMA")
  private String aciklama;
  @Column(name = "SON_KULLANMA_TARIHI")
  private Date sonKullanmaTarihi;
  @Column(name = "IBAN_NO")
  private String ibanNo;
  @Column(name = "UNIT_TYPE")
  private UnitType unitType;
  @Column(name = "GELIS_TARIHI")
  private Date commingDate;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "MALZEME_FIYAT_LISTESI")
  private List<Price> lstPrice;

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getAciklama() {
    return aciklama;
  }

  public void setAciklama(String aciklama) {
    this.aciklama = aciklama;
  }

  public Date getSonKullanmaTarihi() {
    return sonKullanmaTarihi;
  }

  public void setSonKullanmaTarihi(Date sonKullanmaTarihi) {
    this.sonKullanmaTarihi = sonKullanmaTarihi;
  }

  public String getIbanNo() {
    return ibanNo;
  }

  public void setIbanNo(String ibanNo) {
    this.ibanNo = ibanNo;
  }

  public UnitType getUnitType() {
    return unitType;
  }

  public void setUnitType(UnitType unitType) {
    this.unitType = unitType;
  }

  public Date getCommingDate() {
    return commingDate;
  }

  public void setCommingDate(Date commingDate) {
    this.commingDate = commingDate;
  }

  public List<Price> getLstPrice() {
    return lstPrice;
  }

  public void setLstPrice(List<Price> lstPrice) {
    this.lstPrice = lstPrice;
  }
}