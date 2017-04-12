package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.stok.ramazan.helper.EnumUtil.AddresTipi;
import com.stok.ramazan.helper.EnumUtil.AdresKullaniciTipi;

@Entity
@Table(name = "ADRESS")
public class Address extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ADRES_KULLANICI_TIPI")
	private AdresKullaniciTipi adresKullaniciTipi;

	@Column(name = "ADRES")
	private String adres;
	@Column(name = "ADRES_TIPI")
	private AddresTipi adresTipi;
}