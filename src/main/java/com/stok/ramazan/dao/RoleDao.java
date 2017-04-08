package com.stok.ramazan.dao;

import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IRoleDao;
import com.stok.ramazan.entity.Role;

@Repository("roleDao")
public class RoleDao extends GenericDaoImpl<Role, Long> implements IRoleDao {

}