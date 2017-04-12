package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.stok.ramazan.helper.EnumUtil.ContactTipi;

@Entity
@Table(name = "CONDUCT")
public class Conduct extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "CONDUCT_TIPI")
	private ContactTipi contactType;

	@Column(name = "DEGER")
	private String value;

	public ContactTipi getContactType() {
		return contactType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setContactType(ContactTipi contactType) {
		this.contactType = contactType;
	}

}
