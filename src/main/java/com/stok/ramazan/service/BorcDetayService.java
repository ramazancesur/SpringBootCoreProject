package com.stok.ramazan.service;

import com.stok.ramazan.dao.BorcDetayDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.BorcDetay;
import com.stok.ramazan.service.interfaces.IBorcDetayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BorcDetayService extends GenericServiceImpl<BorcDetay, Long> implements IBorcDetayService {
  private BorcDetayDao borcDetayDao;

  @Autowired
  public BorcDetayService(@Qualifier("borcDetayDao") GenericDao<BorcDetay, Long> genericDao) {
    super(genericDao);
    this.borcDetayDao = (BorcDetayDao) genericDao;
  }

  public BorcDetayService() {
    // TODO Auto-generated constructor stub
  }

}
