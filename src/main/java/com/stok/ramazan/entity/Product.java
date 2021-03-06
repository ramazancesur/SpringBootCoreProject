package com.stok.ramazan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stok.ramazan.helper.CustomerDateAndTimeDeserialize;
import com.stok.ramazan.helper.EnumUtil.UnitType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MALZEME")
public class Product extends BaseEntity {
    @Column(name = "ADI")
    private String productName;
    @Column(name = "ACIKLAMA")
    private String aciklama;
    @Column(name = "SON_KULLANMA_TARIHI")
    @JsonIgnore
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date sonKullanmaTarihi;
    @Column(name = "IBAN_NO")
    private String ibanNo;
    @Column(name = "UNIT_TYPE")
    private UnitType unitType;
    @Column(name = "GELIS_TARIHI")
    @JsonIgnore
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date commingDate;
    @OneToOne
    @JoinColumn(name = "MALZEME_FIYAT_LISTESI")
    private Price price;

    @ManyToOne
    @JoinColumn(name = "FIRMA_OID")
    private Firma firma;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Date getSonKullanmaTarihi() {
        return sonKullanmaTarihi;
    }

    public void setSonKullanmaTarihi(Date sonKullanmaTarihi) {
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    public String getIbanNo() {
        return ibanNo;
    }

    public void setIbanNo(String ibanNo) {
        this.ibanNo = ibanNo;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public Date getCommingDate() {
        return commingDate;
    }

    public void setCommingDate(Date commingDate) {
        this.commingDate = commingDate;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }
}