package com.stok.ramazan.jaxb.sms;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by LocalAdmin on 09.06.2017.
 */
@XmlRootElement
@XmlType(propOrder = {"company", "userCode", "password", "startDate", "stopDate", "type", "messageHeader"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Header implements Serializable {
    @XmlElement(name = "company")
    private String company;
    @XmlElement(name = "usercode")
    private String userCode;
    @XmlElement(name = "password")
    private String password;
    @XmlElement(name = "startdate")
    private Date startDate;
    @XmlElement(name = "stopdate")
    private Date stopDate;
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "msgheader")
    private String messageHeader;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }
}