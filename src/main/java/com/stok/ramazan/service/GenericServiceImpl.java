package com.stok.ramazan.service;

import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.BaseEntity;
import com.stok.ramazan.service.interfaces.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class GenericServiceImpl<E extends BaseEntity, K extends Serializable>
        implements IGenericService<E, K> {

    @Autowired
    private GenericDao<E, K> genericDao;

    public GenericServiceImpl(GenericDao<E, K> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImpl() {
    }

    @Override
    public void saveOrUpdate(E entity) {
        genericDao.saveOrUpdate(entity);
    }

    @Override
    public List<E> getAll() {
        return genericDao.getAll();
    }

    @Override
    public E get(K id) {
        return genericDao.find(id);
    }

    @Override
    public void add(E entity) {
        genericDao.add(entity);
    }

    @Override
    public void update(E entity) {
        genericDao.update(entity);
    }

    @Override
    public void remove(E entity) {
        genericDao.remove(entity);
    }
}
