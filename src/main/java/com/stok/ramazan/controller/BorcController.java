package com.stok.ramazan.controller;

import com.stok.ramazan.android.dto.SiparisListesiDTO;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.service.interfaces.IBorcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorcController extends BaseController<Borc> {
    @Autowired
    private IBorcService service;

    @GetMapping("/Borc/all")
    public ResponseEntity<List<Borc>> getAll() {
        List<Borc> lstBorc = this.service.getAll();
        return new ResponseEntity<List<Borc>>(lstBorc, HttpStatus.OK);
    }

    @GetMapping("/Borc/SiparisListesiDTO/all")
    public ResponseEntity<List<SiparisListesiDTO>> getSiparisListesi() {
        List<SiparisListesiDTO> lstSiparisListesi = this.service.getAllSiparis();
        return new ResponseEntity(lstSiparisListesi, HttpStatus.OK);
    }

    @GetMapping(value = "/Borc/SiparisListesiDTO/{id}")
    public ResponseEntity<SiparisListesiDTO> getSiparisListesiiById(@PathVariable("id") Long id) {
        SiparisListesiDTO siparisListesiDTO = this.service.getSiparis(id);
        return new ResponseEntity(siparisListesiDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/Borc/{id}")
    public ResponseEntity<Borc> getDataById(@PathVariable("id") Long id) {
        Borc Borc = this.service.get(id);
        return new ResponseEntity<Borc>(Borc, HttpStatus.OK);
    }

    @PostMapping(value = "/Borc")
    public ResponseEntity<Boolean> addData(@RequestBody Borc Borc) {
        this.service.update(Borc);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PostMapping(value = "/Borc/SiparisListesiDTO")
    public ResponseEntity<SiparisListesiDTO> addSiparisListesi(@RequestBody SiparisListesiDTO siparisListesiDTO) {
        this.service.updateSiparis(siparisListesiDTO);
        return new ResponseEntity(siparisListesiDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Borc/SiparisListesiDTO")
    public ResponseEntity<SiparisListesiDTO> updateSiparisListesi(@RequestBody SiparisListesiDTO siparisListesiDTO) {
        this.service.addSiparis(siparisListesiDTO);
        return new ResponseEntity(siparisListesiDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Borc")
    public ResponseEntity<Boolean> updateData(@RequestBody Borc Borc) {
        this.service.add(Borc);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    @DeleteMapping(value = "/Borc/SiparisListesiDTO")
    public ResponseEntity<Boolean> deleteSiparisListesi(@RequestBody SiparisListesiDTO siparisListesiDTO) {
        this.service.deleteSiparis(siparisListesiDTO.getOid());
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/Borc")
    public ResponseEntity<Boolean> deleteData(@RequestBody Borc Borc) {
        this.service.remove(Borc);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}