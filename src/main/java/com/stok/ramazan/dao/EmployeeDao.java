package com.stok.ramazan.dao;

import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.pojo.Calisan;
import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.entity.Employee;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDao extends GenericDaoImpl<Employee, Long> implements IEmployeeDao {

    @Override
    public List<Calisan> getAllCalisan() {
        Criteria criteria=this.getSession().createCriteria(Employee.class,"emp");
        criteria.createAlias("emp.user","user");
        criteria.add(Restrictions.eq("user.userType", EnumUtil.UserType.CALISAN));
        criteria.add(Restrictions.eq("emp.entityState", EnumUtil.EntityState.ACTIVE));
        return criteria.list();
    }
}
