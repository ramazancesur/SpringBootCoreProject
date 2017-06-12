package com.stok.ramazan.service;

import com.stok.ramazan.dao.LisansDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.ILisansDao;
import com.stok.ramazan.entity.Lisans;
import com.stok.ramazan.service.interfaces.ILisansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by LocalAdmin on 12.06.2017.
 */
@Service
public class LisansService extends GenericServiceImpl<Lisans, Long> implements ILisansService {
    private ILisansDao lisansDao;

    @Autowired
    public LisansService(@Qualifier("lisansDao") GenericDao<Lisans, Long> genericDao) {
        super(genericDao);
        this.lisansDao = (LisansDao) genericDao;
    }
}
