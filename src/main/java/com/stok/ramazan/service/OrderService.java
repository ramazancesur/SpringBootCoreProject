package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.OrderDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.Order;
import com.stok.ramazan.service.interfaces.IOrderService;

@Service
public class OrderService extends GenericServiceImpl<Order, Long> implements IOrderService {
	public OrderService() {
		// TODO Auto-generated constructor stub
	}

	private OrderDao orderDao;

	@Autowired
	public OrderService(@Qualifier("orderDao") GenericDao<Order, Long> genericDao) {
		super(genericDao);
		this.orderDao = (OrderDao) genericDao;
	}

}