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

import com.stok.ramazan.entity.Ilce;
import com.stok.ramazan.service.interfaces.IIlceService;

@RestController
public class IlceController extends BaseController {
	@Autowired
	private IIlceService service;

	@GetMapping("/IlceList")
	public ResponseEntity<List<Ilce>> getKonusmaciList() {
		List<Ilce> lstIlce = this.service.getAll();
		return new ResponseEntity<List<Ilce>>(lstIlce, HttpStatus.OK);
	}

	@GetMapping(value = "/IlceList/{id}")
	public ResponseEntity<Ilce> getKonusmaciById(@PathVariable("id") Long id) {
		Ilce Ilce = this.service.get(id);
		return new ResponseEntity<Ilce>(Ilce, HttpStatus.OK);
	}

	@PostMapping(value = "/Ilce")
	public ResponseEntity<Ilce> addKonusmaci(@RequestBody Ilce Ilce) {
		this.service.add(Ilce);
		return new ResponseEntity<Ilce>(Ilce, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Ilce")
	public ResponseEntity<Ilce> updateKonusmaci(@PathVariable Long id, @RequestBody Ilce Ilce) {
		this.service.update(Ilce);
		return new ResponseEntity<Ilce>(Ilce, HttpStatus.OK);
	}

	@DeleteMapping(value = "/Ilce")
	public ResponseEntity<Boolean> deleteEtkinlik(Ilce Ilce) {
		this.service.remove(Ilce);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
