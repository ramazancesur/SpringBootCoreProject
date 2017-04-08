package com.stok.ramazan.service.interfaces;

import java.util.List;

import com.stok.ramazan.entity.BaseEntity;

public interface IGenericService<E extends BaseEntity, K> {
    public void saveOrUpdate(E entity);

    public List<E> getAll();

    public E get(K id);

    // Aynı şekilde servisleride generic hale getirdim 
    public void add(E entity);

    public void update(E entity);

    public void remove(E entity);
}