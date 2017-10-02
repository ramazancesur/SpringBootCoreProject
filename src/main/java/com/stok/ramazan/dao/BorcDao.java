package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("borcDao")
public class BorcDao extends GenericDaoImpl<Borc, Long>
        implements IBorcDao {

    @Autowired
    private IFirmaDao firmaDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    public Double getToplamBorcByMusteriOid(Long musteriOid) {
        String hql = "select sum(borc.kalanBorc) from Borc as borc   " +
                " inner join borc.musteri as musteri " +
                " where musteri.oid = :musteriOid ";

        Query query = currentSession().createQuery(hql);
        query.setParameter("musteriOid", musteriOid);
        BigDecimal toplamBorc = BigDecimal.ZERO;
        try {
            toplamBorc = (BigDecimal) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.0;
        }
        return toplamBorc.doubleValue();
    }

    @Override
    public List<Borc> getAllBorcByAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userDao.findByUsername(userName);
        Criteria criteria = currentSession().createCriteria(Borc.class, "borc");
        criteria.createAlias("borc.odemeSube", "sube");
        criteria.createAlias("sube.firma", "firma");
        if (user.getUserType() == EnumUtil.UserType.CALISAN) {
            Employee employee = employeeDao.getEmployeeByUserName(userName);
            criteria.add(Restrictions.eq("firma.oid", employee.getFirma().getFirma().getOid()));
        } else {
            criteria.createAlias("firma.user", "user");
            criteria.add(Restrictions.eq("user.userName", userName));
            criteria.add(Restrictions.eq("user.entityState", EnumUtil.EntityState.ACTIVE));
        }
        criteria.add(Restrictions.eq("borc.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("firma.entityState", EnumUtil.EntityState.ACTIVE));
        return criteria.list();
    }

}
