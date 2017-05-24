package com.stok.ramazan.service;

import com.stok.ramazan.dao.RegainDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IRegainDao;
import com.stok.ramazan.entity.Regain;
import com.stok.ramazan.service.interfaces.IRegainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegainService extends GenericServiceImpl<Regain, Long> implements IRegainService {
    private IRegainDao regionDao;

    public RegainService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public RegainService(@Qualifier("regainDao") GenericDao<Regain, Long> genericDao) {
        super(genericDao);
        this.regionDao = (RegainDao) genericDao;
    }
}