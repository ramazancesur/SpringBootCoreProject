package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.ISubeDao;
import com.stok.ramazan.entity.Sube;
import org.springframework.stereotype.Repository;

@Repository("subeDao")
public class SubeDao extends GenericDaoImpl<Sube, Long> implements ISubeDao {

}
