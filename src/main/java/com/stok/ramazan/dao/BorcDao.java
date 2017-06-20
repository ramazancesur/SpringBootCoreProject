package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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

    @Override
    public Double getToplamBorcByMusteriOid(Long musteriOid) {
        Criteria criteria = this.currentSession().createCriteria(Borc.class, "borc");
        criteria.createAlias("borc.musteri", "musteri");
        criteria.add(Restrictions.eq("musteri.oid", musteriOid));
        criteria.setProjection(Projections.projectionList().add(
                Projections.sum("borc.kalanBorc"))
        );
        criteria.setResultTransformer(Transformers.aliasToBean(BigDecimal.class));
        BigDecimal toplamBorc = (BigDecimal) criteria.uniqueResult();
        return toplamBorc.doubleValue();
    }

    @Override
    public List<Borc> getAllBorcByAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println(userName);


        Criteria criteria=currentSession().createCriteria(Borc.class,"borc");
        criteria.createAlias("borc.odemeSube","sube");
        criteria.createAlias("sube.firma","firma");
        criteria.createAlias("firma.user","user");

        criteria.add(Restrictions.eq("user.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("borc.entityState", EnumUtil.EntityState.ACTIVE));

        criteria.add(Restrictions.eq("user.userName",userName));

        return criteria.list();
    }


}
