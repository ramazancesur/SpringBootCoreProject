package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.stok.ramazan.dao.SubeDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.ISubeDao;
import com.stok.ramazan.entity.Sube;
import com.stok.ramazan.service.interfaces.ISubeService;

public class SubeService extends GenericServiceImpl<Sube, Long> implements ISubeService {
	public SubeService() {
		// TODO Auto-generated constructor stub
	}

	private ISubeDao subeDao;

	@Autowired
	public SubeService(@Qualifier("subeDao") GenericDao<Sube, Long> genericDao) {
		super(genericDao);
		this.subeDao = (SubeDao) genericDao;
	}
}
