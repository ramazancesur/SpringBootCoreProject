package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "FIYAT")
public class Price extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "FIYATI")
    private BigDecimal fiyati;
    @Column(name = "ACIKLAMA")
    private String aciklamasi;

    public BigDecimal getFiyati() {
        return fiyati;
    }

    public void setFiyati(BigDecimal fiyati) {
        this.fiyati = fiyati;
    }

    public String getAciklamasi() {
        return aciklamasi;
    }

    public void setAciklamasi(String aciklamasi) {
        this.aciklamasi = aciklamasi;
    }

}