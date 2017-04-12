package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IOrderDetailDao;
import com.stok.ramazan.entity.OrderDetail;

@Repository("orderDetailDao")
public class OrderDetailDao extends GenericDaoImpl<OrderDetail, Long> implements IOrderDetailDao {

}
