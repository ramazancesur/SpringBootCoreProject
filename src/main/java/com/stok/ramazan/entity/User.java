package com.stok.ramazan.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.stok.ramazan.helper.EnumUtil.UserType;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "USER_NAME", unique = true)
	private String userName;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "SOYADI")
	private String soyadi;
	@Column(name = "ADI")
	private String adi;
	@Column(name = "USER_TYPE")
	private UserType userType;
	@ManyToOne
	@JoinColumn(name = "ROLE")
	private Role role;
	@OneToMany
	@JoinColumn(name = "USER_CONTACT")
	private List<Conduct> lstConduct;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Conduct> getLstConduct() {
		return lstConduct;
	}

	public void setLstConduct(List<Conduct> lstConduct) {
		this.lstConduct = lstConduct;
	}
}