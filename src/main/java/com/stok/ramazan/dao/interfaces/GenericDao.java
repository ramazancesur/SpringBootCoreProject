package com.stok.ramazan.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.stok.ramazan.entity.BaseEntity;

public interface GenericDao<E extends BaseEntity, K extends Serializable> {
	public void add(E entity);

	// Doo kısmı ikiye ayrıldım interface ve entity die ve işlemleri base
	// entityden yapılmasını sağlıyorum

	// JPA Repository ye benzer bir şey
	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E find(K key);

	public List<E> getAll();
}