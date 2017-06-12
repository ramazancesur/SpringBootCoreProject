package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.android.dto.SirketDTO;
import com.stok.ramazan.entity.Firma;

import java.util.List;


public interface IFirmaService extends IGenericService<Firma, Long> {
    List<SirketDTO> getAllSirket();

    SirketDTO getSirket(Long sirketOid);

    boolean addSirket(SirketDTO sirketDTO);

    boolean updateSirket(SirketDTO sirketDTO);

    boolean deleteSirket(Long sirketOid);
}