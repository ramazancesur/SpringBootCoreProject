package com.stok.ramazan.controller;

import com.stok.ramazan.entity.UstleniciFirma;
import com.stok.ramazan.service.interfaces.IUstleniciFirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UstleniciFirmaController extends BaseController<UstleniciFirma> {
    @Autowired
    private IUstleniciFirmaService service;

    @GetMapping("/UstleniciFirma")
    public ResponseEntity<List<UstleniciFirma>> getAll() {
        List<UstleniciFirma> lstUstleniciFirma = this.service.getAll();
        return new ResponseEntity<List<UstleniciFirma>>(lstUstleniciFirma, HttpStatus.OK);
    }

    @GetMapping(value = "/UstleniciFirma/{id}")
    public ResponseEntity<UstleniciFirma> getDataById(@PathVariable("id") Long id) {
        UstleniciFirma UstleniciFirma = this.service.get(id);
        return new ResponseEntity<UstleniciFirma>(UstleniciFirma, HttpStatus.OK);
    }

    @PostMapping(value = "/UstleniciFirma")
    public ResponseEntity<Boolean> addData(@RequestBody UstleniciFirma UstleniciFirma) {
        this.service.add(UstleniciFirma);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/UstleniciFirma")
    public ResponseEntity<Boolean> updateData(@RequestBody UstleniciFirma UstleniciFirma) {
        this.service.update(UstleniciFirma);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/UstleniciFirma")
    public ResponseEntity<Boolean> deleteData(UstleniciFirma UstleniciFirma) {
        this.service.remove(UstleniciFirma);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
