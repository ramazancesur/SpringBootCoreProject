package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.dto.SiparisListesiDTO;
import com.stok.ramazan.entity.Borc;

import java.util.List;

public interface IBorcService extends IGenericService<Borc, Long> {
    List<SiparisListesiDTO> getAllSiparis();

    SiparisListesiDTO getSiparis(Long siparisListesiOid);

    boolean addSiparis(SiparisListesiDTO siparisListesiDTO);

    boolean updateSiparis(SiparisListesiDTO siparisListesiDTO);

    boolean deleteSiparis(Long siparisListesiOid);
}