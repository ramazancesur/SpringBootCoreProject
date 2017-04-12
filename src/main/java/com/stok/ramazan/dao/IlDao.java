package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IIlDao;
import com.stok.ramazan.entity.Il;

@Repository("ilDao")
public class IlDao extends GenericDaoImpl<Il, Long> implements IIlDao {

}
