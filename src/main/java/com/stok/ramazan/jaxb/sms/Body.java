package com.stok.ramazan.jaxb.sms;

import com.stok.ramazan.settings.AdapterCDATA;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by Ramazan CESUR on 09.06.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"message", "no"})
public class Body implements Serializable {
  @XmlJavaTypeAdapter(AdapterCDATA.class)
  @XmlElement(name = "msg")
  private String message;
  @XmlElement(name = "no")
  private String no;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }
}