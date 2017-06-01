package com.stok.ramazan.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BORC")
public class Borc extends BaseEntity {
    @Column(name = "TOPLAM_BORC")
    private BigDecimal toplamBorc;
    @Column(name = "KALAN_BORC")
    private BigDecimal kalanBorc;

    @Column(name = "MUSTERI_NOTU")
    private String musteriNotu;
    @Column(name = "SATICI_NOTU")
    private String saticiNotu;

    @Column
    private Date beklenenOdemeTarihi;

    @ManyToOne
    @JoinColumn(name = "ODEME_SUBE")
    private Sube odemeSube;
    @ManyToOne
    @JoinColumn(name = "ODEME_USTLENICI_SUBE")
    private Sube ustleniciSube;
    @ManyToOne
    @JoinColumn(name = "MUSTERI")
    private Musteri musteri;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BORC_DETAY_OID")
    private List<BorcDetay> lstBorceDetay;

    public BigDecimal getToplamBorc() {
        return toplamBorc;
    }

    public void setToplamBorc(BigDecimal toplamBorc) {
        this.toplamBorc = toplamBorc;
    }

    public BigDecimal getKalanBorc() {
        return kalanBorc;
    }

    public void setKalanBorc(BigDecimal kalanBorc) {
        this.kalanBorc = kalanBorc;
    }

    public Sube getOdemeSube() {
        return odemeSube;
    }

    public void setOdemeSube(Sube odemeSube) {
        this.odemeSube = odemeSube;
    }

    public Sube getUstleniciSube() {
        return ustleniciSube;
    }

    public void setUstleniciSube(Sube ustleniciSube) {
        this.ustleniciSube = ustleniciSube;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public List<BorcDetay> getLstBorceDetay() {
        return lstBorceDetay;
    }

    public void setLstBorceDetay(List<BorcDetay> lstBorceDetay) {
        this.lstBorceDetay = lstBorceDetay;
    }

    public Date getBeklenenOdemeTarihi() {
        return beklenenOdemeTarihi;
    }

    public void setBeklenenOdemeTarihi(Date beklenenOdemeTarihi) {
        this.beklenenOdemeTarihi = beklenenOdemeTarihi;
    }

    public String getMusteriNotu() {
        return musteriNotu;
    }

    public void setMusteriNotu(String musteriNotu) {
        this.musteriNotu = musteriNotu;
    }

    public String getSaticiNotu() {
        return saticiNotu;
    }

    public void setSaticiNotu(String saticiNotu) {
        this.saticiNotu = saticiNotu;
    }
}