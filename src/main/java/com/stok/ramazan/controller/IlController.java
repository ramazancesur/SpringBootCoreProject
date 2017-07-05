package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Il;
import com.stok.ramazan.service.interfaces.IIlService;
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
public class IlController extends BaseController<Il> {
    @Autowired
    private IIlService service;

    @GetMapping("/Il/all")
    public ResponseEntity<List<Il>> getAll() {
        List<Il> lstIl = this.service.getAll();
        return new ResponseEntity<List<Il>>(lstIl, HttpStatus.OK);
    }

    @GetMapping(value = "/Il/{id}")
    public ResponseEntity<Il> getDataById(@PathVariable("id") Long id) {
        Il Il = this.service.get(id);
        return new ResponseEntity<Il>(Il, HttpStatus.OK);
    }

    @PostMapping(value = "/Il")
    public ResponseEntity<Boolean> addData(@RequestBody Il Il) {
        this.service.add(Il);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Il")
    public ResponseEntity<Boolean> updateData(@RequestBody Il Il) {
        this.service.update(Il);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Il")
    public ResponseEntity<Boolean> deleteData(@RequestBody Il Il) {
        this.service.remove(Il);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
