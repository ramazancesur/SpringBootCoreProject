package com.stok.ramazan.pojo;

/**
 * Created by LocalAdmin on 23.05.2017.
 */
public class Siparis extends BaseDTO {
    private UrunDTO urunDTO;
    private int metre;
    private SiparisListesiDTO siparisListesiDTO;

    public UrunDTO getUrunDTO() {
        return urunDTO;
    }

    public void setUrunDTO(UrunDTO urunDTO) {
        this.urunDTO = urunDTO;
    }

    public int getMetre() {
        return metre;
    }

    public void setMetre(int metre) {
        this.metre = metre;
    }

    public SiparisListesiDTO getSiparisListesiDTO() {
        return siparisListesiDTO;
    }

    public void setSiparisListesiDTO(SiparisListesiDTO siparisListesiDTO) {
        this.siparisListesiDTO = siparisListesiDTO;
    }
}
