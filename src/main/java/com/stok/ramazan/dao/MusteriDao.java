package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.entity.Musteri;

@Repository("musteriDao")
public class MusteriDao extends GenericDaoImpl<Musteri, Long> implements IMusteriDao {

}
