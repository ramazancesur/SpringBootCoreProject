package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IProductDao;
import com.stok.ramazan.entity.Product;

@Repository("productDao")
public class ProductDao extends GenericDaoImpl<Product, Long> implements IProductDao {

}
