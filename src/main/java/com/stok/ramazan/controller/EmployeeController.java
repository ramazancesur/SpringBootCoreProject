package com.stok.ramazan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.service.interfaces.IEmployeeService;

@RestController
public class EmployeeController extends BaseController {
	@Autowired
	private IEmployeeService service;

	@GetMapping("/EmployeeList")
	public ResponseEntity<List<Employee>> getKonusmaciList() {
		List<Employee> lstEmployee = this.service.getAll();
		return new ResponseEntity<List<Employee>>(lstEmployee, HttpStatus.OK);
	}

	@GetMapping(value = "/EmployeeList/{id}")
	public ResponseEntity<Employee> getKonusmaciById(@PathVariable("id") Long id) {
		Employee Employee = this.service.get(id);
		return new ResponseEntity<Employee>(Employee, HttpStatus.OK);
	}

	@PostMapping(value = "/Employee")
	public ResponseEntity<Employee> addKonusmaci(@RequestBody Employee Employee) {
		this.service.add(Employee);
		return new ResponseEntity<Employee>(Employee, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Employee")
	public ResponseEntity<Employee> updateKonusmaci(@PathVariable Long id, @RequestBody Employee Employee) {
		this.service.update(Employee);
		return new ResponseEntity<Employee>(Employee, HttpStatus.OK);
	}

	@DeleteMapping(value = "/Employee")
	public ResponseEntity<Boolean> deleteEtkinlik(Employee Employee) {
		this.service.remove(Employee);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
