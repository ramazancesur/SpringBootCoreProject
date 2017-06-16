package com.stok.ramazan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by LocalAdmin on 13.06.2017.
 */

@Entity
@Table(name = "MESSAGE_LOG")
public class MessageLog extends BaseEntity {
  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;
  @Column(name = "MESSAGE_CONTENT")
  private String messageContent;
  @Column(name = "MUSTERI_AD_SOYAD")
  private String musteriAdSoyad;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getMessageContent() {
    return messageContent;
  }

  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }

  public String getMusteriAdSoyad() {
    return musteriAdSoyad;
  }

  public void setMusteriAdSoyad(String musteriAdSoyad) {
    this.musteriAdSoyad = musteriAdSoyad;
  }
}