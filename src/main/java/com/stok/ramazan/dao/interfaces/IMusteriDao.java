package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.dto.MusteriDTO;
import com.stok.ramazan.entity.Musteri;

import java.util.List;

public interface IMusteriDao extends GenericDao<Musteri, Long> {
    List<MusteriDTO> getAllMusteri();
    MusteriDTO getMusteriDTO(Long musteriOid);
}