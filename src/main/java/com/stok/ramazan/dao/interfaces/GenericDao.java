package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.BaseEntity;
import com.stok.ramazan.pojo.DataProperty;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E extends BaseEntity, K extends Serializable> {
    E add(E entity);

    // Doo kısmı ikiye ayrıldım interface ve entity die ve işlemleri base
    // entityden yapılmasını sağlıyorum

    // JPA Repository ye benzer bir şey
    E saveOrUpdate(E entity);

    E update(E entity);

    boolean remove(E entity);

    boolean remove(K key);

    E find(K key);

    List<E> getAll();

    Criteria getData(List<DataProperty> lstRelationClass, List<DataProperty> lstProperty);

    public List<Object> getAllPojo(List<DataProperty> lstRelationClass, List<DataProperty> lstProperty, Class clazz);

}