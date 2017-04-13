package com.stok.ramazan.controller;

import java.util.List;

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

import com.stok.ramazan.entity.OrderDetail;
import com.stok.ramazan.service.interfaces.IOrderDetailService;

@RestController
public class OrderDetailController extends BaseController {
	@Autowired
	private IOrderDetailService service;

	@GetMapping("/OrderDetailList")
	public ResponseEntity<List<OrderDetail>> getKonusmaciList() {
		List<OrderDetail> lstOrderDetail = this.service.getAll();
		return new ResponseEntity<List<OrderDetail>>(lstOrderDetail, HttpStatus.OK);
	}

	@GetMapping(value = "/OrderDetailList/{id}")
	public ResponseEntity<OrderDetail> getKonusmaciById(@PathVariable("id") Long id) {
		OrderDetail OrderDetail = this.service.get(id);
		return new ResponseEntity<OrderDetail>(OrderDetail, HttpStatus.OK);
	}

	@PostMapping(value = "/OrderDetail")
	public ResponseEntity<OrderDetail> addKonusmaci(@RequestBody OrderDetail OrderDetail) {
		this.service.add(OrderDetail);
		return new ResponseEntity<OrderDetail>(OrderDetail, HttpStatus.CREATED);
	}

	@PutMapping(value = "/OrderDetail")
	public ResponseEntity<OrderDetail> updateKonusmaci(@PathVariable Long id, @RequestBody OrderDetail OrderDetail) {
		this.service.update(OrderDetail);
		return new ResponseEntity<OrderDetail>(OrderDetail, HttpStatus.OK);
	}

	@DeleteMapping(value = "/OrderDetail")
	public ResponseEntity<Boolean> deleteEtkinlik(OrderDetail OrderDetail) {
		this.service.remove(OrderDetail);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
