package com.stok.ramazan.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="ORDER")
public class Order extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="TOPLAM_TUTAR")
	private BigDecimal toplamTutar;
	@Column(name="HEDEFLENEN_TARIH")
	private Date hedeflenenTarih;
	@ManyToOne
	@JoinColumn(name="SATIS_SUBESI")
	private Sube satisSubesi;
	@ManyToOne
	@JoinColumn(name="MUSTERI")
	private Musteri musteri;
	@ManyToOne
	@JoinColumn(name="EMPLOYEE")
	private Employee employee;
	public BigDecimal getToplamTutar() {
		return toplamTutar;
	}
	public void setToplamTutar(BigDecimal toplamTutar) {
		this.toplamTutar = toplamTutar;
	}
	public Date getHedeflenenTarih() {
		return hedeflenenTarih;
	}
	public void setHedeflenenTarih(Date hedeflenenTarih) {
		this.hedeflenenTarih = hedeflenenTarih;
	}
	public Sube getSatisSubesi() {
		return satisSubesi;
	}
	public void setSatisSubesi(Sube satisSubesi) {
		this.satisSubesi = satisSubesi;
	}
	public Musteri getMusteri() {
		return musteri;
	}
	public void setMusteri(Musteri musteri) {
		this.musteri = musteri;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
