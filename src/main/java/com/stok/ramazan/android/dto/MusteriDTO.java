package com.stok.ramazan.android.dto;


import java.util.List;

public class MusteriDTO extends BaseDTO {
    private String ad;
    private String soyad;
    private List<AdresTelefon> lstAdresTel;
    // Toplam Harcama - Toplam Ödeme
    private Double toplamBorc;

    private Long firmMusteriOid;

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

    public List<AdresTelefon> getLstAdresTel() {
        return lstAdresTel;
    }

    public void setLstAdresTel(List<AdresTelefon> lstAdresTel) {
        this.lstAdresTel = lstAdresTel;
    }

    public Double getToplamBorc() {
        return toplamBorc;
    }

    public void setToplamBorc(Double toplamBorc) {
        this.toplamBorc = toplamBorc;
    }

    public Long getFirmMusteriOid() {
        return firmMusteriOid;
    }

    public void setFirmMusteriOid(Long firmMusteriOid) {
        this.firmMusteriOid = firmMusteriOid;
    }
}