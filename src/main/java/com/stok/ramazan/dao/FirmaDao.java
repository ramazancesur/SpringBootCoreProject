package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Lisans;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("firmaDao")
public class FirmaDao extends GenericDaoImpl<Firma, Long> implements IFirmaDao {

  public Lisans getAllActiveLisans(Long firmaOid) {
        Criteria criteria = currentSession().createCriteria(Lisans.class, "lisans");
        criteria.createAlias("lisans.firma", "firma");
        criteria.add(Restrictions.eq("firma.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("lisans.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("firma.oid", firmaOid));
    criteria.add(Restrictions.ge("lisans.licenseFinishDate", new Date()));
    criteria.add(Restrictions.le("lisans.licenseStartDate", new Date()));
    criteria.add(Restrictions.isNotNull("lisans.licenseKey"));
    return (Lisans) criteria.uniqueResult();
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
        String hql = "select firma from Lisans as lisans \n" +
                " inner join lisans.firma as firma  \n" +
                "where lisans.entityState= :state and firma.entityState =:state \n" +
                " and lisans.licenseStartDate> :currentDate \n" +
                " and lisans.licenseFinishDate< :currentDate \n";
        Query query = currentSession().createQuery(hql);
        query.setParameter("currentDate", new Date());
        query.setParameter("state", EnumUtil.EntityState.ACTIVE);
        query.setResultTransformer(Transformers.aliasToBean(Firma.class));
        return query.list();
    }

    @Override
    public Firma getFirma(String userName) {
        Criteria criteria=currentSession().createCriteria(Firma.class,"firma");
        criteria.createAlias("firma.user","user");
        criteria.add(Restrictions.eq("user.userName",userName));
        criteria.add(Restrictions.eq("firma.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("user.entityState", EnumUtil.EntityState.ACTIVE));
        return (Firma) criteria.uniqueResult();
    }
}