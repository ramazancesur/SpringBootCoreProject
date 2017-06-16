package com.stok.ramazan.controller;

import com.stok.ramazan.entity.OrderDetail;
import com.stok.ramazan.service.interfaces.IOrderDetailService;
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
public class OrderDetailController extends BaseController<OrderDetail> {
  @Autowired
  private IOrderDetailService service;

  @GetMapping("/OrderDetail/all")
  public ResponseEntity<List<OrderDetail>> getAll() {
    List<OrderDetail> lstOrderDetail = this.service.getAll();
    return new ResponseEntity<List<OrderDetail>>(lstOrderDetail, HttpStatus.OK);
  }

  @GetMapping(value = "/OrderDetail/{id}")
  public ResponseEntity<OrderDetail> getDataById(@PathVariable("id") Long id) {
    OrderDetail OrderDetail = this.service.get(id);
    return new ResponseEntity<OrderDetail>(OrderDetail, HttpStatus.OK);
  }

  @PostMapping(value = "/OrderDetail")
  public ResponseEntity<Boolean> addData(@RequestBody OrderDetail OrderDetail) {
    this.service.add(OrderDetail);
    return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
  }

  @PutMapping(value = "/OrderDetail")
  public ResponseEntity<Boolean> updateData(@RequestBody OrderDetail OrderDetail) {
    this.service.update(OrderDetail);
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

  @DeleteMapping(value = "/OrderDetail")
  public ResponseEntity<Boolean> deleteData(OrderDetail OrderDetail) {
    this.service.remove(OrderDetail);
    return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
  }
}
