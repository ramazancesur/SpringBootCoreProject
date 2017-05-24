package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDao extends GenericDaoImpl<Employee, Long> implements IEmployeeDao {

    @Override
    public List<CalisanDTO> getAllCalisan() {
        Criteria criteria = this.getSession().createCriteria(Employee.class, "emp");
        criteria.createAlias("emp.user", "user");
        criteria.add(Restrictions.eq("user.userType", EnumUtil.UserType.CALISAN));
        criteria.add(Restrictions.eq("emp.entityState", EnumUtil.EntityState.ACTIVE));
        return criteria.list();
    }
}
