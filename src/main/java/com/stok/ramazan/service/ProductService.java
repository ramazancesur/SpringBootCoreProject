package com.stok.ramazan.service;

import com.stok.ramazan.dao.ProductDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IProductDao;
import com.stok.ramazan.entity.Product;
import com.stok.ramazan.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends GenericServiceImpl<Product, Long> implements IProductService {
    private IProductDao productDao;

    public ProductService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public ProductService(@Qualifier("productDao") GenericDao<Product, Long> genericDao) {
        super(genericDao);
        this.productDao = (ProductDao) genericDao;
    }
}
