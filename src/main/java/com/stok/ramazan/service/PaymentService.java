package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.PaymentDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.entity.Payment;
import com.stok.ramazan.service.interfaces.IPaymentService;

@Service
public class PaymentService extends GenericServiceImpl<Payment, Long> implements IPaymentService {
	public PaymentService() {
		// TODO Auto-generated constructor stub
	}

	private IPaymentDao paymentDao;

	@Autowired
	public PaymentService(@Qualifier("paymentDao") GenericDao<Payment, Long> genericDao) {
		super(genericDao);
		this.paymentDao = (PaymentDao) genericDao;
	}

}
