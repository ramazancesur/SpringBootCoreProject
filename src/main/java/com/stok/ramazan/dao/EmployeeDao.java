package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.dto.CalisanDTO;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.helper.EnumUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("employeeDao")
public class EmployeeDao extends GenericDaoImpl<Employee, Long>
        implements IEmployeeDao {

    @Override
    public List<CalisanDTO> getAllCalisan() {
        List<CalisanDTO> lstCalisanDTO = new LinkedList<>();
        Criteria criteria = this.getSession().createCriteria(Employee.class, "emp");
        criteria.createAlias("emp.user", "user");
        criteria.add(Restrictions.eq("user.userType", EnumUtil.UserType.CALISAN));
        criteria.add(Restrictions.eq("emp.entityState", EnumUtil.EntityState.ACTIVE));
        List<Employee> lstEmp = criteria.list();

        lstEmp.forEach(x -> {
            CalisanDTO calisanDTO = new CalisanDTO();
            calisanDTO.setOid(x.getOid());
            calisanDTO.setAd(x.getUser().getAdi());
            calisanDTO.setSoyad(x.getUser().getSoyadi());
            calisanDTO.setKullaniciAdi(x.getUser().getUserName());
            calisanDTO.setSifre(x.getUser().getPassword());
            StringBuilder stringBuilder = new StringBuilder();
            x.getLstAdres().stream()
                    .filter(y -> y.getAdres() != null)
                    .forEach(y -> {
                        stringBuilder.append(" " + y.getAdres());
                    });
            calisanDTO.setAdress(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());

            x.getUser().getLstConduct().stream()
                    .filter(y -> y.getValue() != null)
                    .forEach(y -> {
                        stringBuilder.append(" " + y.getValue());
                    });
            calisanDTO.setTelefonNumarasi(stringBuilder.toString());
            lstCalisanDTO.add(calisanDTO);
        });

        return lstCalisanDTO;
    }

    @Override
    public void addCalisan(CalisanDTO calisanDTO) {

    }

    @Override
    public void updateCalisan(CalisanDTO calisanDTO) {

    }

    @Override
    public boolean deleteCalisan(Long calisanOid) {
        return this.remove(calisanOid);
    }


    @Override
    public CalisanDTO getCalisan(Long calisanOid) {
        Criteria criteria = this.getSession().createCriteria(Employee.class, "emp");
        criteria.createAlias("emp.user", "user");
        criteria.add(Restrictions.eq("user.userType", EnumUtil.UserType.CALISAN));
        criteria.add(Restrictions.eq("emp.oid", calisanOid));
        Employee employee = (Employee) criteria.uniqueResult();

        CalisanDTO calisanDTO = new CalisanDTO();
        calisanDTO.setOid(calisanOid);

        StringBuilder sb = new StringBuilder();
        employee.getLstAdres().stream()
                .filter(x -> x.getAdres() != null)
                .forEach(x -> {
                    sb.append(x.getAdresKullaniciTipi().name() + " " + x.getAdres() + " ");
                });


        calisanDTO.setAdress(sb.toString());
        sb.delete(0, sb.length());

        employee.getUser().getLstConduct().stream()
                .filter(x -> x.getValue() != null)
                .forEach(x -> {
                            sb.append(x.getContactType().name() + " " + x.getValue());
                        }
                );
        calisanDTO.setAdress(sb.toString());
        return calisanDTO;
    }
}
