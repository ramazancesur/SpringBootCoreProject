package com.stok.ramazan.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LICENSE")
public class Lisans extends BaseEntity {
  @Column(name = "LICENSE_KEY", unique = true)
  private String licenseKey;
  @Column(name = "LICENSE_START_DATE")
  private Date licenseStartDate;
  @Column(name = "LICENSE_FINISH_DATE")
  private Date licenseFinishDate;
  @ManyToOne
  @JoinColumn(name = "FIRMA")
  private Firma firma;

  public String getLicenseKey() {
    return licenseKey;
  }

  public void setLicenseKey(String licenseKey) {
    this.licenseKey = licenseKey;
  }

  public Date getLicenseStartDate() {
    return licenseStartDate;
  }

  public void setLicenseStartDate(Date licenseStartDate) {
    this.licenseStartDate = licenseStartDate;
  }

  public Date getLicenseFinishDate() {
    return licenseFinishDate;
  }

  public void setLicenseFinishDate(Date licenseFinishDate) {
    this.licenseFinishDate = licenseFinishDate;
  }

  public Firma getFirma() {
    return firma;
  }

  public void setFirma(Firma firma) {
    this.firma = firma;
  }
}