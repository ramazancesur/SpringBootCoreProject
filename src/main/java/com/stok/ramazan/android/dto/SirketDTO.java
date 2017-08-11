package com.stok.ramazan.android.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Ramazan CESUR on 12.06.2017.
 */
public class SirketDTO extends BaseDTO {
  private String sirketAdi;
  private String logoPath;
  private String sirketLisansKey;
  private List<AdresTelefon> lstAdresTel;
  private Long userOid;
  // 06.08.2017 tarihinde eklendi
  private String encodedImages;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  private Date lisansEndTimes;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  private Date lisansStartTimes;
  private Integer kalanSms;

  private String androidLogoPath;
  private Boolean changeImage;


  public String getSirketAdi() {
    return sirketAdi;
  }

  public void setSirketAdi(String sirketAdi) {
    this.sirketAdi = sirketAdi;
  }

  public String getLogoPath() {
    return logoPath;
  }

  public void setLogoPath(String logoPath) {
    this.logoPath = logoPath;
  }

  public String getSirketLisansKey() {
    return sirketLisansKey;
  }

  public void setSirketLisansKey(String sirketLisansKey) {
    this.sirketLisansKey = sirketLisansKey;
  }

  public List<AdresTelefon> getLstAdresTel() {
    return lstAdresTel;
  }

  public void setLstAdresTel(List<AdresTelefon> lstAdresTel) {
    this.lstAdresTel = lstAdresTel;
  }

  public Long getUserOid() {
    return userOid;
  }

  public void setUserOid(Long userOid) {
    this.userOid = userOid;
  }

  public String getEncodedImages() {
    return encodedImages;
  }

  public void setEncodedImages(String encodedImages) {
    this.encodedImages = encodedImages;
  }

  public Date getLisansEndTimes() {
    return lisansEndTimes;
  }

  public void setLisansEndTimes(Date lisansEndTimes) {
    this.lisansEndTimes = lisansEndTimes;
  }

  public Date getLisansStartTimes() {
    return lisansStartTimes;
  }

  public void setLisansStartTimes(Date lisansStartTimes) {
    this.lisansStartTimes = lisansStartTimes;
  }

  public Integer getKalanSms() {
    return kalanSms;
  }

  public void setKalanSms(Integer kalanSms) {
    this.kalanSms = kalanSms;
  }

  public String getAndroidLogoPath() {
    return androidLogoPath;
  }

  public void setAndroidLogoPath(String androidLogoPath) {
    this.androidLogoPath = androidLogoPath;
  }

  public boolean isChangeImage() {
    return changeImage;
  }

  public void setChangeImage(boolean changeImage) {
    this.changeImage = changeImage;
  }
}