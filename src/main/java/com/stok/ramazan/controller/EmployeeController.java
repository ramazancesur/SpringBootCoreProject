package com.stok.ramazan.controller;

import com.stok.ramazan.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController extends BaseController {
    @Autowired
    private IEmployeeService service;

    @GetMapping("/EmployeeList")
    public ResponseEntity<List<Employee>> getKonusmaciList() {
        List<Employee> lstEmployee = this.service.getAll();
        return new ResponseEntity<List<Employee>>(lstEmployee, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CalisanDTO>> getCalisanList() {
        List<CalisanDTO> lstCalisanDTO = service.getAllCalisan();
        return new ResponseEntity<List<CalisanDTO>>(lstCalisanDTO, HttpStatus.OK);
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
