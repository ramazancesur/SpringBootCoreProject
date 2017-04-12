package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IAdressDao;
import com.stok.ramazan.entity.Address;

@Repository("adresDao")
public class AdresDao extends GenericDaoImpl<Address, Long> implements IAdressDao {

}