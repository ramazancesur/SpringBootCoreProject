package com.stok.ramazan.entity;

import com.stok.ramazan.helper.EnumUtil.OdemeTipi;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PAYMENT")
public class Payment extends BaseEntity {
    @Column(name = "ODEME_TUTARI")
    private BigDecimal odemeTutari;
    @Column(name = "ODEME_TIPI")
    private OdemeTipi odemeTipi;
    @Column(name = "BEKLENEN_ODEME_TARIHI")
    private Date beklenenOdemeTarihi;
    @Column(name = "GERCEKLESEN_ODEME_TARIHI")
    private Date gerceklesenOdemeTarihi;
    @ManyToOne
    @JoinColumn(name = "SATICI_SUBE")
    private Sube saticiSube;
    @ManyToOne
    @JoinColumn(name = "USTLENICI_SUBE")
    private Sube ustleniciSube;

    @ManyToOne
    @JoinColumn(name = "BORC_OID")
    private Borc borc;

    public BigDecimal getOdemeTutari() {
        return odemeTutari;
    }

    public void setOdemeTutari(BigDecimal odemeTutari) {
        this.odemeTutari = odemeTutari;
    }

    public OdemeTipi getOdemeTipi() {
        return odemeTipi;
    }

    public void setOdemeTipi(OdemeTipi odemeTipi) {
        this.odemeTipi = odemeTipi;
    }

    public Date getBeklenenOdemeTarihi() {
        return beklenenOdemeTarihi;
    }

    public void setBeklenenOdemeTarihi(Date beklenenOdemeTarihi) {
        this.beklenenOdemeTarihi = beklenenOdemeTarihi;
    }

    public Date getGerceklesenOdemeTarihi() {
        return gerceklesenOdemeTarihi;
    }

    public void setGerceklesenOdemeTarihi(Date gerceklesenOdemeTarihi) {
        this.gerceklesenOdemeTarihi = gerceklesenOdemeTarihi;
    }

    public Sube getSaticiSube() {
        return saticiSube;
    }

    public void setSaticiSube(Sube saticiSube) {
        this.saticiSube = saticiSube;
    }

    public Sube getUstleniciSube() {
        return ustleniciSube;
    }

    public void setUstleniciSube(Sube ustleniciSube) {
        this.ustleniciSube = ustleniciSube;
    }

    public Borc getBorc() {
        return borc;
    }

    public void setBorc(Borc borc) {
        this.borc = borc;
    }
}