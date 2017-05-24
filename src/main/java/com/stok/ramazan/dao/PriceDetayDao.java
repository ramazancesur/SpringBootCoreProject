package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IPriceDetayDao;
import com.stok.ramazan.entity.PriceDetay;
import org.springframework.stereotype.Repository;

@Repository("priceDetayDao")
public class PriceDetayDao extends GenericDaoImpl<PriceDetay, Long> implements IPriceDetayDao {

}
