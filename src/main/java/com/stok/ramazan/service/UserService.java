package com.stok.ramazan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stok.ramazan.dao.UserDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.service.interfaces.IUserService;

@Service
public class UserService  extends GenericServiceImpl<User, Long> implements IUserService {

	@SuppressWarnings("unused")
	private IUserDao userDao;
  
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
    @Autowired
    public UserService(
            @Qualifier("userDao") GenericDao<User, Long> genericDao) {
        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }
}