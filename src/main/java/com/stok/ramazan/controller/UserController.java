package com.stok.ramazan.controller;

import com.stok.ramazan.entity.User;
import com.stok.ramazan.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends BaseController<User> {
    @Autowired
    private IUserService service;

    @GetMapping("/User/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> lstUser = this.service.getAll();
        return new ResponseEntity<List<User>>(lstUser, HttpStatus.OK);
    }

    @GetMapping(value = "/User/{id}")
    public ResponseEntity<User> getDataById(@PathVariable("id") Long id) {
        User User = this.service.get(id);
        return new ResponseEntity<User>(User, HttpStatus.OK);
    }

    @PostMapping(value = "/User")
    public ResponseEntity<Boolean> addData(@RequestBody User User) {
        this.service.add(User);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/User")
    public ResponseEntity<Boolean> updateData(@RequestBody User User) {
        this.service.update(User);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/User")
    public ResponseEntity<Boolean> deleteData(@RequestBody User User) {
        this.service.remove(User);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}