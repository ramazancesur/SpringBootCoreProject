package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.AdresTelefon;
import com.stok.ramazan.android.dto.SirketDTO;
import com.stok.ramazan.dao.FirmaDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IAdressDao;
import com.stok.ramazan.dao.interfaces.IConductDao;
import com.stok.ramazan.dao.interfaces.ILisansDao;
import com.stok.ramazan.dao.interfaces.IRoleDao;
import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.Address;
import com.stok.ramazan.entity.Conduct;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Lisans;
import com.stok.ramazan.entity.Role;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.helper.FileOperations;
import com.stok.ramazan.helper.Helper;
import com.stok.ramazan.service.interfaces.IFirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
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

  @Autowired
  private ILisansDao lisansDao;

  @Autowired
  private IRoleDao roleDao;

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
      if (sirketDTO != null) {
        lstSirket.add(sirketDTO);
      }
    }
    return lstSirket;
  }

  private SirketDTO getSirket(Firma firma) {
    try {
      SirketDTO sirketDTO = new SirketDTO();
      sirketDTO.setLogoPath(firma.getFirmaLogoYolu());
      sirketDTO.setSirketAdi(firma.getFirmaAdi());
      sirketDTO.setCreatedDate(firma.getCreatedDate());
      sirketDTO.setOid(firma.getOid());
      sirketDTO.setUpdatedDate(firma.getUpdatedDate());
      sirketDTO.setAndroidLogoPath(firma.getAndroidLogoPath());
      sirketDTO.setChangeImage(false);

      Lisans lisans = firmaDao.getAllActiveLisans(firma.getOid());
      sirketDTO.setSirketLisansKey(lisans.getLicenseKey());
      sirketDTO.setLisansEndTimes(lisans.getLicenseFinishDate());
      sirketDTO.setLisansStartTimes(lisans.getLicenseStartDate());

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
      sirketDTO.setUserOid(firma.getUser().getOid());
      sirketDTO.setKalanSms(firma.getKalanSms());
      return sirketDTO;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
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
      firma.setFirmaAdi(sirketDTO.getSirketAdi());

      // Android tarafında imagenin datası yüklenecek
      if (sirketDTO.isChangeImage()) {
        String base64Image = sirketDTO.getEncodedImages();
        String fileLocation = FileOperations.convertBytesToFile(Helper.getInstance().decodeBase64(base64Image), "/firmaLogo", firma.getFirmaAdi());
        firma.setFirmaLogoYolu(fileLocation);
      }
      firma.setAndroidLogoPath(sirketDTO.getAndroidLogoPath());
      // Kalan sms tutarını update edebilir
      firma.setKalanSms(sirketDTO.getKalanSms());

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
      Role role;
      List<Role> lstRole = roleDao.getAll();
      if (lstRole.size() != 0) {
        role = lstRole.get(0);
      } else {
        role = new Role();
        role.setYetkiAciklamasi("FIRMA rolü");
        role.setYetkiAdi("FIRMA");
        roleDao.add(role);
      }
      User user = new User();
      user.setRole(role);
      user.setUserType(EnumUtil.UserType.FIRMA);
      user.setLstConduct(lstConduct);

      userDao.add(user);

      firma.setLstConduct(lstConduct);
      firma.setUser(user);

      firmaDao.add(firma);

      Lisans lisans = new Lisans();
      lisans.setFirma(firma);
      lisans.setLicenseStartDate(new Date());
      lisans.setLicenseFinishDate(sirketDTO.getLisansEndTimes());
      String lisansKey = Helper.generateUnique();
      lisans.setLicenseKey(lisansKey);
      lisansDao.add(lisans);

    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean updateSirket(SirketDTO sirketDTO) {
    try {
      Firma firma = firmaDao.find(sirketDTO.getOid());
      // Şirket resmini update edebilir

      String base64Image = sirketDTO.getEncodedImages();

      String fileLocation = FileOperations.convertBytesToFile(Helper.getInstance().decodeBase64(base64Image), "/firmaLogo", firma.getFirmaAdi());

      firma.setFirmaLogoYolu(fileLocation);
      // Kalan sms tutarını update edebilir
      firma.setKalanSms(sirketDTO.getKalanSms());

      List<Conduct> lstConduct = new LinkedList<>();
      if (sirketDTO.getLstAdresTel().size() != 0) {
        for (AdresTelefon adresTelefon : sirketDTO.getLstAdresTel()) {
          if (adresTelefon.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
            Conduct conduct = conductDao.find(adresTelefon.getOid());
            conduct.setTelNo(adresTelefon.getDeger());
            conduct.setContactType(adresTelefon.getTelTipi());
            conductDao.update(conduct);

            lstConduct.add(conduct);
          } else {
            Address address = adressDao.find(adresTelefon.getOid());
            address.setAdresTipi(adresTelefon.getAddresTipi());
            address.setAdres(adresTelefon.getDeger());
            adressDao.update(address);

            firma.setAdress(address);
          }
        }
      }

      User user = userDao.find(firma.getUser().getOid());

      firma.setLstConduct(lstConduct);
      firma.setUser(user);
      firma.setFirmaAdi(sirketDTO.getSirketAdi());
      firma.setFirmaLogoYolu(sirketDTO.getLogoPath());

      firmaDao.update(firma);
      Lisans tempLisans = firmaDao.getAllActiveLisans(firma.getOid());

      if (tempLisans != null) {
        tempLisans.setLicenseFinishDate(sirketDTO.getLisansEndTimes());
        lisansDao.update(tempLisans);
      }

      if (tempLisans == null) {
        Lisans lisans = new Lisans();
        lisans.setFirma(firma);
        lisans.setLicenseStartDate(new Date());
        lisans.setLicenseFinishDate(sirketDTO.getLisansEndTimes());
        String lisansKey = Helper.generateUnique();
        lisans.setLicenseKey(lisansKey);
        lisansDao.add(lisans);
      }


    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean deleteSirket(Long sirketOid) {
    return firmaDao.remove(sirketOid);
  }
}