package com.stok.ramazan.controller;

import com.stok.ramazan.dto.UrunDTO;
import com.stok.ramazan.entity.Product;
import com.stok.ramazan.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController extends BaseController {
    @Autowired
    private IProductService service;

    @GetMapping("/ProductList")
    public ResponseEntity<List<Product>> getKonusmaciList() {
        List<Product> lstProduct = this.service.getAll();
        return new ResponseEntity<List<Product>>(lstProduct, HttpStatus.OK);
    }

    @GetMapping("/ProductList/Urun")
    public ResponseEntity<List<UrunDTO>> getUrunList() {
        List<UrunDTO> lstUrundto = this.service.getAllUrun();
        return new ResponseEntity(lstUrundto, HttpStatus.OK);
    }

    @GetMapping(value = "/ProductList/Urun/{id}")
    public ResponseEntity<UrunDTO> getUrunById(@PathVariable("id") Long id) {
        UrunDTO urunDTO = this.service.getUrunDTO(id);
        return new ResponseEntity<UrunDTO>(urunDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/ProductList/{id}")
    public ResponseEntity<Product> getKonusmaciById(@PathVariable("id") Long id) {
        Product Product = this.service.get(id);
        return new ResponseEntity<Product>(Product, HttpStatus.OK);
    }


    @PostMapping(value = "/Product")
    public ResponseEntity<Product> addKonusmaci(@RequestBody Product Product) {
        this.service.add(Product);
        return new ResponseEntity<Product>(Product, HttpStatus.CREATED);
    }

    @PostMapping(value = "/Product/Urun")
    public ResponseEntity<UrunDTO> addUrunDTO(@RequestBody UrunDTO urunDTO) {
        this.service.addUrunDTO(urunDTO);
        return new ResponseEntity<UrunDTO>(urunDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Product/Urun")
    public ResponseEntity<UrunDTO> updateUrunDTO(@RequestBody UrunDTO urunDTO) {
        this.service.updateUrunDTO(urunDTO);
        return new ResponseEntity<UrunDTO>(urunDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Product")
    public ResponseEntity<Product> updateKonusmaci(@PathVariable Long id, @RequestBody Product Product) {
        this.service.update(Product);
        return new ResponseEntity<Product>(Product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Product/Urun")
    public ResponseEntity<Boolean> deleteUrun(UrunDTO urunDTO) {
        return new ResponseEntity<Boolean>(this.service.deleteUrunDTO(urunDTO.getOid()), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/Product")
    public ResponseEntity<Boolean> deleteEtkinlik(Product Product) {
        this.service.remove(Product);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
