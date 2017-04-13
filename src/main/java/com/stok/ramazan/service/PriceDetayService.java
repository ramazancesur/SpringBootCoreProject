package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.PriceDetayDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IPriceDetayDao;
import com.stok.ramazan.entity.PriceDetay;
import com.stok.ramazan.service.interfaces.IPriceDetayService;

@Service
public class PriceDetayService extends GenericServiceImpl<PriceDetay, Long> implements IPriceDetayService {
	public PriceDetayService() {
		// TODO Auto-generated constructor stub
	}

	private IPriceDetayDao priceDetayDao;

	@Autowired
	public PriceDetayService(@Qualifier("priceDetayDao") GenericDao<PriceDetay, Long> genericDao) {
		super(genericDao);
		this.priceDetayDao = (PriceDetayDao) genericDao;
	}
}