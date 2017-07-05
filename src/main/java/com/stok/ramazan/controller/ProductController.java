package com.stok.ramazan.controller;

import com.stok.ramazan.android.dto.UrunDTO;
import com.stok.ramazan.entity.Product;
import com.stok.ramazan.service.interfaces.IProductService;
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

import java.util.List;

@RestController
public class ProductController extends BaseController<Product> {
    @Autowired
    private IProductService service;

    @GetMapping("/Product/all")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> lstProduct = this.service.getAll();
        return new ResponseEntity<List<Product>>(lstProduct, HttpStatus.OK);
    }

    @GetMapping("/Product/UrunDTO/all")
    public ResponseEntity<List<UrunDTO>> getUrunList() {
        List<UrunDTO> lstUrundto = this.service.getAllUrun();
        return new ResponseEntity(lstUrundto, HttpStatus.OK);
    }

    @GetMapping(value = "/Product/UrunDTO/{id}")
    public ResponseEntity<UrunDTO> getUrunById(@PathVariable("id") Long id) {
        UrunDTO urunDTO = this.service.getUrunDTO(id);
        return new ResponseEntity<UrunDTO>(urunDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/Product/{id}")
    public ResponseEntity<Product> getDataById(@PathVariable("id") Long id) {
        Product Product = this.service.get(id);
        return new ResponseEntity<Product>(Product, HttpStatus.OK);
    }


    @PostMapping(value = "/Product")
    public ResponseEntity<Boolean> addData(@RequestBody Product Product) {
        this.service.add(Product);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PostMapping(value = "/Product/UrunDTO")
    public ResponseEntity<UrunDTO> addUrunDTO(@RequestBody UrunDTO urunDTO) {
        this.service.addUrunDTO(urunDTO);
        return new ResponseEntity<UrunDTO>(urunDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Product/UrunDTO")
    public ResponseEntity<UrunDTO> updateUrunDTO(@RequestBody UrunDTO urunDTO) {
        this.service.updateUrunDTO(urunDTO);
        return new ResponseEntity<UrunDTO>(urunDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Product")
    public ResponseEntity<Boolean> updateData(@RequestBody Product Product) {
        this.service.update(Product);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Product/UrunDTO")
    public ResponseEntity<Boolean> deleteUrun(@RequestBody UrunDTO urunDTO) {
        return new ResponseEntity<Boolean>(this.service.deleteUrunDTO(urunDTO.getOid()), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/Product")
    public ResponseEntity<Boolean> deleteData(@RequestBody Product Product) {
        this.service.remove(Product);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
