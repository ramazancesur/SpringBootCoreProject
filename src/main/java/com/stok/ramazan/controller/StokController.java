package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Stok;
import com.stok.ramazan.service.interfaces.IStokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StokController extends BaseController {
    @Autowired
    private IStokService service;

    @GetMapping("/StokList")
    public ResponseEntity<List<Stok>> getKonusmaciList() {
        List<Stok> lstStok = this.service.getAll();
        return new ResponseEntity<List<Stok>>(lstStok, HttpStatus.OK);
    }

    @GetMapping(value = "/StokList/{id}")
    public ResponseEntity<Stok> getKonusmaciById(@PathVariable("id") Long id) {
        Stok Stok = this.service.get(id);
        return new ResponseEntity<Stok>(Stok, HttpStatus.OK);
    }

    @PostMapping(value = "/Stok")
    public ResponseEntity<Stok> addKonusmaci(@RequestBody Stok Stok) {
        this.service.add(Stok);
        return new ResponseEntity<Stok>(Stok, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Stok")
    public ResponseEntity<Stok> updateKonusmaci(@PathVariable Long id, @RequestBody Stok Stok) {
        this.service.update(Stok);
        return new ResponseEntity<Stok>(Stok, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Stok")
    public ResponseEntity<Boolean> deleteEtkinlik(Stok Stok) {
        this.service.remove(Stok);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
