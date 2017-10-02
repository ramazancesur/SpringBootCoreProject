package com.stok.ramazan.service;

import com.stok.ramazan.dao.SubeDao;
import com.stok.ramazan.dao.interfaces.*;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Sube;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubeService extends GenericServiceImpl<Sube, Long> implements ISubeService {
    private ISubeDao subeDao;

    @Autowired
    private IFirmaDao firmaDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IEmployeeDao employeeDao;

    public SubeService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public SubeService(@Qualifier("subeDao") GenericDao<Sube, Long> genericDao) {
        super(genericDao);
        this.subeDao = (SubeDao) genericDao;
    }

    public Firma getFirmByUser() {
        Firma firma;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userDao.findByUsername(userName);
        if (user.getUserType() == EnumUtil.UserType.FIRMA) {
            firma = firmaDao.getFirma(userName);
        } else {
            Employee emp = employeeDao.getEmployeeByUserName(userName);
            firma = emp.getFirma().getFirma();
        }
        return firma;
    }


    public Sube getUserFirmSube() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userDao.findByUsername(userName);
        Firma firma = null;
        if (user.getUserType() == EnumUtil.UserType.FIRMA) {
            firma = firmaDao.getFirma(userName);
        } else if (user.getUserType() == EnumUtil.UserType.CALISAN) {
            // EmployeeDaodan yazılacak
            Employee emp = employeeDao.getEmployeeByUserName(userName);
            firma = emp.getFirma().getFirma();
            if (firma == null) {
                throw new RuntimeException("Gecersiz Giris");
            }
        }

        List<Sube> lstSube = subeDao.getAllSube(firma.getOid());

        if (lstSube.size() == 0) {
            Sube sube = new Sube();
            sube.setAdress(firma.getAdress());
            sube.setFirma(firma);
            sube.setFirmaAdi(firma.getFirmaAdi());
            sube.setFirmaTipi(EnumUtil.FirmaTipi.KAYITLI_FIRMA);
            sube.setLstConduct(firma.getLstConduct());
            subeDao.add(sube);

            return sube;
        }
        return lstSube.get(0);
    }


}