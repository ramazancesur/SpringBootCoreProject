package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.PriceDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IPriceDao;
import com.stok.ramazan.entity.Price;
import com.stok.ramazan.service.interfaces.IPriceService;

@Service
public class PriceService extends GenericServiceImpl<Price, Long> implements IPriceService {
	public PriceService() {
		// TODO Auto-generated constructor stub
	}

	private IPriceDao priceDao;

	@Autowired
	public PriceService(@Qualifier("priceDao") GenericDao<Price, Long> genericDao) {
		super(genericDao);
		this.priceDao = (PriceDao) genericDao;
	}

}
