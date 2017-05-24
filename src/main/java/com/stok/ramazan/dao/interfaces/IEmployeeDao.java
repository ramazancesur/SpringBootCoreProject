package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;

import java.util.List;

public interface IEmployeeDao extends GenericDao<Employee, Long> {
    List<CalisanDTO> getAllCalisan();
    void addCalisan(CalisanDTO calisanDTO);
    void updateCalisan(CalisanDTO calisanDTO);
    boolean deleteCalisan(Long calisanOid);

    CalisanDTO getCalisan(Long calisanOid);

}
