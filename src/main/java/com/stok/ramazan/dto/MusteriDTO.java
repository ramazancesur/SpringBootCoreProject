package com.stok.ramazan.dto;

public class MusteriDTO extends BaseDTO {
    private String ad;
    private String soyad;
    private String telefon1;
    private String telefon2;
    private String adress;
    private Double toplamBorc;
    private String lisansKey;

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

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon1) {
        this.telefon1 = telefon1;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getToplamBorc() {
        return toplamBorc;
    }

    public void setToplamBorc(Double toplamBorc) {
        this.toplamBorc = toplamBorc;
    }

    public String getLisansKey() {
        return lisansKey;
    }

    public void setLisansKey(String lisansKey) {
        this.lisansKey = lisansKey;
    }
}