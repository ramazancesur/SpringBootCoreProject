package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Lisans;
import com.stok.ramazan.service.interfaces.ILisansService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LocalAdmin on 12.06.2017.
 */
@RestController
public class LisansController extends BaseController<Lisans> {
  private static final Logger LOGGER = LoggerFactory.getLogger(LisansController.class);


  @Autowired
  private ILisansService lisansService;


  @Override
  @GetMapping("/Lisans/all")
  public ResponseEntity<List<Lisans>> getAll() {
    List<Lisans> lstLisans = lisansService.getAll();
    return new ResponseEntity<List<Lisans>>(lstLisans, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/Lisans/{id}")
  public ResponseEntity<Lisans> getDataById(Long id) {
    Lisans lisans = lisansService.get(id);
    return new ResponseEntity<Lisans>(lisans, HttpStatus.OK);
  }

  @Override
  @PostMapping(value = "/Lisans")
  public ResponseEntity<Boolean> addData(Lisans data) {
    boolean flag = false;
    try {
      lisansService.add(data);
      flag = true;
    } catch (Exception ex) {
      LOGGER.error("LisansController data eklenirken hata meydana geldi " + ex.getMessage());
      ex.printStackTrace();
    }
    return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
  }

  @Override
  @PutMapping("/Lisans")
  public ResponseEntity<Boolean> updateData(Lisans data) {
    boolean flag = false;
    try {
      lisansService.update(data);
      flag = true;
    } catch (Exception ex) {
      LOGGER.error("LisansController data guncellenirken hata meydana geldi " + ex.getMessage());
      ex.printStackTrace();
    }
    return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
  }

  @Override
  @DeleteMapping("Lisans")
  public ResponseEntity<Boolean> deleteData(Lisans data) {
    boolean flag = false;
    try {
      lisansService.remove(data);
      flag = true;
    } catch (Exception ex) {
      LOGGER.error("LisansController data silinirken hata meydana geldi " + ex.getMessage());
      ex.printStackTrace();
    }
    return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
  }
}
