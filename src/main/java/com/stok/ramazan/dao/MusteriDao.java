package com.stok.ramazan.dao;

import com.stok.ramazan.android.dto.AdresTelefon;
import com.stok.ramazan.android.dto.MusteriDTO;
import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.dao.interfaces.IUserDao;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Repository("musteriDao")
@Transactional
public class MusteriDao extends GenericDaoImpl<Musteri, Long> implements IMusteriDao {
  @Autowired
  private IBorcDao borcDao;
  @Autowired
  private IPaymentDao paymentDao;

  @Autowired
  private IFirmaDao firmaDao;

  @Autowired
  private IEmployeeDao employeeDao;

  @Autowired
  private IUserDao userDao;

  @Autowired
  private ISubeService subeService;

  private Double getToplamBorcByMusteriOid(Long musteriOid) {
    return borcDao.getToplamBorcByMusteriOid(musteriOid) - paymentDao.getToplamOdemeByMusteriOid(musteriOid);
  }

  public List<Musteri> getAllMusteriByUser(Long firmOid){
    Criteria criteria=currentSession().createCriteria(Musteri.class,"musteri");
    criteria.createAlias("musteri.firma","firma");
    criteria.add(Restrictions.eq("firma.oid",firmOid));
    criteria.add(Restrictions.eq("musteri.entityState", EnumUtil.EntityState.ACTIVE));
    return criteria.list();
  }

  @Override
  public List<MusteriDTO> getAllMusteri() {
    List<MusteriDTO> lstMusteriDto = new LinkedList<>();
    List<Musteri> lstMusteri = getAllMusteriByUser(subeService.getFirmByUser().getOid());
    lstMusteri.stream()
        .forEach(x -> {
          lstMusteriDto.add(getMusteriDTO(x));
        });
    return lstMusteriDto;
  }

  private MusteriDTO getMusteriDTO(Musteri musteri) {
    MusteriDTO musteriDTO = new MusteriDTO();
    List<AdresTelefon> lstAdresTel = new LinkedList<>();

    musteri.getLstConduct().stream()
        .filter(y -> y != null)
        .forEach(y -> {
          AdresTelefon adresTelefon = new AdresTelefon();
          adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
          adresTelefon.setDeger(y.getTelNo());
          adresTelefon.setOid(y.getOid());
          lstAdresTel.add(adresTelefon);
        });
    musteri.getLstAddress().stream()
        .filter(y -> y.getAdresKullaniciTipi() != null)
        .forEach(y -> {
          AdresTelefon adresTelefon = new AdresTelefon();
          adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.ADDRES);
          adresTelefon.setAddresTipi(EnumUtil.AddresTipi.GENEL);
          adresTelefon.setDeger(y.getAdres());
          adresTelefon.setOid(y.getOid());
          lstAdresTel.add(adresTelefon);
        });
    musteriDTO.setLstAdresTel(lstAdresTel);
    musteriDTO.setAd(musteri.getAdi());
    musteriDTO.setSoyad(musteri.getSoyadi());
    musteriDTO.setOid(musteri.getOid());
    musteriDTO.setCreatedDate(musteri.getCreatedDate());
    musteriDTO.setUpdatedDate(musteri.getUpdatedDate());
    musteriDTO.setToplamBorc(getToplamBorcByMusteriOid(musteriDTO.getOid()));
    musteriDTO.setFirmMusteriOid(musteri.getFirma().getOid());

    return musteriDTO;
  }

  @Override
  public MusteriDTO getMusteriDTO(Long musteriOid) {
    return getMusteriDTO(this.find(musteriOid));
  }
}