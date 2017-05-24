package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.entity.Payment;
import org.springframework.stereotype.Repository;

@Repository("paymentDao")
public class PaymentDao extends GenericDaoImpl<Payment, Long> implements IPaymentDao {

}
