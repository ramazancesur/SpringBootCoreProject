package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.entity.Borc;
import org.springframework.stereotype.Repository;

@Repository("borcDao")
public class BorcDao extends GenericDaoImpl<Borc, Long> implements IBorcDao {

}
