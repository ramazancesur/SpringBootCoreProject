package com.stok.ramazan.service;

import com.stok.ramazan.dao.EmployeeDao;
import com.stok.ramazan.dao.interfaces.*;
import com.stok.ramazan.dto.AdresTelefon;
import com.stok.ramazan.dto.CalisanDTO;
import com.stok.ramazan.entity.*;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService extends
        GenericServiceImpl<Employee, Long> implements IEmployeeService {

    private IEmployeeDao employeeDao;

    @Autowired
    private IConductDao conductDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IAdressDao adressDao;

    @Autowired
    public EmployeeService(@Qualifier("employeeDao") GenericDao<Employee, Long> genericDao) {
        super(genericDao);
        this.employeeDao = (EmployeeDao) genericDao;
    }


    public EmployeeService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<CalisanDTO> getAllCalisan() {
        List<CalisanDTO> lstCalisanDTO = employeeDao.getAllCalisan();
        return lstCalisanDTO;
    }

    @Override
    public void addCalsan(CalisanDTO calisanDTO) {
        Employee employee = new Employee();
        employee.setEmployeeType(calisanDTO.getEmployeeType());
        employee.setIseGirisTarihi(calisanDTO.getIseGirisTarihi());

        List<AdresTelefon> lstAddresTel = calisanDTO.getLstAddresTel();

        lstAddresTel.stream()
                .filter(x -> x.getDeger() != null || x.getDeger().equals("") || x.getTelOrAddres() != null)
                .forEach(x -> {
                    if (x.getTelOrAddres() == EnumUtil.TelOrAddres.TELEFON) {
                        Conduct conduct = new Conduct();
                        conduct.setContactType(x.getTelTipi());
                        conduct.setTelNo(x.getDeger());
                        conductDao.add(conduct);
                    } else if (x.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
                        Address address = new Address();
                        address.setAdres(x.getDeger());
                        address.setAdresKullaniciTipi(EnumUtil.AdresKullaniciTipi.EMPLOYEE);
                        adressDao.add(address);
                    }
                });


        User user = new User();
        user.setAdi(calisanDTO.getAd());
        user.setSoyadi(calisanDTO.getSoyad());
        user.setPassword(calisanDTO.getSifre());
        user.setUserType(EnumUtil.UserType.CALISAN);
        user.setPassword(calisanDTO.getSifre());
        user.setUserName(calisanDTO.getKullaniciAdi());

        Role role = roleDao.getRoleforName("admin");
        user.setRole(role);
        userDao.add(user);
        employee.setUser(user);

    }

    @Override
    public void updateCalisan(CalisanDTO calisanDTO) {
        List<AdresTelefon> lstAdresTelefon = calisanDTO.getLstAddresTel();
        List<Conduct> lstConduct = new LinkedList<>();
        lstAdresTelefon.stream()
                .filter(x -> x.getTelOrAddres() != null)
                .forEach(x -> {
                    if (x.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
                        Address address = adressDao.find(x.getOid());
                        address.setAdres(x.getDeger());
                        address.setAdresTipi(x.getAddresTipi());
                        adressDao.update(address);
                    } else if (x.getTelOrAddres() == EnumUtil.TelOrAddres.TELEFON) {
                        Conduct conduct = conductDao.find(x.getOid());
                        conduct.setContactType(x.getTelTipi());
                        conduct.setTelNo(x.getDeger());
                        lstConduct.add(conduct);
                    }
                });
        User user = userDao.findByUsername(calisanDTO.getKullaniciAdi());
        user.setLstConduct(lstConduct);
        user.setPassword(calisanDTO.getSifre());
        user.setAdi(calisanDTO.getAd());
        user.setSoyadi(calisanDTO.getSoyad());
        userDao.update(user);
    }

    @Override
    public boolean deleteCalisan(Long calisanOid) {
        return employeeDao.remove(calisanOid);
    }

    @Override
    public CalisanDTO getCalisan(Long oid) {
        CalisanDTO calisanDTO = employeeDao.getCalisan(oid);
        return calisanDTO;
    }
}
