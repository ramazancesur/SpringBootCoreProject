package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Sube;

import java.util.List;

public interface ISubeDao extends GenericDao<Sube, Long> {
  List<Sube> getAllSube(Long firmaOid);
}