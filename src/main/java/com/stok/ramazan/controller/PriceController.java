package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Price;
import com.stok.ramazan.service.interfaces.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PriceController extends BaseController {
    @Autowired
    private IPriceService service;

    @GetMapping("/PriceList")
    public ResponseEntity<List<Price>> getKonusmaciList() {
        List<Price> lstPrice = this.service.getAll();
        return new ResponseEntity<List<Price>>(lstPrice, HttpStatus.OK);
    }

    @GetMapping(value = "/PriceList/{id}")
    public ResponseEntity<Price> getKonusmaciById(@PathVariable("id") Long id) {
        Price Price = this.service.get(id);
        return new ResponseEntity<Price>(Price, HttpStatus.OK);
    }

    @PostMapping(value = "/Price")
    public ResponseEntity<Price> addKonusmaci(@RequestBody Price Price) {
        this.service.add(Price);
        return new ResponseEntity<Price>(Price, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Price")
    public ResponseEntity<Price> updateKonusmaci(@PathVariable Long id, @RequestBody Price Price) {
        this.service.update(Price);
        return new ResponseEntity<Price>(Price, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Price")
    public ResponseEntity<Boolean> deleteEtkinlik(Price Price) {
        this.service.remove(Price);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}