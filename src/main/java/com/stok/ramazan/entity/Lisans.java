package com.stok.ramazan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stok.ramazan.helper.CustomerDateAndTimeDeserialize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LICENSE")
public class Lisans extends BaseEntity {
    @Column(name = "LICENSE_KEY", unique = true)
    private String licenseKey;
    @Column(name = "LICENSE_START_DATE")
    @JsonIgnore
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date licenseStartDate;
    @Column(name = "LICENSE_FINISH_DATE")
    @JsonIgnore
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date licenseFinishDate;
    @ManyToOne
    @JoinColumn(name = "FIRMA")
    private Firma firma;

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

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }
}