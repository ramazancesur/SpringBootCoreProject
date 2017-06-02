package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IProductDao;
import com.stok.ramazan.entity.Product;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDao extends GenericDaoImpl<Product, Long> implements IProductDao {

}