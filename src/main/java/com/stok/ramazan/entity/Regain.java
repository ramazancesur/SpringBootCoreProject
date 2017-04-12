package com.stok.ramazan.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "REGAIN")
public class Regain extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ADI")
	private String regainAdi;
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "regainIlce", nullable = true)
	private List<Ilce> lstIlce;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "regainIl", nullable = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Il> lstIl;

	public String getRegainAdi() {
		return regainAdi;
	}

	public void setRegainAdi(String regainAdi) {
		this.regainAdi = regainAdi;
	}

	public List<Ilce> getLstIlce() {
		return lstIlce;
	}

	public void setLstIlce(List<Ilce> lstIlce) {
		this.lstIlce = lstIlce;
	}

	public List<Il> getLstIl() {
		return lstIl;
	}

	public void setLstIl(List<Il> lstIl) {
		this.lstIl = lstIl;
	}
}