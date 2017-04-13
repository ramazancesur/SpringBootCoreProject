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

import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.service.interfaces.IBorcService;

@RestController
public class BorcController extends BaseController {
	@Autowired
	private IBorcService service;

	@GetMapping("/BorcList")
	public ResponseEntity<List<Borc>> getKonusmaciList() {
		List<Borc> lstBorc = this.service.getAll();
		return new ResponseEntity<List<Borc>>(lstBorc, HttpStatus.OK);
	}

	@GetMapping(value = "/BorcList/{id}")
	public ResponseEntity<Borc> getKonusmaciById(@PathVariable("id") Long id) {
		Borc Borc = this.service.get(id);
		return new ResponseEntity<Borc>(Borc, HttpStatus.OK);
	}

	@PostMapping(value = "/Borc")
	public ResponseEntity<Borc> addKonusmaci(@RequestBody Borc Borc) {
		this.service.add(Borc);
		return new ResponseEntity<Borc>(Borc, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Borc")
	public ResponseEntity<Borc> updateKonusmaci(@PathVariable Long id, @RequestBody Borc Borc) {
		this.service.update(Borc);
		return new ResponseEntity<Borc>(Borc, HttpStatus.OK);
	}

	@DeleteMapping(value = "/Borc")
	public ResponseEntity<Boolean> deleteEtkinlik(Borc Borc) {
		this.service.remove(Borc);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
