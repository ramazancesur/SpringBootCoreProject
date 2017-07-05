package com.stok.ramazan.controller;

import com.stok.ramazan.entity.BorcDetay;
import com.stok.ramazan.service.interfaces.IBorcDetayService;
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
public class BorcDetayController extends BaseController<BorcDetay> {
    @Autowired
    private IBorcDetayService service;

    @GetMapping("/BorcDetay/all")
    public ResponseEntity<List<BorcDetay>> getAll() {
        List<BorcDetay> lstBorcDetay = this.service.getAll();
        return new ResponseEntity<List<BorcDetay>>(lstBorcDetay, HttpStatus.OK);
    }

    @GetMapping(value = "/BorcDetay/{id}")
    public ResponseEntity<BorcDetay> getDataById(@PathVariable("id") Long id) {
        BorcDetay BorcDetay = this.service.get(id);
        return new ResponseEntity<BorcDetay>(BorcDetay, HttpStatus.OK);
    }

    @PostMapping(value = "/BorcDetay")
    public ResponseEntity<Boolean> addData(@RequestBody BorcDetay BorcDetay) {
        this.service.add(BorcDetay);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/BorcDetay")
    public ResponseEntity<Boolean> updateData(@RequestBody BorcDetay BorcDetay) {
        this.service.update(BorcDetay);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/BorcDetay")
    public ResponseEntity<Boolean> deleteData(@RequestBody BorcDetay BorcDetay) {
        this.service.remove(BorcDetay);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
