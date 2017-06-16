package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Conduct;
import com.stok.ramazan.service.interfaces.IConductService;
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
public class ConductController extends BaseController<Conduct> {
  @Autowired
  private IConductService service;

  @GetMapping("/Conduct/all")
  public ResponseEntity<List<Conduct>> getAll() {
    List<Conduct> lstConduct = this.service.getAll();
    return new ResponseEntity<List<Conduct>>(lstConduct, HttpStatus.OK);
  }

  @GetMapping(value = "/Conduct/{id}")
  public ResponseEntity<Conduct> getDataById(@PathVariable("id") Long id) {
    Conduct Conduct = this.service.get(id);
    return new ResponseEntity<Conduct>(Conduct, HttpStatus.OK);
  }

  @PostMapping(value = "/Conduct")
  public ResponseEntity<Boolean> addData(@RequestBody Conduct Conduct) {
    this.service.add(Conduct);
    return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
  }

  @PutMapping(value = "/Conduct")
  public ResponseEntity<Boolean> updateData(@RequestBody Conduct Conduct) {
    this.service.update(Conduct);
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

  @DeleteMapping(value = "/Conduct")
  public ResponseEntity<Boolean> deleteData(Conduct Conduct) {
    this.service.remove(Conduct);
    return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
  }
}
