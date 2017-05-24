package com.stok.ramazan.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CALISAN")
public class Employee extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @OneToOne
    @JoinColumn(name = "EMP_USER")
    private User user;
    @Column(name = "ISE_GIRIS_TARIHI")
    private Date iseGirisTarihi;
    @Column(name = "ISTEN_CIKIS_TARIHI")
    private Date istenCikisTarihi;
    @OneToOne
    @JoinColumn(name = "EMP_SUBE")
    private Sube firma;

    @OneToMany
    @JoinColumn(name = "USER_ADRES")
    private List<Address> lstAdres;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getIseGirisTarihi() {
        return iseGirisTarihi;
    }

    public void setIseGirisTarihi(Date iseGirisTarihi) {
        this.iseGirisTarihi = iseGirisTarihi;
    }

    public Date getIstenCikisTarihi() {
        return istenCikisTarihi;
    }

    public void setIstenCikisTarihi(Date istenCikisTarihi) {
        this.istenCikisTarihi = istenCikisTarihi;
    }

    public Sube getFirma() {
        return firma;
    }

    public void setFirma(Sube firma) {
        this.firma = firma;
    }

    public List<Address> getLstAdres() {
        return lstAdres;
    }

    public void setLstAdres(List<Address> lstAdres) {
        this.lstAdres = lstAdres;
    }

}