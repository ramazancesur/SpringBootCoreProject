package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.dto.FaturaDTO;
import com.stok.ramazan.entity.Fatura;

import java.util.List;

/**
 * Created by LocalAdmin on 02.06.2017.
 */
public interface IFaturaService extends IGenericService<Fatura, Long> {
    List<FaturaDTO> getAllFatura();
    FaturaDTO getFatura(Long faturaOid);
    boolean addFatura(FaturaDTO faturaDTO);
    boolean updateFatura(FaturaDTO faturaDTO);
    boolean removeFatura(Long faturaOid);
}