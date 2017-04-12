package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IStokDao;
import com.stok.ramazan.entity.Stok;

@Repository("stokDao")
public class StokDao extends GenericDaoImpl<Stok, Long> implements IStokDao {

}
