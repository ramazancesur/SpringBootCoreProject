package com.stok.ramazan.android.dto;

import java.util.Date;
import java.util.List;

public class SiparisListesiDTO extends BaseDTO {
  private MusteriDTO musteri;
  private double toplamSiparisBorcu;
  private String musteriNotu;
  private String saticiNotu;
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
}