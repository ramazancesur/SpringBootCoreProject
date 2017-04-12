package com.stok.ramazan.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STOK")
public class Stok extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private Date gelis_tarihi;
	private int adet;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getGelis_tarihi() {
		return gelis_tarihi;
	}
	public void setGelis_tarihi(Date gelis_tarihi) {
		this.gelis_tarihi = gelis_tarihi;
	}
	public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
}
