package com.stok.ramazan.dao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.BaseEntity;
import com.stok.ramazan.helper.EnumUtil.EntityState;

/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * @see SessionFactory
 */
@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<E extends BaseEntity, K extends Serializable> implements GenericDao<E, K> {
    @Autowired
    private SessionFactory sessionFactory;

    protected Class<? extends E> daoType;
    
// Burda da implemantasyonu var

    /** evet ates edio :D 
     * By defining this class as abstract, we prevent Spring from creating instance of this class
     * If not defined abstract getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor with parameters.
     */
    @SuppressWarnings("rawtypes")
	public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
    	entity.setCreatedDate(new Date());
    	entity.setEntityState(EntityState.ACTIVE);
    	entity.setUpdatedDate(new Date());
    	currentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
    	entity.setUpdatedDate(new Date());
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(E entity) {
    	entity.setUpdatedDate(new Date());
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void remove(E entity) {
    	entity.setUpdatedDate(new Date());
    	entity.setEntityState(EntityState.PASSIVE);
        currentSession().update(entity);
    }
    

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(this.daoType);
	}

    @Override
    public E find(K key) {
    	Criteria criteria=this.createEntityCriteria();
    	criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
    	criteria.add(Restrictions.eq("id",key));
    	return (E) criteria.uniqueResult();
    }

    @Override
    public List<E> getAll() {
    	Criteria criteria=this.createEntityCriteria();
    	criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
        return criteria.list();
    }
}