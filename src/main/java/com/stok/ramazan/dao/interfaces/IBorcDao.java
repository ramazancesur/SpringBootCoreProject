package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Borc;

public interface IBorcDao extends GenericDao<Borc, Long> {
    Double getToplamBorcByMusteriOid(Long musteriOid);
}