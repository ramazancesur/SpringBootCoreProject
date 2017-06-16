package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BORC_DETAY")
public class BorcDetay extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "PRODUCT")
  private Product product;
  @Column(name = "METRE")
  private int metre;
  @Column(name = "ADET")
  private int adet;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getMetre() {
    return metre;
  }

  public void setMetre(int metre) {
    this.metre = metre;
  }

  public int getAdet() {
    return adet;
  }

  public void setAdet(int adet) {
    this.adet = adet;
  }
}