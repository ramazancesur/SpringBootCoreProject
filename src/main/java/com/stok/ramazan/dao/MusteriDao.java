package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.entity.Musteri;
import org.springframework.stereotype.Repository;

@Repository("musteriDao")
public class MusteriDao extends GenericDaoImpl<Musteri, Long> implements IMusteriDao {

}
