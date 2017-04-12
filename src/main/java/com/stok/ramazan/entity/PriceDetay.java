package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ibm.icu.math.BigDecimal;

@Entity
@Table(name = "PRICE_DETAY")
public class PriceDetay extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ONCEKI_FIYAT")
	private BigDecimal oncekiFiyat;
	@Column(name = "SONRAKI_FIYAT")
	private BigDecimal sonrakiFiyat;
	@ManyToOne
	@JoinColumn(name = "PRICE")
	private Price price;

	public BigDecimal getOncekiFiyat() {
		return oncekiFiyat;
	}

	public void setOncekiFiyat(BigDecimal oncekiFiyat) {
		this.oncekiFiyat = oncekiFiyat;
	}

	public BigDecimal getSonrakiFiyat() {
		return sonrakiFiyat;
	}

	public void setSonrakiFiyat(BigDecimal sonrakiFiyat) {
		this.sonrakiFiyat = sonrakiFiyat;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
}