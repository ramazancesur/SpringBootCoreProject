package com.stok.ramazan.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.stok.ramazan.helper.EnumUtil.MusteriTipi;
@Entity
@Table(name="MUSTERI")
public class Musteri extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="email",unique=true,nullable=true)
	private String userName;
	@Column(name="SOYADI")
	private String soyadi;
	@Column(name="ADI")
	private String adi;
	@Column(name="MUSTERI_TIPI")
	private MusteriTipi musteriTipi;
	@OneToMany
	@JoinColumn(name="MUSTERI_CONTACT")
	private List<Conduct> lstConduct;
	@OneToMany
	@JoinColumn(name="MUSTERI_ADRES")
	private List<Address> lstAddress;
	@OneToMany
	@JoinColumn(name="MUSTERİ_KEFIL")
	private List<Musteri> lstMusteriKefil;
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
	public MusteriTipi getMusteriTipi() {
		return musteriTipi;
	}
	public void setMusteriTipi(MusteriTipi musteriTipi) {
		this.musteriTipi = musteriTipi;
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
	public List<Musteri> getLstMusteriKefil() {
		return lstMusteriKefil;
	}
	public void setLstMusteriKefil(List<Musteri> lstMusteriKefil) {
		this.lstMusteriKefil = lstMusteriKefil;
	}
}
