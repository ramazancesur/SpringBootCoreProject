package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.ISubeDao;
import com.stok.ramazan.entity.Sube;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subeDao")
public class SubeDao extends GenericDaoImpl<Sube, Long> implements ISubeDao {
  public List<Sube> getAllSube(Long firmaOid) {
    Criteria criteria = currentSession().createCriteria(Sube.class, "sube");
    criteria.createAlias("sube.firma", "firma");
    criteria.add(Restrictions.eq("firma.entityState", EnumUtil.EntityState.ACTIVE));
    criteria.add(Restrictions.eq("firma.oid", firmaOid));
    return criteria.list();
  }
}