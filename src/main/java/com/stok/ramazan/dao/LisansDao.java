package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.ILisansDao;
import com.stok.ramazan.entity.Lisans;
import org.springframework.stereotype.Repository;

/**
 * Created by LocalAdmin on 12.06.2017.
 */
@Repository("lisansDao")
public class LisansDao extends GenericDaoImpl<Lisans, Long> implements ILisansDao {
}