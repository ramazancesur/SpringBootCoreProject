package com.stok.ramazan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DOVIZ")
public class Doviz extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "DOVIZ_KODU")
	private String dovizKodu;
	@Column(name = "BIRIM")
	private int birim;
	@Column(name = "DOVIZ_CINSI")
	private String dovizCinsi;
	@Column(name = "DOVIZ_ALIS")
	private BigDecimal dovizAlis;
	@Column(name = "DOVIZ_SATIS")
	private BigDecimal dovizSatis;

	public String getDovizKodu() {
		return dovizKodu;
	}

	public void setDovizKodu(String dovizKodu) {
		this.dovizKodu = dovizKodu;
	}

	public int getBirim() {
		return birim;
	}

	public void setBirim(int birim) {
		this.birim = birim;
	}

	public String getDovizCinsi() {
		return dovizCinsi;
	}

	public void setDovizCinsi(String dovizCinsi) {
		this.dovizCinsi = dovizCinsi;
	}

	public BigDecimal getDovizAlis() {
		return dovizAlis;
	}

	public void setDovizAlis(BigDecimal dovizAlis) {
		this.dovizAlis = dovizAlis;
	}

	public BigDecimal getDovizSatis() {
		return dovizSatis;
	}

	public void setDovizSatis(BigDecimal dovizSatis) {
		this.dovizSatis = dovizSatis;
	}

}
