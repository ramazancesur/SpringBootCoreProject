package com.stok.ramazan.service;

import com.stok.ramazan.dao.DepoDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.Depo;
import com.stok.ramazan.service.interfaces.IDepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DepoService extends GenericServiceImpl<Depo, Long> implements IDepoService {
  private DepoDao depoDao;

  @Autowired
  public DepoService(@Qualifier("depoDao") GenericDao<Depo, Long> genericDao) {
    super(genericDao);
    this.depoDao = (DepoDao) genericDao;
  }

  public DepoService() {
    // TODO Auto-generated constructor stub
  }
}
