package com.stok.ramazan.controller;

import com.stok.ramazan.entity.BorcDetay;
import com.stok.ramazan.service.interfaces.IBorcDetayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorcDetayController extends BaseController {
    @Autowired
    private IBorcDetayService service;

    @GetMapping("/BorcDetayList")
    public ResponseEntity<List<BorcDetay>> getKonusmaciList() {
        List<BorcDetay> lstBorcDetay = this.service.getAll();
        return new ResponseEntity<List<BorcDetay>>(lstBorcDetay, HttpStatus.OK);
    }

    @GetMapping(value = "/BorcDetayList/{id}")
    public ResponseEntity<BorcDetay> getKonusmaciById(@PathVariable("id") Long id) {
        BorcDetay BorcDetay = this.service.get(id);
        return new ResponseEntity<BorcDetay>(BorcDetay, HttpStatus.OK);
    }

    @PostMapping(value = "/BorcDetay")
    public ResponseEntity<BorcDetay> addKonusmaci(@RequestBody BorcDetay BorcDetay) {
        this.service.add(BorcDetay);
        return new ResponseEntity<BorcDetay>(BorcDetay, HttpStatus.CREATED);
    }

    @PutMapping(value = "/BorcDetay")
    public ResponseEntity<BorcDetay> updateKonusmaci(@PathVariable Long id, @RequestBody BorcDetay BorcDetay) {
        this.service.update(BorcDetay);
        return new ResponseEntity<BorcDetay>(BorcDetay, HttpStatus.OK);
    }

    @DeleteMapping(value = "/BorcDetay")
    public ResponseEntity<Boolean> deleteEtkinlik(BorcDetay BorcDetay) {
        this.service.remove(BorcDetay);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
