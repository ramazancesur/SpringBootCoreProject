package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IPriceDetayDao;
import com.stok.ramazan.entity.PriceDetay;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("priceDetayDao")
public class PriceDetayDao extends GenericDaoImpl<PriceDetay, Long> implements IPriceDetayDao {

  @Override
  public List<PriceDetay> getPriceDetayByProduct(Long productOid) {
    String hql = "select priceDetay from PriceDetay as priceDetay , Price as price, Product as product " +
        " where priceDetay.price= price and product.price =price  " +
        " and priceDetay.entityState = :entityState and price.entityState= :entityState" +
        " and product.entityState = :entityState and product.oid= :productOid ";
    Query query = currentSession().createQuery(hql);
    query.setParameter("productOid", productOid);
    query.setParameter("entityState", EnumUtil.EntityState.ACTIVE);
    return query.list();
  }
}