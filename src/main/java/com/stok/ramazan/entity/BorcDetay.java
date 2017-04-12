package com.stok.ramazan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="BORC_DETAY")
public class BorcDetay extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="KALAN_BORC")
	private BigDecimal kalanBorc;
	@Column(name="ONCEKI_BORC")
	private BigDecimal oncekiBorc;
	@Column(name="TOPLAM_AKSATMA_SAYISI")
	private int toplamAksatmaSayisi;
	@Column(name="TOPLAM_GECIKILEN_GUN_SAYISI")
	private int toplamGecikilenGunSayisi;
	@Column(name="ORTALAMA_GECIKILEN_GUN_SAYISI")
	private double ortalamaGecikilenGunSayisi;
	@ManyToOne
	@JoinColumn(name="BORC")
	private Borc borc;
	public BigDecimal getKalanBorc() {
		return kalanBorc;
	}
	public void setKalanBorc(BigDecimal kalanBorc) {
		this.kalanBorc = kalanBorc;
	}
	public BigDecimal getOncekiBorc() {
		return oncekiBorc;
	}
	public void setOncekiBorc(BigDecimal oncekiBorc) {
		this.oncekiBorc = oncekiBorc;
	}
	public int getToplamAksatmaSayisi() {
		return toplamAksatmaSayisi;
	}
	public void setToplamAksatmaSayisi(int toplamAksatmaSayisi) {
		this.toplamAksatmaSayisi = toplamAksatmaSayisi;
	}
	public int getToplamGecikilenGunSayisi() {
		return toplamGecikilenGunSayisi;
	}
	public void setToplamGecikilenGunSayisi(int toplamGecikilenGunSayisi) {
		this.toplamGecikilenGunSayisi = toplamGecikilenGunSayisi;
	}
	public double getOrtalamaGecikilenGunSayisi() {
		return ortalamaGecikilenGunSayisi;
	}
	public void setOrtalamaGecikilenGunSayisi(double ortalamaGecikilenGunSayisi) {
		this.ortalamaGecikilenGunSayisi = ortalamaGecikilenGunSayisi;
	}
	public Borc getBorc() {
		return borc;
	}
	public void setBorc(Borc borc) {
		this.borc = borc;
	}
}