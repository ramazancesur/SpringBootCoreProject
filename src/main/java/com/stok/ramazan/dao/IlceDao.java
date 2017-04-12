package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IIlceDao;
import com.stok.ramazan.entity.Ilce;

@Repository("ilceDao")
public class IlceDao extends GenericDaoImpl<Ilce, Long> implements IIlceDao {

}
