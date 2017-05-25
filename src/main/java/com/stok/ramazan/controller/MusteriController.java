package com.stok.ramazan.controller;

import com.stok.ramazan.dto.MusteriDTO;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.service.interfaces.IMusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MusteriController extends BaseController {
    @Autowired
    private IMusteriService service;

    @GetMapping("/MusteriList")
    public ResponseEntity<List<Musteri>> getMusteriList() {
        List<Musteri> lstMusteri = this.service.getAll();
        return new ResponseEntity<List<Musteri>>(lstMusteri, HttpStatus.OK);
    }

    @GetMapping("/MusteriList/MusteriDTOList")
    public ResponseEntity<List<MusteriDTO>> getAllMusteri() {
        List<MusteriDTO> lstMusteri = this.service.getAllMusteriDTO();
        return new ResponseEntity(lstMusteri, HttpStatus.OK);
    }

    @GetMapping(value = "/MusteriList/{id}")
    public ResponseEntity<Musteri> getKonusmaciById(@PathVariable("id") Long id) {
        Musteri Musteri = this.service.get(id);
        return new ResponseEntity<Musteri>(Musteri, HttpStatus.OK);
    }

    @GetMapping(value = "/MusteriList/musteriDTO/{id}")
    public ResponseEntity<MusteriDTO> getMusteriDTOById(@PathVariable("id") Long id) {
        MusteriDTO musteriDTO = this.service.getMusteriDTO(id);
        return new ResponseEntity(musteriDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/Musteri")
    public ResponseEntity<Musteri> addKonusmaci(@RequestBody Musteri Musteri) {
        this.service.add(Musteri);
        return new ResponseEntity<Musteri>(Musteri, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Musteri")
    public ResponseEntity<Musteri> updateKonusmaci(@PathVariable Long id, @RequestBody Musteri Musteri) {
        this.service.update(Musteri);
        return new ResponseEntity<Musteri>(Musteri, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Musteri")
    public ResponseEntity<Boolean> deleteEtkinlik(Musteri Musteri) {

        this.service.remove(Musteri);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/Musteri/MusteriDTO")
    public ResponseEntity<Boolean> deleteMusteriDto(MusteriDTO musteriDTO) {
        return new ResponseEntity<Boolean>(this.service.deleteMusteriDto(musteriDTO.getOid()), HttpStatus.NO_CONTENT);
    }
}
