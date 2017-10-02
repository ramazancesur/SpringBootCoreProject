package com.stok.ramazan.jaxb.doviz;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Tarih_Date")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarihXML {
    @XmlAttribute(name = "Date")
    private String formatliTarih;
    @XmlAttribute(name = "Bulten_No")
    private String bulten_No;
    @XmlElement(name = "Currency")
    private List<Currency> lstCurrency;


    public String getFormatliTarih() {
        return formatliTarih;
    }

    public void setFormatliTarih(String formatliTarih) {
        this.formatliTarih = formatliTarih;
    }

    public String getBulten_No() {
        return bulten_No;
    }

    public void setBulten_No(String bulten_No) {
        this.bulten_No = bulten_No;
    }

    public List<Currency> getLstCurrency() {
        return lstCurrency;
    }

    public void setLstCurrency(List<Currency> lstCurrency) {
        this.lstCurrency = lstCurrency;
    }

}