package com.stok.ramazan.pojo;

import com.stok.ramazan.helper.EnumUtil;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date createdDate;
	private Date updatedDate;
	private EnumUtil.EntityState entityState;
	private Integer version;
	private String oid;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public EnumUtil.EntityState getEntityState() {
		return entityState;
	}

	public void setEntityState(EnumUtil.EntityState entityState) {
		this.entityState = entityState;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}