package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDetayDao;
import com.stok.ramazan.entity.BorcDetay;
import org.springframework.stereotype.Repository;

@Repository("borcDetayDao")
public class BorcDetayDao extends GenericDaoImpl<BorcDetay, Long> implements IBorcDetayDao {

}
