package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Regain;
import com.stok.ramazan.service.interfaces.IRegainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegainController extends BaseController {
    @Autowired
    private IRegainService service;

    @GetMapping("/RegainList")
    public ResponseEntity<List<Regain>> getKonusmaciList() {
        List<Regain> lstRegain = this.service.getAll();
        return new ResponseEntity<List<Regain>>(lstRegain, HttpStatus.OK);
    }

    @GetMapping(value = "/RegainList/{id}")
    public ResponseEntity<Regain> getKonusmaciById(@PathVariable("id") Long id) {
        Regain Regain = this.service.get(id);
        return new ResponseEntity<Regain>(Regain, HttpStatus.OK);
    }

    @PostMapping(value = "/Regain")
    public ResponseEntity<Regain> addKonusmaci(@RequestBody Regain Regain) {
        this.service.add(Regain);
        return new ResponseEntity<Regain>(Regain, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Regain")
    public ResponseEntity<Regain> updateKonusmaci(@PathVariable Long id, @RequestBody Regain Regain) {
        this.service.update(Regain);
        return new ResponseEntity<Regain>(Regain, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Regain")
    public ResponseEntity<Boolean> deleteEtkinlik(Regain Regain) {
        this.service.remove(Regain);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
