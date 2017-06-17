package com.stok.ramazan.entity;

import javax.persistence.*;

@Entity
@Table(name = "SATIS_DETAY")
public class OrderDetail extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "SATIS_DETAY_MALZEME")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "STOK")
    private Stok stok;

    @Column(name = "ADET")
    private int adet;
    @ManyToOne
    @JoinColumn(name = "SATIS")
    private Order order;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Stok getStok() {
        return stok;
    }

    public void setStok(Stok stok) {
        this.stok = stok;
    }

}