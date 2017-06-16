package com.stok.ramazan.service;

import com.stok.ramazan.dao.StokDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IStokDao;
import com.stok.ramazan.entity.Stok;
import com.stok.ramazan.service.interfaces.IStokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StokService extends GenericServiceImpl<Stok, Long> implements IStokService {
  private IStokDao stokDao;

  public StokService() {
    // TODO Auto-generated constructor stub
  }

  @Autowired
  public StokService(@Qualifier("stokDao") GenericDao<Stok, Long> genericDao) {
    super(genericDao);
    this.stokDao = (StokDao) genericDao;
  }
}
