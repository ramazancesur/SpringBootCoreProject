package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.dto.MusteriDTO;
import com.stok.ramazan.entity.Musteri;

import java.util.List;

public interface IMusteriService extends IGenericService<Musteri, Long> {
    List<MusteriDTO> getAllMusteriDTO();

    boolean deleteMusteriDto(Long oid);

    MusteriDTO getMusteriDTO(Long oid);

    MusteriDTO addMusteriDTO(MusteriDTO musteriDTO);

    MusteriDTO updateMusteriDTO(MusteriDTO musteriDTO);
}