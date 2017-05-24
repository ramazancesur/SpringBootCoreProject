package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IConductDao;
import com.stok.ramazan.entity.Conduct;
import org.springframework.stereotype.Repository;

@Repository("conductDao")
public class ConductDao extends GenericDaoImpl<Conduct, Long> implements IConductDao {

}