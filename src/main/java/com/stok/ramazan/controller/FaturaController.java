package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Fatura;
import com.stok.ramazan.service.interfaces.IFaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ramazan CESUR on 02.06.2017.
 */
@Controller
public class FaturaController implements BController<Fatura> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FaturaController.class);
    @Autowired
    private IFaturaService faturaService;

    @Override
    @GetMapping("/fatura/faturaList")
    public ResponseEntity<List<Fatura>> getAll() {
        List<Fatura> lstFatura = faturaService.getAll();
        return new ResponseEntity<List<Fatura>>(lstFatura, HttpStatus.OK);
    }

    @Override
    @GetMapping("/fatura/{id}")
    public ResponseEntity<Fatura> getDataById(@PathVariable("id") Long id) {
        Fatura fatura = faturaService.get(id);
        return new ResponseEntity<Fatura>(fatura, HttpStatus.OK);
    }

    @Override
    @PostMapping("/fatura")
    public ResponseEntity<Boolean> addData(@RequestBody Fatura data) {
        try {
            faturaService.add(data);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("hata meydana geldi" + ex.getMessage());
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PutMapping("/fatura")
    public ResponseEntity<Boolean> updateData(@RequestBody Fatura data) {
        try {
            faturaService.update(data);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("hata meydana geldi" + ex.getMessage());
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/fatura")
    public ResponseEntity<Boolean> deleteData(Fatura data) {
        try {
            faturaService.remove(data);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("hata meydana geldi" + ex.getMessage());
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

}