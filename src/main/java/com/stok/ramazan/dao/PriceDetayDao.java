package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IPriceDetayDao;
import com.stok.ramazan.entity.PriceDetay;

@Repository("priceDetayDao")
public class PriceDetayDao extends GenericDaoImpl<PriceDetay, Long> implements IPriceDetayDao {

}
