package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.pojo.Calisan;

import java.util.List;

public interface IEmployeeService extends IGenericService<Employee, Long> {
    List<Calisan> getAllCalisan();
}
