package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Lisans;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository("firmaDao")
public class FirmaDao extends GenericDaoImpl<Firma, Long> implements IFirmaDao {

    public Lisans getAllActiveLisans(Long firmaOid) {
        String hql = "select  lisans from Lisans as lisans , Firma as firma  " +
                " where firma = lisans.firma and lisans.entityState= :entityState  \n " +
                " and lisans.licenseStartDate< :currentDate \n" +
                " and firma.entityState=:entityState and lisans.licenseFinishDate> :currentDate \n" +
                " order by lisans.licenseFinishDate desc ";

        Query query = currentSession().createQuery(hql);
        query.setParameter("entityState", EnumUtil.EntityState.ACTIVE);
        query.setParameter("currentDate", Calendar.getInstance().getTime());
        query.setMaxResults(1);
        return (Lisans) query.uniqueResult();
    }

    public Firma getFirma(String sirketAdi, String sirketLogoYol) {
        Criteria criteria = currentSession().createCriteria(Firma.class, "firma");
        criteria.add(Restrictions.eq("firma.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("firma.firmaAdi", sirketAdi));
        criteria.add(Restrictions.eq("firma.firmaLogoYolu", sirketLogoYol));
        Firma firma = (Firma) criteria.uniqueResult();
        return firma;
    }

    public List<Firma> getAllEssentialFirm() {
        String hql = "select firma from Lisans as lisans, Firma as firma  \n" +
                "where lisans.entityState= :state and firma.entityState =:state \n" +
                " and lisans.licenseStartDate<= :currentDate \n" +
                " and lisans.licenseFinishDate>= :currentDate \n" +
                " and lisans.firma = firma  ";
        Query query = currentSession().createQuery(hql);
        query.setParameter("currentDate", new Date());
        query.setParameter("state", EnumUtil.EntityState.ACTIVE);
        return query.list();
    }

    @Override
    public Firma getFirma(String userName) {
        Criteria criteria = currentSession().createCriteria(Firma.class, "firma");
        criteria.createAlias("firma.user", "user");
        criteria.add(Restrictions.eq("user.userName", userName));
        criteria.add(Restrictions.eq("firma.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("user.entityState", EnumUtil.EntityState.ACTIVE));
        return (Firma) criteria.uniqueResult();
    }
}