package com.stok.ramazan.controller;

import com.stok.ramazan.android.dto.SirketDTO;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.service.interfaces.IFirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirmaController extends BaseController<Firma> {
    @Autowired
    private IFirmaService service;

    @GetMapping("/Firma/all")
    public ResponseEntity<List<Firma>> getAll() {
        List<Firma> lstFirma = this.service.getAll();
        return new ResponseEntity<List<Firma>>(lstFirma, HttpStatus.OK);
    }

    @GetMapping("/Firma/Sirket")
    public ResponseEntity<List<SirketDTO>> getSirketDTOlist() {
        List<SirketDTO> lstSirketDTO = this.service.getAllSirket();
        return new ResponseEntity<List<SirketDTO>>(lstSirketDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/Firma/{id}")
    public ResponseEntity<Firma> getDataById(@PathVariable("id") Long id) {
        Firma Firma = this.service.get(id);
        return new ResponseEntity<Firma>(Firma, HttpStatus.OK);
    }


    @GetMapping(value = "/Firma/Sirket/{id}")
    public ResponseEntity<SirketDTO> getSirketById(@PathVariable("id") Long id) {
        SirketDTO sirketDTO = this.service.getSirket(id);
        return new ResponseEntity<SirketDTO>(sirketDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/Firma")
    public ResponseEntity<Boolean> addData(@RequestBody Firma Firma) {
        this.service.add(Firma);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PostMapping(value = "/Firma/Sirket")
    public ResponseEntity<SirketDTO> addSirket(@RequestBody SirketDTO sirketDTO) {
        this.service.addSirket(sirketDTO);
        return new ResponseEntity<SirketDTO>(sirketDTO, HttpStatus.CREATED);
    }


    @PutMapping(value = "/Firma")
    public ResponseEntity<Boolean> updateData(@RequestBody Firma Firma) {
        this.service.update(Firma);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(value = "/Firma/Sirket")
    public ResponseEntity<SirketDTO> updateSirket(@PathVariable Long id, @RequestBody SirketDTO sirketDTO) {
        this.service.updateSirket(sirketDTO);
        return new ResponseEntity<SirketDTO>(sirketDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Firma")
    public ResponseEntity<Boolean> deleteData(Firma Firma) {
        this.service.remove(Firma);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/Firma/Sirket")
    public ResponseEntity<Boolean> deleteSirket(SirketDTO sirketDTO) {
        return new ResponseEntity<Boolean>(this.service.deleteSirket(sirketDTO.getOid()), HttpStatus.NO_CONTENT);
    }
}
