package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.ITaksitDao;
import com.stok.ramazan.entity.Taksit;
import org.springframework.stereotype.Repository;

@Repository("taksitDao")
public class TaksitDao extends GenericDaoImpl<Taksit, Long> implements ITaksitDao {

}
