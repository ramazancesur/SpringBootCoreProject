package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Conduct;
import com.stok.ramazan.service.interfaces.IConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConductController {
    @Autowired
    private IConductService service;

    @GetMapping("/ConductList")
    public ResponseEntity<List<Conduct>> getKonusmaciList() {
        List<Conduct> lstConduct = this.service.getAll();
        return new ResponseEntity<List<Conduct>>(lstConduct, HttpStatus.OK);
    }

    @GetMapping(value = "/ConductList/{id}")
    public ResponseEntity<Conduct> getKonusmaciById(@PathVariable("id") Long id) {
        Conduct Conduct = this.service.get(id);
        return new ResponseEntity<Conduct>(Conduct, HttpStatus.OK);
    }

    @PostMapping(value = "/Conduct")
    public ResponseEntity<Conduct> addKonusmaci(@RequestBody Conduct Conduct) {
        this.service.add(Conduct);
        return new ResponseEntity<Conduct>(Conduct, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Conduct")
    public ResponseEntity<Conduct> updateKonusmaci(@PathVariable Long id, @RequestBody Conduct Conduct) {
        this.service.update(Conduct);
        return new ResponseEntity<Conduct>(Conduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Conduct")
    public ResponseEntity<Boolean> deleteEtkinlik(Conduct Conduct) {
        this.service.remove(Conduct);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
