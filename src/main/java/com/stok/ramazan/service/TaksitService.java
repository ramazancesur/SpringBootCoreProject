package com.stok.ramazan.service;

import com.stok.ramazan.dao.TaksitDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.ITaksitDao;
import com.stok.ramazan.entity.Taksit;
import com.stok.ramazan.service.interfaces.ITaksitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TaksitService extends GenericServiceImpl<Taksit, Long> implements ITaksitService {
    private ITaksitDao taksitDao;

    public TaksitService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public TaksitService(@Qualifier("taksitDao") GenericDao<Taksit, Long> genericDao) {
        super(genericDao);
        this.taksitDao = (TaksitDao) genericDao;
    }
}
