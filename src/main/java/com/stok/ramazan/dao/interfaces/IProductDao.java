package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Product;

import java.util.List;

public interface IProductDao extends GenericDao<Product, Long> {
    List<Product> getAllProductforFirmOid();
}