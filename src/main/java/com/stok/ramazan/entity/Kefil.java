package com.stok.ramazan.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KEFIL")
public class Kefil extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "email", unique = true, nullable = true)
	private String userName;
	@Column(name = "SOYADI")
	private String soyadi;
	@Column(name = "ADI")
	private String adi;
	@OneToMany
	@JoinColumn(name = "KEFIL_CONTACT")
	private List<Conduct> lstConduct;
	@OneToMany
	@JoinColumn(name = "KEFIL_ADRES")
	private List<Address> lstAddress;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSoyadi() {
		return soyadi;
	}

	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public List<Conduct> getLstConduct() {
		return lstConduct;
	}

	public void setLstConduct(List<Conduct> lstConduct) {
		this.lstConduct = lstConduct;
	}

	public List<Address> getLstAddress() {
		return lstAddress;
	}

	public void setLstAddress(List<Address> lstAddress) {
		this.lstAddress = lstAddress;
	}
}