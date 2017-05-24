package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Taksit;
import com.stok.ramazan.service.interfaces.ITaksitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaksitController extends BaseController {
    @Autowired
    private ITaksitService service;

    @GetMapping("/TaksitList")
    public ResponseEntity<List<Taksit>> getKonusmaciList() {
        List<Taksit> lstTaksit = this.service.getAll();
        return new ResponseEntity<List<Taksit>>(lstTaksit, HttpStatus.OK);
    }

    @GetMapping(value = "/TaksitList/{id}")
    public ResponseEntity<Taksit> getKonusmaciById(@PathVariable("id") Long id) {
        Taksit Taksit = this.service.get(id);
        return new ResponseEntity<Taksit>(Taksit, HttpStatus.OK);
    }

    @PostMapping(value = "/Taksit")
    public ResponseEntity<Taksit> addKonusmaci(@RequestBody Taksit Taksit) {
        this.service.add(Taksit);
        return new ResponseEntity<Taksit>(Taksit, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Taksit")
    public ResponseEntity<Taksit> updateKonusmaci(@PathVariable Long id, @RequestBody Taksit Taksit) {
        this.service.update(Taksit);
        return new ResponseEntity<Taksit>(Taksit, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Taksit")
    public ResponseEntity<Boolean> deleteEtkinlik(Taksit Taksit) {
        this.service.remove(Taksit);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
