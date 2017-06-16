package com.stok.ramazan.service;

import com.stok.ramazan.dao.ConductDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IConductDao;
import com.stok.ramazan.entity.Conduct;
import com.stok.ramazan.service.interfaces.IConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConductService extends GenericServiceImpl<Conduct, Long> implements IConductService {
  private IConductDao conductDao;

  @Autowired
  public ConductService(@Qualifier("conductDao") GenericDao<Conduct, Long> genericDao) {
    super(genericDao);
    this.conductDao = (ConductDao) genericDao;
  }

  public ConductService() {
    // TODO Auto-generated constructor stub
  }

}
