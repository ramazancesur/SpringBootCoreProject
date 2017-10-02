package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.entity.Payment;
import com.stok.ramazan.entity.Sube;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("paymentDao")
public class PaymentDao extends GenericDaoImpl<Payment, Long> implements IPaymentDao {

    @Autowired
    private ISubeService subeService;

    @Autowired
    private IBorcDao borcDao;

    @Override
    public Double getToplamOdemeByMusteriOid(Long musteriOid) {
        // Müşteri ödemeleri toplami
        String hql = "select sum(payment.odemeTutari) as odemeTutari from Payment as payment " +
                " inner join payment.borc as borc " +
                " inner join borc.musteri as musteri " +
                " where payment.entityState = :entityState and borc.entityState= :entityState " +
                " and musteri.entityState= :entityState and musteri.oid= :musteriOid ";
        Query query = getSession().createQuery(hql);
        query.setParameter("entityState", EnumUtil.EntityState.ACTIVE);
        query.setParameter("musteriOid", musteriOid);

        if (query.uniqueResult() == null) {
            return 0.0;
        }
        BigDecimal totalOdeme = (BigDecimal) query.uniqueResult();
        return totalOdeme.doubleValue();
    }

    @Override
    public List<Payment> getAllPaymentForAuthType(String userName) {
        String hql = "select payment from Payment as payment " +
                "where payment.saticiSube.firma.user.userName= :userName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("userName", userName);
        return query.list();
    }

    @Override
    public List<Payment> getAllPaymentByUser() {
        Sube sube = subeService.getUserFirmSube();
        if (sube != null) {
            Criteria criteria = currentSession().createCriteria(Payment.class, "payment");
            criteria.createAlias("payment.saticiSube", "sube");
            criteria.add(Restrictions.eq("sube.oid", sube.getOid()));
            criteria.add(Restrictions.eq("payment.entityState", EnumUtil.EntityState.ACTIVE));
            return criteria.list();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Payment getLastPayment(Long borcOid) {
        String hql = "select payment from Payment as payment" +
                " inner join payment.borc as borc  " +
                " where borc.oid = :borcOid " +
                " order by date(payment.createdDate) desc ";
        Query query = currentSession().createQuery(hql).setMaxResults(1);
        query.setParameter("borcOid", borcOid);
        Payment lastedPayment = (Payment) query.uniqueResult();
        if (lastedPayment == null) {
            lastedPayment = new Payment();
            lastedPayment.setOdemeTutari(BigDecimal.ZERO);
            lastedPayment.setGerceklesenOdemeTarihi(new Date());
            lastedPayment.setBorc(borcDao.find(borcOid));
            lastedPayment.setOdemeTipi(EnumUtil.OdemeTipi.GELIR);
            this.add(lastedPayment);
        } else if (lastedPayment.getOdemeTutari() == null) {
            lastedPayment.setOdemeTutari(BigDecimal.ZERO);
            this.update(lastedPayment);
        }
        return lastedPayment;
    }

}