package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil.EntityState;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

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

    @Override
    public User add(User entity) {
        String password = entity.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        entity.setPassword(hashedPassword);
        return super.add(entity);
    }

    @Override
    public User update(User entity) {
        String password = entity.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = this.find(entity.getOid());
        // passwordun değişip değişmediğine bakıyor
        if (!passwordEncoder.matches(user.getPassword(), password)) {
            String hashadPassword = passwordEncoder.encode(password);
            entity.setPassword(hashadPassword);
        }
        return super.update(entity);
    }
}