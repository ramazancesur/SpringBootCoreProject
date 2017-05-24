package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E extends BaseEntity, K extends Serializable> {
    public E add(E entity);

    // Doo kısmı ikiye ayrıldım interface ve entity die ve işlemleri base
    // entityden yapılmasını sağlıyorum

    // JPA Repository ye benzer bir şey
    public E saveOrUpdate(E entity);

    public E update(E entity);

    public boolean remove(E entity);

    public boolean remove(K key);

    public E find(K key);

    public List<E> getAll();
}