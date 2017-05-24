package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IRoleDao;
import com.stok.ramazan.entity.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDao extends GenericDaoImpl<Role, Long> implements IRoleDao {

}