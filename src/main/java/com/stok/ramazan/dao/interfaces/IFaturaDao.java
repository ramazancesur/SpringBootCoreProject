package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Fatura;

import java.util.List;

/**
 * Created by LocalAdmin on 02.06.2017.
 */
public interface IFaturaDao extends GenericDao<Fatura, Long> {
    List<Fatura> getAllFaturaByAuth();
}