package com.stok.ramazan.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stok.ramazan.helper.EnumUtil.OdemeTipi;

@Entity
@Table(name = "PAYMENT")
public class Payment extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private BigDecimal OdemeTutari;
	@Column(name = "ODEME_TIPI")
	private OdemeTipi odemeTipi;
	@Column(name = "BEKLENEN_ODEME_TARIHI")
	private Date beklenenOdemeTarihi;
	@Column(name = "GERCEKLESEN_ODEME_TARIHI")
	private Date gerceklesenOdemeTarihi;
	@ManyToOne
	@JoinColumn(name = "SATICI_SUBE")
	private Sube saticiSube;
	@ManyToOne
	@JoinColumn(name = "USTLENICI_SUBE")
	private Sube ustleniciSube;
	@ManyToOne
	@JoinColumn(name = "MUSTERI")
	private Musteri musteri;

	public BigDecimal getOdemeTutari() {
		return OdemeTutari;
	}

	public void setOdemeTutari(BigDecimal odemeTutari) {
		OdemeTutari = odemeTutari;
	}

	public OdemeTipi getOdemeTipi() {
		return odemeTipi;
	}

	public void setOdemeTipi(OdemeTipi odemeTipi) {
		this.odemeTipi = odemeTipi;
	}

	public Sube getSaticiSube() {
		return saticiSube;
	}

	public void setSaticiSube(Sube saticiSube) {
		this.saticiSube = saticiSube;
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
