package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.ISubeDao;
import com.stok.ramazan.entity.Sube;

@Repository("subeDao")
public class SubeDao extends GenericDaoImpl<Sube, Long> implements ISubeDao {

}
