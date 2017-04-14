package com.stok.ramazan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stok.ramazan.helper.EnumUtil.OdemeTipi;

@Entity
@Table(name = "BORC")
public class Borc extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "TOPLAM_BORC")
	private BigDecimal toplamBorc;
	@Column(name = "KALAN_BORC")
	private BigDecimal kalanBorc;
	@Column(name = "ODEME_TIPI")
	private OdemeTipi odemeTipi;
	@ManyToOne
	@JoinColumn(name = "ODEME_SUBE")
	private Sube odemeSube;
	@ManyToOne
	@JoinColumn(name = "ODEME_USTLENICI_SUBE")
	private Sube ustleniciSube;
	@ManyToOne
	@JoinColumn(name = "MUSTERI")
	private Musteri musteri;

	public BigDecimal getToplamBorc() {
		return toplamBorc;
	}

	public void setToplamBorc(BigDecimal toplamBorc) {
		this.toplamBorc = toplamBorc;
	}

	public BigDecimal getKalanBorc() {
		return kalanBorc;
	}

	public void setKalanBorc(BigDecimal kalanBorc) {
		this.kalanBorc = kalanBorc;
	}

	public OdemeTipi getOdemeTipi() {
		return odemeTipi;
	}

	public void setOdemeTipi(OdemeTipi odemeTipi) {
		this.odemeTipi = odemeTipi;
	}

	public Sube getOdemeSube() {
		return odemeSube;
	}

	public void setOdemeSube(Sube odemeSube) {
		this.odemeSube = odemeSube;
	}

	public Sube getUstleniciSube() {
		return ustleniciSube;
	}

	public void setUstleniciSube(Sube ustleniciSube) {
		this.ustleniciSube = ustleniciSube;
	}

	public Musteri getMusteri() {
		return musteri;
	}

	public void setMusteri(Musteri musteri) {
		this.musteri = musteri;
	}

}