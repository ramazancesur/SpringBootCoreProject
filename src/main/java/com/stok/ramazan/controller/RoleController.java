package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Role;
import com.stok.ramazan.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController extends BaseController<Role> {
    @Autowired
    private IRoleService service;

    @GetMapping("/Role/all")
    public ResponseEntity<List<Role>> getAll() {
        List<Role> lstRole = this.service.getAll();
        return new ResponseEntity<List<Role>>(lstRole, HttpStatus.OK);
    }

    @GetMapping(value = "/Role/{id}")
    public ResponseEntity<Role> getDataById(@PathVariable("id") Long id) {
        Role role = this.service.get(id);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    @PostMapping(value = "/Role")
    public ResponseEntity<Boolean> addData(@RequestBody Role role) {
        this.service.add(role);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Role")
    public ResponseEntity<Boolean> updateData(@RequestBody Role role) {
        this.service.update(role);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Role")
    public ResponseEntity<Boolean> deleteData(Role role) {
        this.service.remove(role);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}