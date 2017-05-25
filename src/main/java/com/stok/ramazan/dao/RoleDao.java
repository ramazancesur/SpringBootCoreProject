package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IRoleDao;
import com.stok.ramazan.entity.Role;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDao extends GenericDaoImpl<Role, Long> implements IRoleDao {
    @Override
    public Role getRoleforName(String roleName) {
        Criteria criteria = this.getSession().createCriteria(Role.class, "role");
        criteria.add(Restrictions.eq("role.entityState", EnumUtil.EntityState.ACTIVE));
        criteria.add(Restrictions.ilike("role.yetkiAdi", roleName, MatchMode.ANYWHERE));
        return (Role) criteria.uniqueResult();
    }
}