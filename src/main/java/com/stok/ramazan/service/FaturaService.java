package com.stok.ramazan.service;

import com.stok.ramazan.dao.FaturaDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IFaturaDao;
import com.stok.ramazan.entity.Fatura;
import com.stok.ramazan.service.interfaces.IFaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by LocalAdmin on 02.06.2017.
 */
@Service
public class FaturaService extends GenericServiceImpl<Fatura, Long>
        implements IFaturaService {
    private IFaturaDao faturaDao;

    @Autowired
    public FaturaService(@Qualifier("faturaDao") GenericDao<Fatura, Long> genericDao) {
        super(genericDao);
        this.faturaDao = (FaturaDao) genericDao;
    }

}