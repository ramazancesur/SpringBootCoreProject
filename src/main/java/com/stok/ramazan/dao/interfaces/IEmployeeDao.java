package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.android.dto.CalisanDTO;
import com.stok.ramazan.android.dto.SirketDTO;
import com.stok.ramazan.entity.Employee;

import java.util.List;

public interface IEmployeeDao extends GenericDao<Employee, Long> {
    List<CalisanDTO> getAllCalisan(SirketDTO sirketDTO);

    CalisanDTO getCalisan(Long calisanOid);

    Employee getEmployeeByUserName(String userName);
}