package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.entity.Firma;

@Repository("firmaDao")
public class FirmaDao extends GenericDaoImpl<Firma, Long> implements IFirmaDao {

}
