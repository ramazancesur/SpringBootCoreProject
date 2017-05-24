package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Il;
import com.stok.ramazan.service.interfaces.IIlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IlController extends BaseController {
    @Autowired
    private IIlService service;

    @GetMapping("/IlList")
    public ResponseEntity<List<Il>> getKonusmaciList() {
        List<Il> lstIl = this.service.getAll();
        return new ResponseEntity<List<Il>>(lstIl, HttpStatus.OK);
    }

    @GetMapping(value = "/IlList/{id}")
    public ResponseEntity<Il> getKonusmaciById(@PathVariable("id") Long id) {
        Il Il = this.service.get(id);
        return new ResponseEntity<Il>(Il, HttpStatus.OK);
    }

    @PostMapping(value = "/Il")
    public ResponseEntity<Il> addKonusmaci(@RequestBody Il Il) {
        this.service.add(Il);
        return new ResponseEntity<Il>(Il, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Il")
    public ResponseEntity<Il> updateKonusmaci(@PathVariable Long id, @RequestBody Il Il) {
        this.service.update(Il);
        return new ResponseEntity<Il>(Il, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Il")
    public ResponseEntity<Boolean> deleteEtkinlik(Il Il) {
        this.service.remove(Il);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}