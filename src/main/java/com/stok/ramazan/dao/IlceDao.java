package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IIlceDao;
import com.stok.ramazan.entity.Ilce;
import org.springframework.stereotype.Repository;

@Repository("ilceDao")
public class IlceDao extends GenericDaoImpl<Ilce, Long> implements IIlceDao {

}
