package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Payment;

import java.util.List;

public interface IPaymentDao extends GenericDao<Payment, Long> {
    Double getToplamOdemeByMusteriOid(Long musteriOid);

    List<Payment> getAllPaymentForAuthType(String userName);

    List<Payment> getAllPaymentByUser();

    Payment getLastPayment(Long borcOid);
}