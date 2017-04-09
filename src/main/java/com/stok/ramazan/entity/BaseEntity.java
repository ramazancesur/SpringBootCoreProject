package com.stok.ramazan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.stok.ramazan.helper.EnumUtil;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// Entity kısmı
	@Version
	@Column(name="LAST_UPDATED_VERSION")
	private Long lastUpdated;

	@Column(name = "CREAYED_DATE")
	private Date createdDate;
 
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "DURUM")
	private EnumUtil.EntityState entityState;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
