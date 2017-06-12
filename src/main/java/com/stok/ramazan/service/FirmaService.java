package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.AdresTelefon;
import com.stok.ramazan.android.dto.SirketDTO;
import com.stok.ramazan.dao.FirmaDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IAdressDao;
import com.stok.ramazan.dao.interfaces.IConductDao;
import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.Address;
import com.stok.ramazan.entity.Conduct;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.IFirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FirmaService extends GenericServiceImpl<Firma, Long> implements IFirmaService {
    private FirmaDao firmaDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IAdressDao adressDao;

    @Autowired
    private IConductDao conductDao;

    public FirmaService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public FirmaService(@Qualifier("firmaDao") GenericDao<Firma, Long> genericDao) {
        super(genericDao);
        this.firmaDao = (FirmaDao) genericDao;
    }

    @Override
    public List<SirketDTO> getAllSirket() {
        List<Firma> lstFirma = firmaDao.getAllEssentialFirm();
        List<SirketDTO> lstSirket = new LinkedList<>();

        for (Firma firma : lstFirma) {
            SirketDTO sirketDTO = getSirket(firma);
            lstSirket.add(sirketDTO);
        }
        return lstSirket;
    }

    private SirketDTO getSirket(Firma firma) {
        SirketDTO sirketDTO = new SirketDTO();
        sirketDTO.setLogoPath(firma.getFirmaLogoYolu());
        sirketDTO.setSirketAdi(firma.getFirmaAdi());
        sirketDTO.setCreatedDate(firma.getCreatedDate());
        sirketDTO.setOid(firma.getOid());
        sirketDTO.setUpdatedDate(firma.getUpdatedDate());
        List<String> lstLisansKey = new LinkedList<>();
        firmaDao.getAllActiveLisans(firma.getOid())
                .stream().filter(lisans -> lisans.getFirma() != null)
                .forEach(lisans -> {
                    if (lisans.getLicenseKey() != null) {
                        lstLisansKey.add(lisans.getLicenseKey());
                    }
                });
        List<AdresTelefon> lstAdresTel = new LinkedList<>();
        if (firma.getAdress() != null) {
            AdresTelefon adresTelefon = new AdresTelefon();
            adresTelefon.setAddresTipi(EnumUtil.AddresTipi.GENEL);
            adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.ADDRES);
            adresTelefon.setOid(firma.getAdress().getOid());
            adresTelefon.setDeger(firma.getAdress().getAdres());

            lstAdresTel.add(adresTelefon);
        }

        firma.getLstConduct()
                .stream()
                .filter(conduct -> conduct.getTelNo() != null)
                .forEach(conduct -> {
                    AdresTelefon adresTelefon = new AdresTelefon();
                    adresTelefon.setTelTipi(EnumUtil.ContactTipi.GENEL);
                    adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
                    adresTelefon.setOid(conduct.getOid());
                    adresTelefon.setDeger(conduct.getTelNo());
                    lstAdresTel.add(adresTelefon);
                });


        sirketDTO.setLstAdresTel(lstAdresTel);
        sirketDTO.setSirketLisansKey(lstLisansKey);
        sirketDTO.setUserOid(firma.getUser().getOid());
        return sirketDTO;
    }

    @Override
    public SirketDTO getSirket(Long sirketOid) {
        Firma firma = firmaDao.find(sirketOid);
        return getSirket(firma);
    }

    @Override
    public boolean addSirket(SirketDTO sirketDTO) {
        try {
            Firma firma = new Firma();
            List<Conduct> lstConduct = new LinkedList<>();
            if (sirketDTO.getLstAdresTel().size() != 0) {
                for (AdresTelefon adresTelefon : sirketDTO.getLstAdresTel()) {
                    if (adresTelefon.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
                        Conduct conduct = new Conduct();
                        conduct.setTelNo(adresTelefon.getDeger());
                        conduct.setContactType(adresTelefon.getTelTipi());
                        conductDao.add(conduct);

                        lstConduct.add(conduct);
                    } else {
                        Address address = new Address();
                        address.setAdresTipi(adresTelefon.getAddresTipi());
                        address.setAdres(adresTelefon.getDeger());
                        address.setAdresKullaniciTipi(EnumUtil.AdresKullaniciTipi.EMPLOYEE);
                        adressDao.add(address);

                        firma.setAdress(address);


                    }
                }
            }

            User user = userDao.find(firma.getUser().getOid());

            firma.setLstConduct(lstConduct);
            firma.setUser(user);
            firma.setFirmaAdi(sirketDTO.getSirketAdi());
            firma.setFirmaLogoYolu(sirketDTO.getLogoPath());

        } catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSirket(SirketDTO sirketDTO) {
        return false;
    }

    @Override
    public boolean deleteSirket(Long sirketOid) {
        return firmaDao.remove(sirketOid);
    }
}
