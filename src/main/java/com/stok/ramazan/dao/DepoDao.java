package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IDepoDoo;
import com.stok.ramazan.entity.Depo;

@Repository("depoDao")
public class DepoDao extends GenericDaoImpl<Depo, Long> implements IDepoDoo {

}
