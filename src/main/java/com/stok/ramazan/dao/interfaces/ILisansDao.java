package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Lisans;

import java.util.List;

/**
 * Created by LocalAdmin on 12.06.2017.
 */
public interface ILisansDao extends GenericDao<Lisans, Long> {
    List<Lisans> getLisansListbyFirmID(Long firmOid);
}
