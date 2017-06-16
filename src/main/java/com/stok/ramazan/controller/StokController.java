package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Stok;
import com.stok.ramazan.service.interfaces.IStokService;
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
public class StokController extends BaseController<Stok> {
  @Autowired
  private IStokService service;

  @GetMapping("/Stok/all")
  public ResponseEntity<List<Stok>> getAll() {
    List<Stok> lstStok = this.service.getAll();
    return new ResponseEntity<List<Stok>>(lstStok, HttpStatus.OK);
  }

  @GetMapping(value = "/Stok/{id}")
  public ResponseEntity<Stok> getDataById(@PathVariable("id") Long id) {
    Stok Stok = this.service.get(id);
    return new ResponseEntity<Stok>(Stok, HttpStatus.OK);
  }

  @PostMapping(value = "/Stok")
  public ResponseEntity<Boolean> addData(@RequestBody Stok Stok) {
    this.service.add(Stok);
    return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
  }

  @PutMapping(value = "/Stok")
  public ResponseEntity<Boolean> updateData(@RequestBody Stok Stok) {
    this.service.update(Stok);
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

  @DeleteMapping(value = "/Stok")
  public ResponseEntity<Boolean> deleteData(Stok Stok) {
    this.service.remove(Stok);
    return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
  }
}
