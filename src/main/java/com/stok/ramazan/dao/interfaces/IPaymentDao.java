package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Payment;

public interface IPaymentDao extends GenericDao<Payment, Long> {
  Double getToplamOdemeByMusteriOid(Long musteriOid);
}