package com.stok.ramazan.entity;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Ramazan CESUR on 02.06.2017.
 */

@Entity
@Table(name = "FATURA")
public class Fatura extends BaseEntity {
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "EMPLOYEE_OID")
  private Employee employee;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "BORC_OID")
  private Borc borc;
  @Column(name = "FATURA_NOTU")
  private String faturaNotu;
  @OneToOne
  @JoinColumn(name = "FIRMA_OID")
  private Firma firma;

  @Column(name = "FATURA_TUTARI")
  private BigDecimal faturaTutari;

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Borc getBorc() {
    return borc;
  }

  public void setBorc(Borc borc) {
    this.borc = borc;
  }

  public String getFaturaNotu() {
    return faturaNotu;
  }

  public void setFaturaNotu(String faturaNotu) {
    this.faturaNotu = faturaNotu;
  }

  public Firma getFirma() {
    return firma;
  }

  public void setFirma(Firma firma) {
    this.firma = firma;
  }

  public BigDecimal getFaturaTutari() {
    return faturaTutari;
  }

  public void setFaturaTutari(BigDecimal faturaTutari) {
    this.faturaTutari = faturaTutari;
  }
}