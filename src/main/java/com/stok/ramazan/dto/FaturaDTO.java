package com.stok.ramazan.dto;

public class FaturaDTO extends BaseDTO {
    private String sirketAdi;
    private double toplamTutar;
    private String sirketLogoYolu;
    private String musteriAdresi;
    private SiparisListesiDTO siparisListesi;

    public String getSirketAdi() {
        return sirketAdi;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }

    public double getToplamTutar() {
        return toplamTutar;
    }

    public void setToplamTutar(double toplamTutar) {
        this.toplamTutar = toplamTutar;
    }

    public String getSirketLogoYolu() {
        return sirketLogoYolu;
    }

    public void setSirketLogoYolu(String sirketLogoYolu) {
        this.sirketLogoYolu = sirketLogoYolu;
    }

    public String getMusteriAdresi() {
        return musteriAdresi;
    }

    public void setMusteriAdresi(String musteriAdresi) {
        this.musteriAdresi = musteriAdresi;
    }

    public SiparisListesiDTO getSiparisListesi() {
        return siparisListesi;
    }

    public void setSiparisListesi(SiparisListesiDTO siparisListesi) {
        this.siparisListesi = siparisListesi;
    }
}