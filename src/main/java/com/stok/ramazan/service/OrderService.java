package com.stok.ramazan.service;

import com.stok.ramazan.dao.OrderDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.Order;
import com.stok.ramazan.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends GenericServiceImpl<Order, Long> implements IOrderService {
  private OrderDao orderDao;

  public OrderService() {
    // TODO Auto-generated constructor stub
  }

  @Autowired
  public OrderService(@Qualifier("orderDao") GenericDao<Order, Long> genericDao) {
    super(genericDao);
    this.orderDao = (OrderDao) genericDao;
  }

}