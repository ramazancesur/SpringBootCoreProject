package com.stok.ramazan.android.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable {
    @JsonFormat(pattern = "MMMM d, yyyy HH:mm:ss a")
    private Date createdDate;
    @JsonFormat(pattern = "MMMM d, yyyy HH:mm:ss a")
    private Date updatedDate;
    private Long oid;

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

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }
}