package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Ilce;
import com.stok.ramazan.service.interfaces.IIlceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IlceController extends BaseController<Ilce> {
    @Autowired
    private IIlceService service;

    @GetMapping("/Ilce/all")
    public ResponseEntity<List<Ilce>> getAll() {
        List<Ilce> lstIlce = this.service.getAll();
        return new ResponseEntity<List<Ilce>>(lstIlce, HttpStatus.OK);
    }

    @GetMapping(value = "/Ilce/{id}")
    public ResponseEntity<Ilce> getDataById(@PathVariable("id") Long id) {
        Ilce Ilce = this.service.get(id);
        return new ResponseEntity<Ilce>(Ilce, HttpStatus.OK);
    }

    @PostMapping(value = "/Ilce")
    public ResponseEntity<Boolean> addData(@RequestBody Ilce Ilce) {
        this.service.add(Ilce);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Ilce")
    public ResponseEntity<Boolean> updateData(@RequestBody Ilce Ilce) {
        this.service.update(Ilce);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Ilce")
    public ResponseEntity<Boolean> deleteData(@RequestBody Ilce Ilce) {
        this.service.remove(Ilce);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
