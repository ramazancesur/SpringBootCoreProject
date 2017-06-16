package com.stok.ramazan.service;

import com.stok.ramazan.dao.IlceDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IIlceDao;
import com.stok.ramazan.entity.Ilce;
import com.stok.ramazan.service.interfaces.IIlceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IlceService extends GenericServiceImpl<Ilce, Long> implements IIlceService {
  private IIlceDao ilceDao;

  @Autowired
  public IlceService(@Qualifier("ilceDao") GenericDao<Ilce, Long> genericDao) {
    super(genericDao);
    this.ilceDao = (IlceDao) genericDao;
  }

  public IlceService() {
    // TODO Auto-generated constructor stub
  }
}
