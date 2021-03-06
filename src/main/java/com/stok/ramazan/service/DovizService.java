package com.stok.ramazan.service;

import com.stok.ramazan.dao.DovizDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IDovizDao;
import com.stok.ramazan.entity.Doviz;
import com.stok.ramazan.service.interfaces.IDovizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DovizService extends GenericServiceImpl<Doviz, Long> implements IDovizService {
    private IDovizDao dovizDao;

    public DovizService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public DovizService(@Qualifier("dovizDao") GenericDao<Doviz, Long> genericDao) {
        super(genericDao);
        this.dovizDao = (DovizDao) genericDao;
    }

    @Override
    public Doviz getDovizKodunaGore(String dovizKodu) {
        // TODO Auto-generated method stub
        return dovizDao.getDovizKodunaGore(dovizKodu);
    }

}
