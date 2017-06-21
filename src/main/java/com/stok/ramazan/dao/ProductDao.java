package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IProductDao;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Product;
import com.stok.ramazan.entity.Sube;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.security.tools.keytool.Resources_sv;

import java.util.List;

@Repository("productDao")
public class ProductDao extends GenericDaoImpl<Product, Long> implements IProductDao {

  @Autowired
  private ISubeService subeService;

  @Override
  public List<Product> getAllProductforFirmOid() {
    Firma firma= subeService.getFirmByUser();
    Criteria criteria=currentSession().createCriteria(Product.class,"product");
    criteria.createAlias("product.firma","firma");
    criteria.add(Restrictions.eq("firma.oid",firma.getOid()));
    criteria.add(Restrictions.eq("product.entityState", EnumUtil.EntityState.ACTIVE));
    return criteria.list();
  }
}