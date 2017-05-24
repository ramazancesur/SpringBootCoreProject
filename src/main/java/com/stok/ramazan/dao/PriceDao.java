package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IPriceDao;
import com.stok.ramazan.entity.Price;
import org.springframework.stereotype.Repository;

@Repository("priceDao")
public class PriceDao extends GenericDaoImpl<Price, Long> implements IPriceDao {

}
