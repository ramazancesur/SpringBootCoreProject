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

import com.stok.ramazan.entity.Role;
import com.stok.ramazan.service.interfaces.IRoleService;

@RestController("/role")
public class RoleController {
	@Autowired
	private IRoleService service;
	
	@GetMapping("/roleList")
	public ResponseEntity<List<Role>> getKonusmaciList() {
		List<Role> lstRole = this.service.getAll();
		return new ResponseEntity<List<Role>>(lstRole, HttpStatus.OK);
	}

	@GetMapping(value = "/roleList/{id}")
	public ResponseEntity<Role> getKonusmaciById(@PathVariable("id") Long id) {
		Role role = this.service.get(id);
		return new ResponseEntity<Role>(role, HttpStatus.OK);		
	}

	@PostMapping(value = "/role")
	public ResponseEntity<Role> addKonusmaci(@RequestBody Role role) {
		this.service.add(role);
		return new ResponseEntity<Role>(role,HttpStatus.CREATED);
	}

	@PutMapping(value = "/role/{id}")
	public ResponseEntity<Role> updateKonusmaci(@PathVariable Long id, @RequestBody Role role) {
		this.service.update(role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@DeleteMapping(value = "/role/{id}")
	public ResponseEntity<Boolean> deleteEtkinlik( Role role) {
		this.service.remove(role);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}