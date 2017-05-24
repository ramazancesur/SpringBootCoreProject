package com.stok.ramazan.entity;

import javax.persistence.*;

@Entity
@Table(name = "FIRMA")
public class Firma extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "FIRM_ADI")
    private String firmaAdi;

    @OneToOne
    @JoinColumn(name = "FIRM_USER")
    private User user;

    @OneToOne
    @JoinColumn(name = "USER_ADRES")
    private Address adress;

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

}