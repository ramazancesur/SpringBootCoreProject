package com.stok.ramazan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FIRMA")
public class Firma extends BaseEntity {
    @Column(name = "FIRM_ADI")
    private String firmaAdi;
    @Column(name = "FIRMA_LOGO_YOLU")
    private String firmaLogoYolu;

    @Column(name = "KALAN_SMS")
    private Integer kalanSms;

    @OneToOne
    @JoinColumn(name = "FIRM_USER")
    private User user;

    @OneToOne
    @JoinColumn(name = "USER_ADRES")
    private Address adress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FIRMA_CONDUCT_ID")
    private List<Conduct> lstConduct;

    @Column(name = "ANDROID_LOGO_PATH")
    private String androidLogoPath;


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

    public int getKalanSms() {
        return kalanSms;
    }

    public void setKalanSms(int kalanSms) {
        this.kalanSms = kalanSms;
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

    public String getAndroidLogoPath() {
        return androidLogoPath;
    }

    public void setAndroidLogoPath(String androidLogoPath) {
        this.androidLogoPath = androidLogoPath;
    }
}