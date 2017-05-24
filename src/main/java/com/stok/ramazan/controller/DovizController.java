package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Doviz;
import com.stok.ramazan.service.interfaces.IDovizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DovizController extends BaseController {
    @Autowired
    private IDovizService service;

    @GetMapping("/DovizList")
    public ResponseEntity<List<Doviz>> getKonusmaciList() {
        List<Doviz> lstDoviz = this.service.getAll();
        return new ResponseEntity<List<Doviz>>(lstDoviz, HttpStatus.OK);
    }

    @GetMapping(value = "/DovizList/{id}")
    public ResponseEntity<Doviz> getKonusmaciById(@PathVariable("id") Long id) {
        Doviz Doviz = this.service.get(id);
        return new ResponseEntity<Doviz>(Doviz, HttpStatus.OK);
    }

    @PostMapping(value = "/Doviz")
    public ResponseEntity<Doviz> addKonusmaci(@RequestBody Doviz Doviz) {
        this.service.add(Doviz);
        return new ResponseEntity<Doviz>(Doviz, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Doviz")
    public ResponseEntity<Doviz> updateKonusmaci(@PathVariable Long id, @RequestBody Doviz Doviz) {
        this.service.update(Doviz);
        return new ResponseEntity<Doviz>(Doviz, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Doviz")
    public ResponseEntity<Boolean> deleteEtkinlik(Doviz Doviz) {
        this.service.remove(Doviz);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
