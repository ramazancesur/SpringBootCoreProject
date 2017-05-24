package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;

import java.util.List;

public interface IEmployeeService extends IGenericService<Employee, Long> {
    List<CalisanDTO> getAllCalisan();
}
