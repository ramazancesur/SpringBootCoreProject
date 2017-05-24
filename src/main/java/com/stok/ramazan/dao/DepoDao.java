package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IDepoDoo;
import com.stok.ramazan.entity.Depo;
import org.springframework.stereotype.Repository;

@Repository("depoDao")
public class DepoDao extends GenericDaoImpl<Depo, Long> implements IDepoDoo {

}
