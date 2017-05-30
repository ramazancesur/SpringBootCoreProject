package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.entity.Borc;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository("borcDao")
public class BorcDao extends GenericDaoImpl<Borc, Long> implements IBorcDao {

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
}
