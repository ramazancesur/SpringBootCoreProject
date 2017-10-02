package com.stok.ramazan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stok.ramazan.helper.CustomerDateAndTimeDeserialize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STOK")
public class Stok extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "PRODUCT")
    private Product product;
    @Column(name = "GELIS_TARIHI")
    @JsonIgnore
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date gelisTarihi;
    @Column(name = "ADET")
    private int adet;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getGelisTarihi() {
        return gelisTarihi;
    }

    public void setGelisTarihi(Date gelisTarihi) {
        this.gelisTarihi = gelisTarihi;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

}