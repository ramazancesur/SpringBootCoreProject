package com.stok.ramazan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STOK")
public class Stok extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name = "PRODUCT")
	private Product product;
	@Column(name = "GELIS_TARIHI")
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
