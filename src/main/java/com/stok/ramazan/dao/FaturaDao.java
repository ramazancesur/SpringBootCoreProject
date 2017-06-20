package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IFaturaDao;
import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.Fatura;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ramazan CESUR on 02.06.2017.
 */
@Repository("faturaDao")
public class FaturaDao extends GenericDaoImpl<Fatura, Long>
    implements IFaturaDao {
  @Autowired
  private IUserDao userDao;

  @Override
  public List<Fatura> getAllFaturaByAuth() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String userName = auth.getName();

    User user = userDao.findByUsername(userName);
    Criteria criteria = currentSession().createCriteria(Fatura.class, "fatura");
    if (user.getUserType() == EnumUtil.UserType.CALISAN) {
      criteria.createAlias("fatura.employee", "emp");
      criteria.createAlias("emp.user", "user");
    } else if (user.getUserType() == EnumUtil.UserType.FIRMA) {
      criteria.createAlias("fatura.firma", "firma");
      criteria.createAlias("firma.user", "user");
    }
    criteria.add(Restrictions.eq("user.userName", userName));
    criteria.add(Restrictions.eq("fatura.entityState", EnumUtil.EntityState.ACTIVE));
    return criteria.list();
  }
}