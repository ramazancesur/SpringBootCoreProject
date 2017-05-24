package com.stok.ramazan.service;

import com.stok.ramazan.dao.FirmaDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.service.interfaces.IFirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FirmaService extends GenericServiceImpl<Firma, Long> implements IFirmaService {
    private FirmaDao firmaDao;

    public FirmaService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public FirmaService(@Qualifier("firmaDao") GenericDao<Firma, Long> genericDao) {
        super(genericDao);
        this.firmaDao = (FirmaDao) genericDao;
    }

}
