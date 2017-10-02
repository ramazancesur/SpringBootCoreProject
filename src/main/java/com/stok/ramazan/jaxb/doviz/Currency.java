package com.stok.ramazan.jaxb.doviz;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {
    @XmlAttribute(name = "Kod")
    private String kod;
    @XmlElement(name = "Unit")
    private int unit;
    @XmlElement(name = "Isim")
    private String isim;
    @XmlElement(name = "CurrencyName")
    private String currencyName;
    @XmlElement(name = "ForexBuying")
    private double alisFiyati;
    @XmlElement(name = "ForexSelling")
    private double satisFiyati;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getAlisFiyati() {
        return alisFiyati;
    }

    public void setAlisFiyati(double alisFiyati) {
        this.alisFiyati = alisFiyati;
    }

    public double getSatisFiyati() {
        return satisFiyati;
    }

    public void setSatisFiyati(double satisFiyati) {
        this.satisFiyati = satisFiyati;
    }


}