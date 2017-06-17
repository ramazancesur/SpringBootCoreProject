package com.stok.ramazan.service;

import com.stok.ramazan.dao.UstleniciFirmaDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IUstleniciFirmaDao;
import com.stok.ramazan.entity.UstleniciFirma;
import com.stok.ramazan.service.interfaces.IUstleniciFirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UstleniciFirmaService extends GenericServiceImpl<UstleniciFirma, Long> implements IUstleniciFirmaService {
    private IUstleniciFirmaDao ustleniciFirmaDao;

    public UstleniciFirmaService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public UstleniciFirmaService(@Qualifier("ustleniciFirmaDao") GenericDao<UstleniciFirma, Long> genericDao) {
        super(genericDao);
        this.ustleniciFirmaDao = (UstleniciFirmaDao) genericDao;
    }
}
