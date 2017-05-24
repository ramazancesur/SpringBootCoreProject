package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IOrderDetailDao;
import com.stok.ramazan.entity.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository("orderDetailDao")
public class OrderDetailDao extends GenericDaoImpl<OrderDetail, Long> implements IOrderDetailDao {

}
