package com.stok.ramazan.controller;

import com.stok.ramazan.entity.PriceDetay;
import com.stok.ramazan.service.interfaces.IPriceDetayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PriceDetayController extends BaseController {
    @Autowired
    private IPriceDetayService service;

    @GetMapping("/PriceDetayList")
    public ResponseEntity<List<PriceDetay>> getKonusmaciList() {
        List<PriceDetay> lstPriceDetay = this.service.getAll();
        return new ResponseEntity<List<PriceDetay>>(lstPriceDetay, HttpStatus.OK);
    }

    @GetMapping(value = "/PriceDetayList/{id}")
    public ResponseEntity<PriceDetay> getKonusmaciById(@PathVariable("id") Long id) {
        PriceDetay PriceDetay = this.service.get(id);
        return new ResponseEntity<PriceDetay>(PriceDetay, HttpStatus.OK);
    }

    @PostMapping(value = "/PriceDetay")
    public ResponseEntity<PriceDetay> addKonusmaci(@RequestBody PriceDetay PriceDetay) {
        this.service.add(PriceDetay);
        return new ResponseEntity<PriceDetay>(PriceDetay, HttpStatus.CREATED);
    }

    @PutMapping(value = "/PriceDetay")
    public ResponseEntity<PriceDetay> updateKonusmaci(@PathVariable Long id, @RequestBody PriceDetay PriceDetay) {
        this.service.update(PriceDetay);
        return new ResponseEntity<PriceDetay>(PriceDetay, HttpStatus.OK);
    }

    @DeleteMapping(value = "/PriceDetay")
    public ResponseEntity<Boolean> deleteEtkinlik(PriceDetay PriceDetay) {
        this.service.remove(PriceDetay);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
