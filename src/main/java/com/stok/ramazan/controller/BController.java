package com.stok.ramazan.controller;

import com.stok.ramazan.entity.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by LocalAdmin on 02.06.2017.
 */
public interface BController<T extends BaseEntity> {
  ResponseEntity<List<T>> getAll();

  ResponseEntity<T> getDataById(@PathVariable("id") Long id);

  ResponseEntity<Boolean> addData(@RequestBody T data);

  ResponseEntity<Boolean> updateData(@RequestBody T data);

  ResponseEntity<Boolean> deleteData(@RequestBody T data);
}