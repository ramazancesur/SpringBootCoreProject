package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IRegainDao;
import com.stok.ramazan.entity.Regain;

@Repository("regainDao")
public class RegainDao extends GenericDaoImpl<Regain, Long> implements IRegainDao {

}
