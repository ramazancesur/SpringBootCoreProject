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

import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.service.interfaces.IFirmaService;

@RestController
public class FirmaController {
	@Autowired
	private IFirmaService service;

	@GetMapping("/FirmaList")
	public ResponseEntity<List<Firma>> getKonusmaciList() {
		List<Firma> lstFirma = this.service.getAll();
		return new ResponseEntity<List<Firma>>(lstFirma, HttpStatus.OK);
	}

	@GetMapping(value = "/FirmaList/{id}")
	public ResponseEntity<Firma> getKonusmaciById(@PathVariable("id") Long id) {
		Firma Firma = this.service.get(id);
		return new ResponseEntity<Firma>(Firma, HttpStatus.OK);
	}

	@PostMapping(value = "/Firma")
	public ResponseEntity<Firma> addKonusmaci(@RequestBody Firma Firma) {
		this.service.add(Firma);
		return new ResponseEntity<Firma>(Firma, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Firma")
	public ResponseEntity<Firma> updateKonusmaci(@PathVariable Long id, @RequestBody Firma Firma) {
		this.service.update(Firma);
		return new ResponseEntity<Firma>(Firma, HttpStatus.OK);
	}

	@DeleteMapping(value = "/Firma")
	public ResponseEntity<Boolean> deleteEtkinlik(Firma Firma) {
		this.service.remove(Firma);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
