package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "YETKI")
public class Role extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "YETKI_ADI", length = 50)
    private String yetkiAdi;
    @Column(name = "YETKI_ACIKLAMASI")
    private String yetkiAciklamasi;

    public String getYetkiAdi() {
        return yetkiAdi;
    }

    public void setYetkiAdi(String yetkiAdi) {
        this.yetkiAdi = yetkiAdi;
    }

    public String getYetkiAciklamasi() {
        return yetkiAciklamasi;
    }

    public void setYetkiAciklamasi(String yetkiAciklamasi) {
        this.yetkiAciklamasi = yetkiAciklamasi;
    }

}