package com.stok.ramazan.service;

import com.stok.ramazan.dao.OrderDetailDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IOrderDetailDao;
import com.stok.ramazan.entity.OrderDetail;
import com.stok.ramazan.service.interfaces.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService extends GenericServiceImpl<OrderDetail, Long> implements IOrderDetailService {

  private IOrderDetailDao orderDetailDao;

  public OrderDetailService() {
    // TODO Auto-generated constructor stub
  }

  @Autowired
  public OrderDetailService(@Qualifier("orderDetailDao") GenericDao<OrderDetail, Long> genericDao) {
    super(genericDao);
    this.orderDetailDao = (OrderDetailDao) genericDao;
  }

}
