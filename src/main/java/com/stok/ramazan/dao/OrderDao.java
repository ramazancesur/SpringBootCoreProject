package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IOrderDao;
import com.stok.ramazan.entity.Order;

@Repository("orderDao")
public class OrderDao extends GenericDaoImpl<Order, Long> implements IOrderDao {

}
