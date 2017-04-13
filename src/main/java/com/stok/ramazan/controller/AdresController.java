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

import com.stok.ramazan.entity.Address;
import com.stok.ramazan.service.interfaces.IAddresService;

@RestController
public class AdresController extends BaseController {
	@Autowired
	private IAddresService service;

	@GetMapping("/AddressList")
	public ResponseEntity<List<Address>> getKonusmaciList() {
		List<Address> lstAddress = this.service.getAll();
		return new ResponseEntity<List<Address>>(lstAddress, HttpStatus.OK);
	}

	@GetMapping(value = "/AddressList/{id}")
	public ResponseEntity<Address> getKonusmaciById(@PathVariable("id") Long id) {
		Address Address = this.service.get(id);
		return new ResponseEntity<Address>(Address, HttpStatus.OK);
	}

	@PostMapping(value = "/Address")
	public ResponseEntity<Address> addKonusmaci(@RequestBody Address Address) {
		this.service.add(Address);
		return new ResponseEntity<Address>(Address, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Address")
	public ResponseEntity<Address> updateKonusmaci(@PathVariable Long id, @RequestBody Address Address) {
		this.service.update(Address);
		return new ResponseEntity<Address>(Address, HttpStatus.OK);
	}

	@DeleteMapping(value = "/Address")
	public ResponseEntity<Boolean> deleteEtkinlik(Address Address) {
		this.service.remove(Address);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}