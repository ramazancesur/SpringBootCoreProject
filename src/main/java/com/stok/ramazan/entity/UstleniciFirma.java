package com.stok.ramazan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USTLENICI_FIRMA")
public class UstleniciFirma extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "ADI")
    private String adi;
    @OneToOne
    private Address adress;

    @OneToMany
    @Column(name = "USTLENICI_FIRMA_CONDUCT")
    private List<Conduct> lstConduct;

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
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