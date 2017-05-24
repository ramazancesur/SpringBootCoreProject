package com.stok.ramazan.dto;

import com.stok.ramazan.helper.EnumUtil;

import java.util.Date;

public class CalisanDTO extends BaseDTO {
    private EnumUtil.EmployeeType employeeType;
    private String adSoyad;
    private String adress;
    private String telefonNumarasi;
    private Date iseGirisTarihi;
    private String tcKimlikNumarasi;

    public EnumUtil.EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EnumUtil.EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(String telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    public Date getIseGirisTarihi() {
        return iseGirisTarihi;
    }

    public void setIseGirisTarihi(Date iseGirisTarihi) {
        this.iseGirisTarihi = iseGirisTarihi;
    }

    public String getTcKimlikNumarasi() {
        return tcKimlikNumarasi;
    }

    public void setTcKimlikNumarasi(String tcKimlikNumarasi) {
        this.tcKimlikNumarasi = tcKimlikNumarasi;
    }
}