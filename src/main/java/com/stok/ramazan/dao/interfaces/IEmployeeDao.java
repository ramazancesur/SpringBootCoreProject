package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.pojo.Calisan;

import java.util.List;

public interface IEmployeeDao extends GenericDao<Employee, Long> {
    List<Calisan> getAllCalisan();
}