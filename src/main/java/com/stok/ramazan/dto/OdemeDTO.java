package com.stok.ramazan.dto;

import java.util.Date;

/**
 * Created by LocalAdmin on 24.05.2017.
 */
public class OdemeDTO extends BaseDTO {
    private MusteriDTO musteriDTO;
    private Date odemeTarihi;
    private Double odemeMiktari;

    public MusteriDTO getMusteriDTO() {
        return musteriDTO;
    }

    public void setMusteriDTO(MusteriDTO musteriDTO) {
        this.musteriDTO = musteriDTO;
    }

    public Date getOdemeTarihi() {
        return odemeTarihi;
    }

    public void setOdemeTarihi(Date odemeTarihi) {
        this.odemeTarihi = odemeTarihi;
    }

    public Double getOdemeMiktari() {
        return odemeMiktari;
    }

    public void setOdemeMiktari(Double odemeMiktari) {
        this.odemeMiktari = odemeMiktari;
    }
}