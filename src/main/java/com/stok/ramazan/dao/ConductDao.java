package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IConductDao;
import com.stok.ramazan.entity.Conduct;

@Repository("conductDao")
public class ConductDao extends GenericDaoImpl<Conduct, Long> implements IConductDao {

}