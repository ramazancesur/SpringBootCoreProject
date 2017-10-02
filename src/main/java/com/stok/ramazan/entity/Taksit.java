package com.stok.ramazan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stok.ramazan.helper.CustomerDateAndTimeDeserialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TAKSIT")
public class Taksit extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "TAKSIT_NO")
    private int taksitNo;
    @Column(name = "TAKSIT_TUTARI")
    private BigDecimal taksitTutari;
    @Column(name = "BEKLENEN_ODEME_TARIHI")
    @JsonIgnore
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date beklenenOdemeTarihi;
    @ManyToOne
    @JoinColumn(name = "BORC")
    private Borc borc;

    public int getTaksitNo() {
        return taksitNo;
    }

    public void setTaksitNo(int taksitNo) {
        this.taksitNo = taksitNo;
    }

    public BigDecimal getTaksitTutari() {
        return taksitTutari;
    }

    public void setTaksitTutari(BigDecimal taksitTutari) {
        this.taksitTutari = taksitTutari;
    }

    public Date getBeklenenOdemeTarihi() {
        return beklenenOdemeTarihi;
    }

    public void setBeklenenOdemeTarihi(Date beklenenOdemeTarihi) {
        this.beklenenOdemeTarihi = beklenenOdemeTarihi;
    }

    public Borc getBorc() {
        return borc;
    }

    public void setBorc(Borc borc) {
        this.borc = borc;
    }

}