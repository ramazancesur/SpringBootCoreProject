package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Taksit;
import com.stok.ramazan.service.interfaces.ITaksitService;
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
public class TaksitController extends BaseController<Taksit> {
  @Autowired
  private ITaksitService service;

  @GetMapping("/Taksit/all")
  public ResponseEntity<List<Taksit>> getAll() {
    List<Taksit> lstTaksit = this.service.getAll();
    return new ResponseEntity<List<Taksit>>(lstTaksit, HttpStatus.OK);
  }

  @GetMapping(value = "/Taksit/{id}")
  public ResponseEntity<Taksit> getDataById(@PathVariable("id") Long id) {
    Taksit Taksit = this.service.get(id);
    return new ResponseEntity<Taksit>(Taksit, HttpStatus.OK);
  }

  @PostMapping(value = "/Taksit")
  public ResponseEntity<Boolean> addData(@RequestBody Taksit Taksit) {
    this.service.add(Taksit);
    return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
  }

  @PutMapping(value = "/Taksit")
  public ResponseEntity<Boolean> updateData(@RequestBody Taksit Taksit) {
    this.service.update(Taksit);
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

  @DeleteMapping(value = "/Taksit")
  public ResponseEntity<Boolean> deleteData(Taksit Taksit) {
    this.service.remove(Taksit);
    return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
  }
}
