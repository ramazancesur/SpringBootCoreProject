package com.stok.ramazan.controller;

import com.stok.ramazan.android.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController extends BaseController<Employee> {
    @Autowired
    private IEmployeeService service;

    @GetMapping("/Employee/all")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> lstEmployee = this.service.getAll();
        return new ResponseEntity<List<Employee>>(lstEmployee, HttpStatus.OK);
    }

    @GetMapping("/CalisanList")
    public ResponseEntity<List<CalisanDTO>> getCalisanList() {
        List<CalisanDTO> lstCalisanDTO = service.getAllCalisan();
        return new ResponseEntity<List<CalisanDTO>>(lstCalisanDTO, HttpStatus.OK);
    }

    @GetMapping("/CalisanList/{oıd}")
    public ResponseEntity<CalisanDTO> getCalisan(@PathVariable("oid") Long oid) {
        CalisanDTO calisanDTO = this.service.getCalisan(oid);
        return new ResponseEntity<CalisanDTO>(calisanDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/EmployeeList/{id}")
    public ResponseEntity<Employee> getDataById(@PathVariable("id") Long id) {
        Employee Employee = this.service.get(id);
        return new ResponseEntity<Employee>(Employee, HttpStatus.OK);
    }

    @PostMapping(value = "/Employee")
    public ResponseEntity<Boolean> addData(@RequestBody Employee Employee) {
        this.service.add(Employee);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PostMapping(value = "/Employee/Calisan")
    public ResponseEntity<Void> addCalisan(@RequestBody CalisanDTO calisanDTO) {
        this.service.addCalsan(calisanDTO);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value = "/Employee")
    public ResponseEntity<Boolean> updateData(@RequestBody Employee Employee) {
        this.service.update(Employee);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(value = "/Employee/Calisan")
    public ResponseEntity<CalisanDTO> updateCalisan(@RequestBody CalisanDTO calisanDTO) {
        this.service.updateCalisan(calisanDTO);
        return new ResponseEntity<CalisanDTO>(calisanDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Employee")
    public ResponseEntity<Boolean> deleteData(Employee Employee) {
        this.service.remove(Employee);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(value = "/Employee/Calisan")
    public ResponseEntity<Boolean> deleteCalisan(CalisanDTO calisanDTO) {
        this.service.deleteCalisan(calisanDTO.getOid());
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
