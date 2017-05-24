package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IOrderDao;
import com.stok.ramazan.entity.Order;
import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderDao extends GenericDaoImpl<Order, Long> implements IOrderDao {

}
