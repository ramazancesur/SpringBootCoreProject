package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IBorcDetayDao;
import com.stok.ramazan.entity.BorcDetay;

@Repository("borcDetayDao")
public class BorcDetayDao extends GenericDaoImpl<BorcDetay, Long> implements IBorcDetayDao {

}
