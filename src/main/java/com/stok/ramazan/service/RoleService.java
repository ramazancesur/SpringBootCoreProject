package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.RoleDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IRoleDao;
import com.stok.ramazan.entity.Role;
import com.stok.ramazan.service.interfaces.IRoleService;

@Service
public class RoleService extends GenericServiceImpl<Role, Long> implements IRoleService {

	@SuppressWarnings("unused")
	private IRoleDao roleDao;

	public RoleService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public RoleService(@Qualifier("roleDao") GenericDao<Role, Long> genericDao) {
		super(genericDao);
		this.roleDao = (RoleDao) genericDao;
	}

}
