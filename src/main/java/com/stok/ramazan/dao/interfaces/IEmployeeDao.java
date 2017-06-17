package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.android.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;

import java.util.List;

public interface IEmployeeDao extends GenericDao<Employee, Long> {
    List<CalisanDTO> getAllCalisan();

    CalisanDTO getCalisan(Long calisanOid);
}
