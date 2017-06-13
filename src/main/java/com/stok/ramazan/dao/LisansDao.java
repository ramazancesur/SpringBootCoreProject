package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.ILisansDao;
import com.stok.ramazan.entity.Lisans;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.helper.LicansGenerator;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by LocalAdmin on 12.06.2017.
 */
@Repository("lisansDao")
public class LisansDao extends GenericDaoImpl<Lisans, Long> implements ILisansDao {
    LicansGenerator lisans = new LicansGenerator();

    @Override
    public Lisans add(Lisans lisans) {
        String lisansKey = lisans.getLicenseKey();
        lisans.setLicenseKey(lisansKey);
        return super.add(lisans);
    }

    public List<Lisans> getLisansListbyFirmID(Long firmOid) {
        String hql = "select lisans.licenseKey as licenseKey " +
                " lisans.licenseFinishDate as licenseFinishDate , lisans.licenseStartDate as licenseStartDate " +
                " from Lisans as lisans \n" +
                " inner join lisans.firma as firma  \n" +
                "where lisans.entityState= :state and firma.entityState =:state \n" +
                " and lisans.licenseStartDate> :currentDate \n" +
                " and lisans.licenseFinishDate< :currentDate \n" +
                " and firma.oid = :firmOid ";
        Query query = currentSession().createQuery(hql);
        query.setParameter("currentDate", new Date());
        query.setParameter("state", EnumUtil.EntityState.ACTIVE);
        query.setParameter("firmOid", firmOid);
        query.setResultTransformer(Transformers.aliasToBean(Lisans.class));
        return query.list();
    }
}