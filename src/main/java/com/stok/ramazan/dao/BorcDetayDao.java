package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDetayDao;
import com.stok.ramazan.entity.BorcDetay;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("borcDetayDao")
public class BorcDetayDao extends GenericDaoImpl<BorcDetay, Long> implements IBorcDetayDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BorcDetay.class);

    @Override
    public boolean removeBorc(Long borcOid) {
        try {
            String hql = "update BorcDetay as borcDetay set borcDetay.entityState = :durum" +
                    " where borcDetay.borc.oid = :borcOid";
            Query query = currentSession().createQuery(hql);
            query.setParameter("borcOid", borcOid);
            query.setParameter("durum", EnumUtil.EntityState.PASSIVE);
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            LOGGER.error(this.getClass().getSimpleName() + " class da hata oluştu " + ex.getMessage());
            return false;
        }
    }
}