package com.stok.ramazan.dto;

import com.stok.ramazan.helper.EnumUtil;

import java.util.Date;

public class CalisanDTO extends BaseDTO {
    private EnumUtil.EmployeeType employeeType;
    private String ad;
    private String soyad;
    private String kullaniciAdi;
    private String sifre;
    private String adress;
    private String telefonNumarasi;
    private Date iseGirisTarihi;

    public EnumUtil.EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EnumUtil.EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
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

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}