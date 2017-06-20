package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Borc;

import java.util.List;

public interface IBorcDao extends GenericDao<Borc, Long> {
    Double getToplamBorcByMusteriOid(Long musteriOid);
    List<Borc> getAllBorcByAuthenticated();
}