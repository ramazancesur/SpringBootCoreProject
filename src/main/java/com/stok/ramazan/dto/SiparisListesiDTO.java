package com.stok.ramazan.dto;

import java.util.Date;
import java.util.List;

public class SiparisListesiDTO extends BaseDTO {
    private MusteriDTO musteri;
    private double toplamSiparisBorcu;
    private String musteriSiparisNotuy;
    private String satisSiparisNotu;
    private Date beklenenTeslimatTarihi;
    private List<SiparisDTO> lstSiparisDTOS;

    public MusteriDTO getMusteri() {
        return musteri;
    }

    public void setMusteri(MusteriDTO musteri) {
        this.musteri = musteri;
    }

    public double getToplamSiparisBorcu() {
        return toplamSiparisBorcu;
    }

    public void setToplamSiparisBorcu(double toplamSiparisBorcu) {
        this.toplamSiparisBorcu = toplamSiparisBorcu;
    }

    public String getMusteriSiparisNotuy() {
        return musteriSiparisNotuy;
    }

    public void setMusteriSiparisNotuy(String musteriSiparisNotuy) {
        this.musteriSiparisNotuy = musteriSiparisNotuy;
    }

    public String getSatisSiparisNotu() {
        return satisSiparisNotu;
    }

    public void setSatisSiparisNotu(String satisSiparisNotu) {
        this.satisSiparisNotu = satisSiparisNotu;
    }

    public Date getBeklenenTeslimatTarihi() {
        return beklenenTeslimatTarihi;
    }

    public void setBeklenenTeslimatTarihi(Date beklenenTeslimatTarihi) {
        this.beklenenTeslimatTarihi = beklenenTeslimatTarihi;
    }

    public List<SiparisDTO> getLstSiparisDTOS() {
        return lstSiparisDTOS;
    }

    public void setLstSiparisDTOS(List<SiparisDTO> lstSiparisDTOS) {
        this.lstSiparisDTOS = lstSiparisDTOS;
    }
}