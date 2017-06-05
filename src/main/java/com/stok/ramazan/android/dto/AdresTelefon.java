package com.stok.ramazan.android.dto;

import com.stok.ramazan.helper.EnumUtil;

/**
 * Created by LocalAdmin on 25.05.2017.
 */
public class AdresTelefon {
    private EnumUtil.AddresTipi addresTipi;
    private String deger;
    private EnumUtil.ContactTipi telTipi;
    private EnumUtil.TelOrAddres telOrAddres;
    private Long oid;

    public EnumUtil.AddresTipi getAddresTipi() {
        return addresTipi;
    }

    public void setAddresTipi(EnumUtil.AddresTipi addresTipi) {
        this.addresTipi = addresTipi;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public EnumUtil.ContactTipi getTelTipi() {
        return telTipi;
    }

    public void setTelTipi(EnumUtil.ContactTipi telTipi) {
        this.telTipi = telTipi;
    }

    public EnumUtil.TelOrAddres getTelOrAddres() {
        return telOrAddres;
    }

    public void setTelOrAddres(EnumUtil.TelOrAddres telOrAddres) {
        this.telOrAddres = telOrAddres;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }
}