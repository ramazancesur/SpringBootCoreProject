package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IPriceDao;
import com.stok.ramazan.entity.Price;

@Repository("priceDao")
public class PriceDao extends GenericDaoImpl<Price, Long> implements IPriceDao {

}
