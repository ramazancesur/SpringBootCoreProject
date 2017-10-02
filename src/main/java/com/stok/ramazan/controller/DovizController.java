package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Doviz;
import com.stok.ramazan.service.interfaces.IDovizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DovizController extends BaseController<Doviz> {
    @Autowired
    private IDovizService service;

    @GetMapping("/Doviz/all")
    public ResponseEntity<List<Doviz>> getAll() {
        List<Doviz> lstDoviz = this.service.getAll();
        return new ResponseEntity<List<Doviz>>(lstDoviz, HttpStatus.OK);
    }

    @GetMapping(value = "/Doviz/{id}")
    public ResponseEntity<Doviz> getDataById(@PathVariable("id") Long id) {
        Doviz Doviz = this.service.get(id);
        return new ResponseEntity<Doviz>(Doviz, HttpStatus.OK);
    }

    @PostMapping(value = "/Doviz")
    public ResponseEntity<Boolean> addData(@RequestBody Doviz Doviz) {
        this.service.add(Doviz);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Doviz")
    public ResponseEntity<Boolean> updateData(@RequestBody Doviz Doviz) {
        this.service.update(Doviz);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Doviz")
    public ResponseEntity<Boolean> deleteData(@RequestBody Doviz Doviz) {
        this.service.remove(Doviz);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
