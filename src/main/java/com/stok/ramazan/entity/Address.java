package com.stok.ramazan.entity;

import com.stok.ramazan.helper.EnumUtil.AddresTipi;
import com.stok.ramazan.helper.EnumUtil.AdresKullaniciTipi;

import javax.persistence.*;

@Entity
@Table(name = "ADRESSSS")
public class Address extends BaseEntity {

    @Column(name = "ADRES_KULLANICI_TIPI")
    private AdresKullaniciTipi adresKullaniciTipi;
    @Column(name = "ADRES")
    private String adres;
    @Column(name = "ADRES_TIPI")
    private AddresTipi adresTipi;
    @ManyToOne
    @JoinColumn(name = "REGAIN")
    private Regain regain;

    public AdresKullaniciTipi getAdresKullaniciTipi() {
        return adresKullaniciTipi;
    }

    public void setAdresKullaniciTipi(AdresKullaniciTipi adresKullaniciTipi) {
        this.adresKullaniciTipi = adresKullaniciTipi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public AddresTipi getAdresTipi() {
        return adresTipi;
    }

    public void setAdresTipi(AddresTipi adresTipi) {
        this.adresTipi = adresTipi;
    }

    public Regain getRegain() {
        return regain;
    }

    public void setRegain(Regain regain) {
        this.regain = regain;
    }

}