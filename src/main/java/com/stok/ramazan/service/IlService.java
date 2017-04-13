package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.IlDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IIlDao;
import com.stok.ramazan.entity.Il;
import com.stok.ramazan.service.interfaces.IIlService;

@Service
public class IlService extends GenericServiceImpl<Il, Long> implements IIlService {
	private IIlDao ilDao;

	@Autowired
	public IlService(@Qualifier("ilDao") GenericDao<Il, Long> genericDao) {
		super(genericDao);
		this.ilDao = (IlDao) genericDao;
	}

	public IlService() {
		// TODO Auto-generated constructor stub
	}

}
