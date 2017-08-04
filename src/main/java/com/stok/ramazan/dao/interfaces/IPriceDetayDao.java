package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.PriceDetay;

import java.util.List;

public interface IPriceDetayDao extends GenericDao<PriceDetay, Long> {
  List<PriceDetay> getPriceDetayByProduct(Long productOid);
}
