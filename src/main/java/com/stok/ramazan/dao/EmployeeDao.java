package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.dto.AdresTelefon;
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
            List<AdresTelefon> lstAddresTelefon = new LinkedList<>();
            x.getLstAdres().stream()
                    .filter(y -> y.getAdres() != null)
                    .forEach(y -> {
                        AdresTelefon adresTelefon = new AdresTelefon();
                        adresTelefon.setAddresTipi(y.getAdresTipi());
                        adresTelefon.setOid(y.getOid());
                        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.ADDRES);
                        adresTelefon.setDeger(y.getAdres());
                        lstAddresTelefon.add(adresTelefon);
                    });


            x.getUser().getLstConduct().stream()
                    .filter(y -> y.getTelNo() != null)
                    .forEach(y -> {
                        AdresTelefon adresTelefon = new AdresTelefon();
                        adresTelefon.setTelTipi(y.getContactType());
                        adresTelefon.setOid(y.getOid());
                        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
                        adresTelefon.setDeger(y.getTelNo());
                        lstAddresTelefon.add(adresTelefon);
                    });
            calisanDTO.setLstAddresTel(lstAddresTelefon);
            lstCalisanDTO.add(calisanDTO);
        });

        return lstCalisanDTO;
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

        List<AdresTelefon> lstAddresTel = new LinkedList<>();

        employee.getLstAdres().stream()
                .filter(x -> x.getAdres() != null)
                .forEach(x -> {
                    AdresTelefon adresTelefon = new AdresTelefon();
                    adresTelefon.setOid(x.getOid());
                    adresTelefon.setDeger(x.getAdres());
                    adresTelefon.setAddresTipi(x.getAdresTipi());
                    adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.ADDRES);
                });


        employee.getUser().getLstConduct().stream()
                .filter(x -> x.getTelNo() != null)
                .forEach(y -> {
                            AdresTelefon adresTelefon = new AdresTelefon();
                            adresTelefon.setTelTipi(y.getContactType());
                            adresTelefon.setOid(y.getOid());
                            adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
                            adresTelefon.setDeger(y.getTelNo());
                            lstAddresTel.add(adresTelefon);
                        }
                );
        calisanDTO.setLstAddresTel(lstAddresTel);
        return calisanDTO;
    }
}
