package com.stok.ramazan.dto;

import java.util.Date;

public class UrunDTO extends BaseDTO {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String productName;
    private double price;
    private Date sonKullanmaTarihi;
    private Date gelisTarihi;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getSonKullanmaTarihi() {
        return sonKullanmaTarihi;
    }

    public void setSonKullanmaTarihi(Date sonKullanmaTarihi) {
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    public Date getGelisTarihi() {
        return gelisTarihi;
    }

    public void setGelisTarihi(Date gelisTarihi) {
        this.gelisTarihi = gelisTarihi;
    }
}