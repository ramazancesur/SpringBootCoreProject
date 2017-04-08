package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.User;

public interface IUserDao extends GenericDao<User, Long>{
	User findByUsername(String username);
}