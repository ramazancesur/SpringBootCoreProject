package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="IL")
public class Il extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="ADI")
	private String ilAdi;
	@Column(name="PLAKA_KODU")
	private int plakaKodu;
	public String getIlAdi() {
		return ilAdi;
	}
	public void setIlAdi(String ilAdi) {
		this.ilAdi = ilAdi;
	}
	public int getPlakaKodu() {
		return plakaKodu;
	}
	public void setPlakaKodu(int plakaKodu) {
		this.plakaKodu = plakaKodu;
	}

	
}
