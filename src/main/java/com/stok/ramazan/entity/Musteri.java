package com.stok.ramazan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MUSTERI")
public class Musteri extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Column(name = "ADI")
    private String adi;
    @Column(name = "SOYADI")
    private String soyadi;
    @ManyToOne
    @JoinColumn(name = "FIRMA")
    private Firma firma;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MUSTERI_CONTACT")
    private List<Conduct> lstConduct;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MUSTERI_ADRES")
    private List<Address> lstAddress;
    @OneToMany
    @JoinColumn(name = "MUSTERI_KEFIL", nullable = true)
    private List<Kefil> lstKefil;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
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