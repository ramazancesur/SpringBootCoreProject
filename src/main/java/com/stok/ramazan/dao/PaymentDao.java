package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.entity.Payment;

@Repository("paymentDao")
public class PaymentDao extends GenericDaoImpl<Payment, Long> implements IPaymentDao {

}
