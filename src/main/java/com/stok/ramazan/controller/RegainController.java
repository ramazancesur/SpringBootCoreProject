package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Regain;
import com.stok.ramazan.service.interfaces.IRegainService;
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

import java.util.List;

@RestController
public class RegainController extends BaseController<Regain> {
    @Autowired
    private IRegainService service;

    @GetMapping("/Regain/all")
    public ResponseEntity<List<Regain>> getAll() {
        List<Regain> lstRegain = this.service.getAll();
        return new ResponseEntity<List<Regain>>(lstRegain, HttpStatus.OK);
    }

    @GetMapping(value = "/Regain/{id}")
    public ResponseEntity<Regain> getDataById(@PathVariable("id") Long id) {
        Regain Regain = this.service.get(id);
        return new ResponseEntity<Regain>(Regain, HttpStatus.OK);
    }

    @PostMapping(value = "/Regain")
    public ResponseEntity<Boolean> addData(@RequestBody Regain Regain) {
        this.service.add(Regain);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Regain")
    public ResponseEntity<Boolean> updateData(@RequestBody Regain Regain) {
        this.service.update(Regain);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Regain")
    public ResponseEntity<Boolean> deleteData(@RequestBody Regain Regain) {
        this.service.remove(Regain);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
