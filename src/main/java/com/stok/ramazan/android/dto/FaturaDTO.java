package com.stok.ramazan.android.dto;

public class FaturaDTO extends BaseDTO {
    private String sirketAdi;
    private String sirketLogoYolu;
    private MusteriDTO musteri;
    private SiparisListesiDTO siparisListesi;

    public String getSirketAdi() {
        return sirketAdi;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }

    public String getSirketLogoYolu() {
        return sirketLogoYolu;
    }

    public void setSirketLogoYolu(String sirketLogoYolu) {
        this.sirketLogoYolu = sirketLogoYolu;
    }

    public MusteriDTO getMusteri() {
        return musteri;
    }

    public void setMusteri(MusteriDTO musteri) {
        this.musteri = musteri;
    }

    public SiparisListesiDTO getSiparisListesi() {
        return siparisListesi;
    }

    public void setSiparisListesi(SiparisListesiDTO siparisListesi) {
        this.siparisListesi = siparisListesi;
    }
}