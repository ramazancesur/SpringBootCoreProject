package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Price;
import com.stok.ramazan.service.interfaces.IPriceService;
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
public class PriceController extends BaseController<Price> {
  @Autowired
  private IPriceService service;

  @GetMapping("/Price")
  public ResponseEntity<List<Price>> getAll() {
    List<Price> lstPrice = this.service.getAll();
    return new ResponseEntity<List<Price>>(lstPrice, HttpStatus.OK);
  }

  @GetMapping(value = "/Price/{id}")
  public ResponseEntity<Price> getDataById(@PathVariable("id") Long id) {
    Price Price = this.service.get(id);
    return new ResponseEntity<Price>(Price, HttpStatus.OK);
  }

  @PostMapping(value = "/Price")
  public ResponseEntity<Boolean> addData(@RequestBody Price Price) {
    this.service.add(Price);
    return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
  }

  @PutMapping(value = "/Price")
  public ResponseEntity<Boolean> updateData(@RequestBody Price Price) {
    this.service.update(Price);
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

  @DeleteMapping(value = "/Price")
  public ResponseEntity<Boolean> deleteData(Price Price) {
    this.service.remove(Price);
    return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
  }
}
