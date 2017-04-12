package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.entity.Borc;

@Repository("borcDao")
public class BorcDao extends GenericDaoImpl<Borc, Long> implements IBorcDao {

}
