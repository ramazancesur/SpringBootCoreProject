package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Address;
import com.stok.ramazan.service.interfaces.IAddresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdresController extends BaseController<Address> {
    @Autowired
    private IAddresService service;

    @GetMapping("/Address/all")
    @Override
    public ResponseEntity<List<Address>> getAll() {
        List<Address> lstAddress = this.service.getAll();
        return new ResponseEntity<List<Address>>(lstAddress, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/AddressList/{id}")
    public ResponseEntity<Address> getDataById(@PathVariable("id") Long id) {
        Address Address = this.service.get(id);
        return new ResponseEntity<Address>(Address, HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/Address")
    public ResponseEntity<Boolean> addData(@RequestBody Address Address) {
        this.service.add(Address);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @Override
    @PutMapping(value = "/Address")
    public ResponseEntity<Boolean> updateData(@RequestBody Address address) {
        this.service.update(address);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Address")
    public ResponseEntity<Boolean> deleteData(@RequestBody Address Address) {
        this.service.remove(Address);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }

}