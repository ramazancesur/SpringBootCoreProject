package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IFaturaDao;
import com.stok.ramazan.entity.Fatura;
import org.springframework.stereotype.Repository;

/**
 * Created by LocalAdmin on 02.06.2017.
 */
@Repository("faturaDao")
public class FaturaDao extends GenericDaoImpl<Fatura, Long>
        implements IFaturaDao {

}