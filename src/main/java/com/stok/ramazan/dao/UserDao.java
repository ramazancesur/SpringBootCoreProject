package com.stok.ramazan.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil.EntityState;

@Repository("userDao")
public class UserDao extends GenericDaoImpl<User, Long> implements IUserDao {

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		Criteria criteria = this.createEntityCriteria();
		criteria.add(Restrictions.eq("userName", username));
		criteria.add(Restrictions.eq("entityState", EntityState.ACTIVE));
		return (User) criteria.uniqueResult();
	}

}