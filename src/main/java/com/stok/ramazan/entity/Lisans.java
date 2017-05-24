package com.stok.ramazan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LICENSE")
public class Lisans extends BaseEntity {
    @Column(name = "LICENSE_KEY", unique = true)
    private String licenseKey;
    @Column(name = "LICENSE_START_DATE")
    private Date licenseStartDate;
    @Column(name = "LICENSE_FINISH_DATE")
    private Date licenseFinishDate;
    @ManyToOne
    @JoinColumn(name = "MUSTERI_OID")
    private Musteri musteri;

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public Date getLicenseStartDate() {
        return licenseStartDate;
    }

    public void setLicenseStartDate(Date licenseStartDate) {
        this.licenseStartDate = licenseStartDate;
    }

    public Date getLicenseFinishDate() {
        return licenseFinishDate;
    }

    public void setLicenseFinishDate(Date licenseFinishDate) {
        this.licenseFinishDate = licenseFinishDate;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }
}