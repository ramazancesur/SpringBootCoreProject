package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.android.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;

import java.util.List;

public interface IEmployeeService extends IGenericService<Employee, Long> {
    List<CalisanDTO> getAllCalisan();

    void addCalsan(CalisanDTO calisanDTO);

    void updateCalisan(CalisanDTO calisanDTO);

    boolean deleteCalisan(Long calisanOid);

    CalisanDTO getCalisan(Long oid);
}
