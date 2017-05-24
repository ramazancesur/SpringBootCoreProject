package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IAdressDao;
import com.stok.ramazan.entity.Address;
import org.springframework.stereotype.Repository;

@Repository("adresDao")
public class AdresDao extends GenericDaoImpl<Address, Long> implements IAdressDao {

}