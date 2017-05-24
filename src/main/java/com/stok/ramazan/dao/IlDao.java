package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IIlDao;
import com.stok.ramazan.entity.Il;
import org.springframework.stereotype.Repository;

@Repository("ilDao")
public class IlDao extends GenericDaoImpl<Il, Long> implements IIlDao {

}
