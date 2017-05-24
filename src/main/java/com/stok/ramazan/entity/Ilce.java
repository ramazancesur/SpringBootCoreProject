package com.stok.ramazan.entity;

import javax.persistence.*;

@Entity
@Table(name = "ILCE")
public class Ilce extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "ADI")
    private String ilceAdi;
    @ManyToOne
    @JoinColumn(name = "il_id")
    private Il il;

    public String getIlceAdi() {
        return ilceAdi;
    }

    public void setIlceAdi(String ilceAdi) {
        this.ilceAdi = ilceAdi;
    }

    public Il getIl() {
        return il;
    }

    public void setIl(Il il) {
        this.il = il;
    }

}