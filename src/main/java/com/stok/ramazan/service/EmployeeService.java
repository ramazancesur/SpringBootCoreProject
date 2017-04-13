package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.EmployeeDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.service.interfaces.IEmployeeService;

@Service
public class EmployeeService extends GenericServiceImpl<Employee, Long> implements IEmployeeService {
	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeService(@Qualifier("employeeDao") GenericDao<Employee, Long> genericDao) {
		super(genericDao);
		this.employeeDao = (EmployeeDao) genericDao;
	}
	
	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}
}
