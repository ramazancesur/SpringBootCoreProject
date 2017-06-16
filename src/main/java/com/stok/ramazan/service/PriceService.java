package com.stok.ramazan.service;

import com.stok.ramazan.dao.PriceDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IPriceDao;
import com.stok.ramazan.entity.Price;
import com.stok.ramazan.service.interfaces.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PriceService extends GenericServiceImpl<Price, Long> implements IPriceService {
  private IPriceDao priceDao;

  public PriceService() {
    // TODO Auto-generated constructor stub
  }

  @Autowired
  public PriceService(@Qualifier("priceDao") GenericDao<Price, Long> genericDao) {
    super(genericDao);
    this.priceDao = (PriceDao) genericDao;
  }

}
