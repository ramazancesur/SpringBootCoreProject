package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.Payment;
import com.stok.ramazan.entity.Sube;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository("paymentDao")
public class PaymentDao extends GenericDaoImpl<Payment, Long> implements IPaymentDao {

    @Autowired
    private ISubeService subeService;

    @Override
    public Double getToplamOdemeByMusteriOid(Long musteriOid) {
        Criteria criteria = getSession().createCriteria(Payment.class, "payment");
        criteria.createAlias("payment.borc", "borc");
        criteria.createAlias("borc.musteri", "musteri");

        criteria.add(Restrictions.eq("payment.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("borc.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.eq("musteri.entityState", EnumUtil.EntityState.ACTIVE));

        criteria.add(Restrictions.eq("musteri.oid", musteriOid));
        criteria.setProjection(Projections.projectionList().add(
                Projections.sum("payment.OdemeTutari")
        ));
        criteria.setResultTransformer(Transformers.aliasToBean(BigDecimal.class));
        BigDecimal totalOdeme = (BigDecimal) criteria.uniqueResult();
        return totalOdeme.doubleValue();
    }

    @Override
    public List<Payment> getAllPaymentForAuthType(String userName) {
        String hql="select payment from Payment as payment " +
            "where payment.saticiSube.firma.user.userName= :userName";
        Query query=currentSession().createQuery(hql);
        query.setParameter("userName",userName);
        return query.list();
    }

    @Override
    public List<Payment> getAllPaymentByUser() {
        Sube sube=subeService.getUserFirmSube();
        if (sube!=null) {
            Criteria criteria = currentSession().createCriteria(Payment.class, "payment");
            criteria.createAlias("payment.saticiSube", "sube");
            criteria.add(Restrictions.eq("sube.oid", sube.getOid()));
            criteria.add(Restrictions.eq("payment.entityState", EnumUtil.EntityState.ACTIVE));
            return criteria.list();
        }else {
            return new ArrayList<>();
        }
    }
}