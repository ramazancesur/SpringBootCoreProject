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

import com.stok.ramazan.entity.Order;
import com.stok.ramazan.service.interfaces.IOrderService;

@RestController
public class OrderController extends BaseController {
	@Autowired
	private IOrderService service;

	@GetMapping("/OrderList")
	public ResponseEntity<List<Order>> getKonusmaciList() {
		List<Order> lstOrder = this.service.getAll();
		return new ResponseEntity<List<Order>>(lstOrder, HttpStatus.OK);
	}

	@GetMapping(value = "/OrderList/{id}")
	public ResponseEntity<Order> getKonusmaciById(@PathVariable("id") Long id) {
		Order Order = this.service.get(id);
		return new ResponseEntity<Order>(Order, HttpStatus.OK);
	}

	@PostMapping(value = "/Order")
	public ResponseEntity<Order> addKonusmaci(@RequestBody Order Order) {
		this.service.add(Order);
		return new ResponseEntity<Order>(Order, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Order")
	public ResponseEntity<Order> updateKonusmaci(@PathVariable Long id, @RequestBody Order Order) {
		this.service.update(Order);
		return new ResponseEntity<Order>(Order, HttpStatus.OK);
	}

	@DeleteMapping(value = "/Order")
	public ResponseEntity<Boolean> deleteEtkinlik(Order Order) {
		this.service.remove(Order);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
