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

import com.stok.ramazan.entity.User;
import com.stok.ramazan.service.interfaces.IUserService;

@RestController("/user")
public class UserController {
	@Autowired
	private IUserService service;

	@GetMapping("/userList")
	public ResponseEntity<List<User>> getKonusmaciList() {
		List<User> lstUser = this.service.getAll();
		return new ResponseEntity<List<User>>(lstUser, HttpStatus.OK);
	}

	@GetMapping(value = "/userList/{id}")
	public ResponseEntity<User> getKonusmaciById(@PathVariable("id") Long id) {
		User User = this.service.get(id);
		return new ResponseEntity<User>(User, HttpStatus.OK);
	}

	@PostMapping(value = "/user")
	public ResponseEntity<User> addKonusmaci(@RequestBody User User) {
		this.service.add(User);
		return new ResponseEntity<User>(User, HttpStatus.CREATED);
	}

	@PutMapping(value = "/user/{id}")
	public ResponseEntity<User> updateKonusmaci(@PathVariable Long id, @RequestBody User User) {
		this.service.update(User);
		return new ResponseEntity<User>(User, HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Boolean> deleteEtkinlik(User User) {
		this.service.remove(User);
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
}
