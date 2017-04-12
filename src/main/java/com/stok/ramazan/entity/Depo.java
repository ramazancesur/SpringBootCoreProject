package com.stok.ramazan.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEPO")
public class Depo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ADI")
	private String depoAdi;
	@ManyToOne
	@JoinColumn(name = "DEPO_FIRM")
	private Firma firma;
	@OneToOne
	@JoinColumn(name = "DEPO_ADR")
	private Address adress;
	@OneToMany
	@JoinColumn(name = "DEPO_CONT")
	private List<Conduct> lstContact;

	public String getDepoAdi() {
		return depoAdi;
	}

	public void setDepoAdi(String depoAdi) {
		this.depoAdi = depoAdi;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public List<Conduct> getLstContact() {
		return lstContact;
	}

	public void setLstContact(List<Conduct> lstContact) {
		this.lstContact = lstContact;
	}
}
