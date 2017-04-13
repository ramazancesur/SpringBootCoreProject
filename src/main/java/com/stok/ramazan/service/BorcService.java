package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.BorcDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.service.interfaces.IBorcService;

@Service
public class BorcService extends GenericServiceImpl<Borc, Long> implements IBorcService {
	private IBorcDao borcDao;

	@Autowired
	public BorcService(@Qualifier("borcDao") GenericDao<Borc, Long> genericDao) {
		super(genericDao);
		this.borcDao = (BorcDao) genericDao;
	}

	public BorcService() {
		// TODO Auto-generated constructor stub
	}
}
