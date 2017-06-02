package com.stok.ramazan.entity;

import com.stok.ramazan.annotations.Control;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Ramazan CESUR on 02.06.2017.
 */
public class Fatura extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_OID")
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BORC_OID")
    private Borc borc;
    @Column(name = "FATURA_NOTU")
    private String faturaNotu;
    @Column(name = "SIRKET_ADI")
    private String sirketAdi;
    @Column(name = "SIRKET_LOGO_PATH")
    private String sirketLogoPath;
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

    public String getSirketAdi() {
        return sirketAdi;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }

    public String getSirketLogoPath() {
        return sirketLogoPath;
    }

    public void setSirketLogoPath(String sirketLogoPath) {
        this.sirketLogoPath = sirketLogoPath;
    }
}