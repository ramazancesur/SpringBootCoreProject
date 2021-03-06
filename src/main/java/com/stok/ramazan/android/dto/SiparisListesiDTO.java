package com.stok.ramazan.android.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stok.ramazan.helper.CustomerDateAndTimeDeserialize;
import com.stok.ramazan.helper.EnumUtil;

import java.util.Date;
import java.util.List;

public class SiparisListesiDTO extends BaseDTO {
    private MusteriDTO musteri;
    private double siparisBorcuToplami;
    private String musteriNotu;
    private String saticiNotu;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date beklenenTeslimatTarihi;
    private List<SiparisDTO> lstSiparisDTOS;
    private EnumUtil.SiparisDurum siparisDurum;
    // Bu senet için kalan borcu temsil eder
    private double kalanBorc;


    // Ödeme için eklenen Alanlar
    private double sonOdenenTutar;
    private boolean sonGuncelemeOdeme;

    public SiparisListesiDTO() {
    }

    public MusteriDTO getMusteri() {
        return musteri;
    }

    public void setMusteri(MusteriDTO musteri) {
        this.musteri = musteri;
    }

    public double getSiparisBorcuToplami() {
        return siparisBorcuToplami;
    }

    public void setSiparisBorcuToplami(double siparisBorcuToplami) {
        this.siparisBorcuToplami = siparisBorcuToplami;
    }

    public String getMusteriNotu() {
        return musteriNotu;
    }

    public void setMusteriNotu(String musteriNotu) {
        this.musteriNotu = musteriNotu;
    }

    public String getSaticiNotu() {
        return saticiNotu;
    }

    public void setSaticiNotu(String saticiNotu) {
        this.saticiNotu = saticiNotu;
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

    public EnumUtil.SiparisDurum getSiparisDurum() {
        return siparisDurum;
    }

    public void setSiparisDurum(EnumUtil.SiparisDurum siparisDurum) {
        this.siparisDurum = siparisDurum;
    }

    public double getKalanBorc() {
        return kalanBorc;
    }

    public void setKalanBorc(double kalanBorc) {
        this.kalanBorc = kalanBorc;
    }

    public double getSonOdenenTutar() {
        return sonOdenenTutar;
    }

    public void setSonOdenenTutar(double sonOdenenTutar) {
        this.sonOdenenTutar = sonOdenenTutar;
    }

    public boolean isSonGuncelemeOdeme() {
        return sonGuncelemeOdeme;
    }

    public void setSonGuncelemeOdeme(boolean sonGuncelemeOdeme) {
        this.sonGuncelemeOdeme = sonGuncelemeOdeme;
    }
}