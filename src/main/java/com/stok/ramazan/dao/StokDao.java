package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IStokDao;
import com.stok.ramazan.entity.Stok;
import org.springframework.stereotype.Repository;

@Repository("stokDao")
public class StokDao extends GenericDaoImpl<Stok, Long> implements IStokDao {

}
