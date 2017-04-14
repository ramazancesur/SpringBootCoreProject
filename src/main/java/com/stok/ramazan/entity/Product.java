package com.stok.ramazan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stok.ramazan.helper.EnumUtil.UnitType;

@Entity
@Table(name = "MALZEME")
public class Product extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ADI")
	private String productName;
	@Column(name = "ACIKLAMA")
	private String aciklama;
	@Column(name = "SON_KULLANMA_TARIHI")
	private Date sonKullanmaTarihi;
	@Column(name = "IBAN_NO")
	private String ibanNo;
	@Column(name = "UNIT_TYPE")
	private UnitType unitType;
	@ManyToOne
	@JoinColumn(name = "MALZEME_FIYATI")
	private Price price;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getSonKullanmaTarihi() {
		return sonKullanmaTarihi;
	}

	public void setSonKullanmaTarihi(Date sonKullanmaTarihi) {
		this.sonKullanmaTarihi = sonKullanmaTarihi;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
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

}