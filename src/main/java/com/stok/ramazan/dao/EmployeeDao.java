package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.entity.Employee;

@Repository("employeeDao")
public class EmployeeDao extends GenericDaoImpl<Employee, Long> implements IEmployeeDao {

}
