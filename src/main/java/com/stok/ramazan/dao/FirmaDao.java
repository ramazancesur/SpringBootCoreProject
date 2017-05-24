package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.entity.Firma;
import org.springframework.stereotype.Repository;

@Repository("firmaDao")
public class FirmaDao extends GenericDaoImpl<Firma, Long> implements IFirmaDao {

}
