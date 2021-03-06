package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.AdresTelefon;
import com.stok.ramazan.android.dto.CalisanDTO;
import com.stok.ramazan.android.dto.SirketDTO;
import com.stok.ramazan.dao.EmployeeDao;
import com.stok.ramazan.dao.interfaces.*;
import com.stok.ramazan.entity.*;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.IEmployeeService;
import com.stok.ramazan.service.interfaces.IFirmaService;
import com.stok.ramazan.service.interfaces.ISubeService;
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
    private IFirmaDao firmaDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IAdressDao adressDao;

    @Autowired
    private ISubeDao subeDao;

    @Autowired
    private IFirmaService firmaService;

    @Autowired
    private ISubeService subeService;

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
        SirketDTO sirketDTO = firmaService.getSirket(subeService.getFirmByUser());
        List<CalisanDTO> lstCalisanDTO = employeeDao.getAllCalisan(sirketDTO);
        return lstCalisanDTO;
    }

    @Override
    public void addCalsan(CalisanDTO calisanDTO) {
        Employee employee = new Employee();
        employee.setEmployeeType(calisanDTO.getEmployeeType());
        employee.setIseGirisTarihi(calisanDTO.getIseGirisTarihi());

        List<Conduct> lstConduct = new LinkedList<>();
        List<AdresTelefon> lstAddresTel = calisanDTO.getLstAddresTel();
        List<Address> lstAdres = new LinkedList<>();

        lstAddresTel.stream()
                .filter(x -> x.getDeger() != null || x.getDeger().equals("") || x.getTelOrAddres() != null)
                .forEach(x -> {
                    if (x.getTelOrAddres() == EnumUtil.TelOrAddres.TELEFON) {
                        Conduct conduct = new Conduct();
                        conduct.setContactType(x.getTelTipi());
                        conduct.setTelNo(x.getDeger());
                        conductDao.add(conduct);
                        lstConduct.add(conduct);
                    } else if (x.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
                        Address address = new Address();
                        address.setAdres(x.getDeger());
                        address.setAdresKullaniciTipi(EnumUtil.AdresKullaniciTipi.EMPLOYEE);
                        adressDao.add(address);
                        lstAdres.add(address);
                    }
                });


        User user = new User();
        user.setAdi(calisanDTO.getAd());
        user.setSoyadi(calisanDTO.getSoyad());
        user.setPassword(calisanDTO.getSifre());
        user.setUserType(EnumUtil.UserType.CALISAN);
        user.setPassword(calisanDTO.getSifre());
        user.setUserName(calisanDTO.getKullaniciAdi());
        user.setLstConduct(lstConduct);

        Role role = roleDao.getRoleforName("admin");
        user.setRole(role);
        userDao.add(user);
        employee.setUser(user);
        employee.setFirma(subeService.getUserFirmSube());
        employee.setLstAdres(lstAdres);
        employee.setIseGirisTarihi(calisanDTO.getIseGirisTarihi());

        employeeDao.add(employee);

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
