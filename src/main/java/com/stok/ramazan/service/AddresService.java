package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IAdressDao;
import com.stok.ramazan.entity.Address;
import com.stok.ramazan.service.interfaces.IAddresService;

@Service
public class AddresService extends GenericServiceImpl<Address, Long> implements IAddresService {
	private IAdressDao adresDao;

	@Autowired
	public AddresService(@Qualifier("adresDao") GenericDao<Address, Long> genericDao) {
		super(genericDao);
		this.adresDao = (IAdressDao) genericDao;
	}

	public AddresService() {
		// TODO Auto-generated constructor stub
	}
}
