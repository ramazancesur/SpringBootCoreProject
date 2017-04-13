package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.MusteriDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.service.interfaces.IMusteriService;

@Service
public class MusteriService extends GenericServiceImpl<Musteri, Long> implements IMusteriService {
	private MusteriDao musteriDao;

	public MusteriService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public MusteriService(@Qualifier("musteriDao") GenericDao<Musteri, Long> genericDao) {
		super(genericDao);
		this.musteriDao = (MusteriDao) genericDao;
	}

}
