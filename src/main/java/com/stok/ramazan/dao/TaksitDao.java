package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.ITaksitDao;
import com.stok.ramazan.entity.Taksit;

@Repository("taksitDao")
public class TaksitDao extends GenericDaoImpl<Taksit, Long> implements ITaksitDao {

}
