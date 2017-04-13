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

import com.stok.ramazan.entity.Depo;
import com.stok.ramazan.service.interfaces.IDepoService;

@RestController
public class DepoController extends BaseController {
	@Autowired
	private IDepoService service;

	@GetMapping("/DepoList")
	public ResponseEntity<List<Depo>> getKonusmaciList() {
		List<Depo> lstDepo = this.service.getAll();
		return new ResponseEntity<List<Depo>>(lstDepo, HttpStatus.OK);
	}

	@GetMapping(value = "/DepoList/{id}")
	public ResponseEntity<Depo> getKonusmaciById(@PathVariable("id") Long id) {
		Depo Depo = this.service.get(id);
		return new ResponseEntity<Depo>(Depo, HttpStatus.OK);
	}

	@PostMapping(value = "/Depo")
	public ResponseEntity<Depo> addKonusmaci(@RequestBody Depo Depo) {
		this.service.add(Depo);
		return new ResponseEntity<Depo>(Depo, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Depo")
	public ResponseEntity<Depo> updateKonusmaci(@PathVariable Long id, @RequestBody Depo Depo) {
		this.service.update(Depo);
		return new ResponseEntity<Depo>(Depo, HttpStatus.OK);
	}

	@DeleteMapping(value = "/Depo")
	public ResponseEntity<Boolean> deleteEtkinlik(Depo Depo) {
		this.service.remove(Depo);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
