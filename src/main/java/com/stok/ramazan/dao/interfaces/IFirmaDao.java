package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Lisans;

import java.util.List;

public interface IFirmaDao extends GenericDao<Firma, Long> {
    Lisans getAllActiveLisans(Long firmaOid);

    Firma getFirma(String sirketAdi, String sirketLogoYol);

    List<Firma> getAllEssentialFirm();

    Firma getFirma(String userName);
}