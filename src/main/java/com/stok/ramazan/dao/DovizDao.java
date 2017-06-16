package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IDovizDao;
import com.stok.ramazan.entity.Doviz;
import com.stok.ramazan.helper.EnumUtil.EntityState;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dovizDao")
public class DovizDao extends GenericDaoImpl<Doviz, Long> implements IDovizDao {

  @Override
  public Doviz getDovizKodunaGore(String dovizKodu) {
    // TODO Auto-generated method stub
    Criteria criteria = this.createEntityCriteria();
    criteria.add(Restrictions.eq("dovizKodu", dovizKodu));
    criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
    List<Doviz> lstDoviz = criteria.list();
    if (lstDoviz.size() > 0) {
      return lstDoviz.get(lstDoviz.size() - 1);
    } else {
      return null;
    }
  }
}