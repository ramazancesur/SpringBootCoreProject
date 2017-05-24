package com.stok.ramazan.controller;

import com.stok.ramazan.entity.Payment;
import com.stok.ramazan.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController extends BaseController {
    @Autowired
    private IPaymentService service;

    @GetMapping("/PaymentList")
    public ResponseEntity<List<Payment>> getKonusmaciList() {
        List<Payment> lstPayment = this.service.getAll();
        return new ResponseEntity<List<Payment>>(lstPayment, HttpStatus.OK);
    }

    @GetMapping(value = "/PaymentList/{id}")
    public ResponseEntity<Payment> getKonusmaciById(@PathVariable("id") Long id) {
        Payment Payment = this.service.get(id);
        return new ResponseEntity<Payment>(Payment, HttpStatus.OK);
    }

    @PostMapping(value = "/Payment")
    public ResponseEntity<Payment> addKonusmaci(@RequestBody Payment Payment) {
        this.service.add(Payment);
        return new ResponseEntity<Payment>(Payment, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Payment")
    public ResponseEntity<Payment> updateKonusmaci(@PathVariable Long id, @RequestBody Payment Payment) {
        this.service.update(Payment);
        return new ResponseEntity<Payment>(Payment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/Payment")
    public ResponseEntity<Boolean> deleteEtkinlik(Payment Payment) {
        this.service.remove(Payment);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
