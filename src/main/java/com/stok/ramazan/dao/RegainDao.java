package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IRegainDao;
import com.stok.ramazan.entity.Regain;
import org.springframework.stereotype.Repository;

@Repository("regainDao")
public class RegainDao extends GenericDaoImpl<Regain, Long> implements IRegainDao {

}
