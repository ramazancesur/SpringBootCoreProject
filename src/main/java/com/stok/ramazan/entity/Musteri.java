package com.stok.ramazan.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MUSTERI")
public class Musteri extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Column(name = "ADI")
    private String adi;
    @Column(name = "SOYADI")
    private String soyadi;
    @Column(name = "MUSTERI_TOPLAM_BORCU")
    private BigDecimal musteriToplamBorcu;
    @ManyToOne
    @JoinColumn(name = "FIRMA")
    private Firma firma;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "MUSTERI_CONTACT")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Conduct> lstConduct;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "MUSTERI_ADRES")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Address> lstAddress;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "MUSTERI_KEFIL")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Kefil> lstKefil;

    public List<Kefil> getLstKefil() {
        return lstKefil;
    }

    public void setLstKefil(List<Kefil> lstKefil) {
        this.lstKefil = lstKefil;
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

    public BigDecimal getMusteriToplamBorcu() {
        return musteriToplamBorcu;
    }

    public void setMusteriToplamBorcu(BigDecimal musteriToplamBorcu) {
        this.musteriToplamBorcu = musteriToplamBorcu;
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
}