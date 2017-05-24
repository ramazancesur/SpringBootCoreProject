package com.stok.ramazan.service;

import com.stok.ramazan.dao.EmployeeDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService extends
        GenericServiceImpl<Employee, Long> implements IEmployeeService {
    private IEmployeeDao employeeDao;

    @Autowired
    public EmployeeService(@Qualifier("employeeDao") GenericDao<Employee, Long> genericDao) {
        super(genericDao);
        this.employeeDao = (EmployeeDao) genericDao;
    }


    public EmployeeService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<CalisanDTO> getAllCalisan() {
        List<CalisanDTO> lstCalisanDTO = employeeDao.getAllCalisan();
        return lstCalisanDTO;
    }
}
