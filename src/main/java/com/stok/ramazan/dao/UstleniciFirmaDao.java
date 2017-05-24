package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IUstleniciFirmaDao;
import com.stok.ramazan.entity.UstleniciFirma;
import org.springframework.stereotype.Repository;

@Repository("ustleniciFirmaDao")
public class UstleniciFirmaDao extends GenericDaoImpl<UstleniciFirma, Long> implements IUstleniciFirmaDao {

}
