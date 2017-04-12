package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IUstleniciFirmaDao;
import com.stok.ramazan.entity.UstleniciFirma;

@Repository("ustleniciFirmaDao")
public class UstleniciFirmaDao extends GenericDaoImpl<UstleniciFirma, Long> implements IUstleniciFirmaDao {

}
