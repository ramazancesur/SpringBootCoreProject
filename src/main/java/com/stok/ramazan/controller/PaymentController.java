package com.stok.ramazan.controller;

import com.stok.ramazan.android.dto.OdemeDTO;
import com.stok.ramazan.entity.Payment;
import com.stok.ramazan.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController extends BaseController<Payment> {
    @Autowired
    private IPaymentService service;

    @GetMapping("/Payment/all")
    public ResponseEntity<List<Payment>> getAll() {
        List<Payment> lstPayment = this.service.getAll();
        return new ResponseEntity<List<Payment>>(lstPayment, HttpStatus.OK);
    }


    @GetMapping("/Payment/OdemeDTO/all")
    public ResponseEntity<List<OdemeDTO>> getOdemeList() {
        List<OdemeDTO> lstPayment = this.service.getAllOdemeDTO();
        return new ResponseEntity<List<OdemeDTO>>(lstPayment, HttpStatus.OK);
    }

    @GetMapping(value = "/Payment/OdemeDTO/{id}")
    public ResponseEntity<OdemeDTO> getOdemeById(@PathVariable("id") Long id) {
        OdemeDTO odemeDTO = this.service.getOdemeDTO(id);
        return new ResponseEntity(odemeDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/Payment/{id}")
    public ResponseEntity<Payment> getDataById(@PathVariable("id") Long id) {
        Payment Payment = this.service.get(id);
        return new ResponseEntity<Payment>(Payment, HttpStatus.OK);
    }

    @PostMapping(value = "/Payment")
    public ResponseEntity<Boolean> addData(@RequestBody Payment Payment) {
        this.service.add(Payment);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PostMapping(value = "/Payment/OdemeDTO")
    public ResponseEntity<OdemeDTO> addOdemeDto(@RequestBody OdemeDTO odemeDTO) {
        this.service.addOdeme(odemeDTO);
        return new ResponseEntity(odemeDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Payment")
    public ResponseEntity<Boolean> updateData(@RequestBody Payment Payment) {
        this.service.update(Payment);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(value = "/Payment/OdemeDTO")
    public ResponseEntity<OdemeDTO> updateKonusmaci(@RequestBody OdemeDTO odemeDTO) {
        this.service.updateOdeme(odemeDTO);
        return new ResponseEntity(odemeDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Payment")
    public ResponseEntity<Boolean> deleteData(Payment Payment) {
        this.service.remove(Payment);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(value = "/Payment/OdemeDTO")
    public ResponseEntity<Boolean> deleteOdeme(OdemeDTO odemeDTO) {
        this.service.deleteOdeme(odemeDTO);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
