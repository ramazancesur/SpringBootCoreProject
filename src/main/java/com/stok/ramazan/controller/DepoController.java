package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Depo;
import com.stok.ramazan.service.interfaces.IDepoService;
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
public class DepoController extends BaseController<Depo> {
  @Autowired
  private IDepoService service;

  @GetMapping("/Depo/all")
  public ResponseEntity<List<Depo>> getAll() {
    List<Depo> lstDepo = this.service.getAll();
    return new ResponseEntity<List<Depo>>(lstDepo, HttpStatus.OK);
  }

  @GetMapping(value = "/Depo/{id}")
  public ResponseEntity<Depo> getDataById(@PathVariable("id") Long id) {
    Depo Depo = this.service.get(id);
    return new ResponseEntity<Depo>(Depo, HttpStatus.OK);
  }

  @PostMapping(value = "/Depo")
  public ResponseEntity<Boolean> addData(@RequestBody Depo Depo) {
    this.service.add(Depo);
    return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
  }

  @PutMapping(value = "/Depo")
  public ResponseEntity<Boolean> updateData(@RequestBody Depo Depo) {
    this.service.update(Depo);
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

  @DeleteMapping(value = "/Depo")
  public ResponseEntity<Boolean> deleteData(Depo Depo) {
    this.service.remove(Depo);
    return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
  }
}
