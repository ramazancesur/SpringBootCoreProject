package com.stok.ramazan.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "EMP_USER")
	private User user;
	@Column(name = "ISE_GIRIS_TARIHI")
	private Date iseGirisTarihi;
	@Column(name = "ISTEN_CIKIS_TARIHI")
	private Date istenCikisTarihi;
	@OneToOne
	@JoinColumn(name = "EMP_SUBE")
	private Sube firma;

	@OneToMany
	@JoinColumn(name = "USER_ADRES")
	private List<Address> lstAdres;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getIseGirisTarihi() {
		return iseGirisTarihi;
	}

	public void setIseGirisTarihi(Date iseGirisTarihi) {
		this.iseGirisTarihi = iseGirisTarihi;
	}

	public Date getIstenCikisTarihi() {
		return istenCikisTarihi;
	}

	public void setIstenCikisTarihi(Date istenCikisTarihi) {
		this.istenCikisTarihi = istenCikisTarihi;
	}

}
