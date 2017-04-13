package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.TaksitDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.ITaksitDao;
import com.stok.ramazan.entity.Taksit;
import com.stok.ramazan.service.interfaces.ITaksitService;

@Service
public class TaksitService extends GenericServiceImpl<Taksit, Long> implements ITaksitService {
	public TaksitService() {
		// TODO Auto-generated constructor stub
	}

	private ITaksitDao taksitDao;

	@Autowired
	public TaksitService(@Qualifier("taksitDao") GenericDao<Taksit, Long> genericDao) {
		super(genericDao);
		this.taksitDao = (TaksitDao) genericDao;
	}
}
