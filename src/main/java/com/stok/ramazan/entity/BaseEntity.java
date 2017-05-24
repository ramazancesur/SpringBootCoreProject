package com.stok.ramazan.entity;

import com.stok.ramazan.helper.EnumUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Column(name = "OID", unique = true)
    private String oid;
    @Version
    @Column(name = "LAST_UPDATED_VERSION")
    private Long lastUpdated;
    @Column(name = "CREAYED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    @Column(name = "DURUM")
    private EnumUtil.EntityState entityState;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

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
}