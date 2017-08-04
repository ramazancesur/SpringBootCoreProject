package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.SiparisDTO;
import com.stok.ramazan.android.dto.SiparisListesiDTO;
import com.stok.ramazan.android.dto.UrunDTO;
import com.stok.ramazan.dao.BorcDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IBorcDetayDao;
import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.dao.interfaces.IProductDao;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.entity.BorcDetay;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.entity.Payment;
import com.stok.ramazan.entity.Product;
import com.stok.ramazan.service.interfaces.IBorcService;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class BorcService extends GenericServiceImpl<Borc, Long>
    implements IBorcService {
  private static final Logger LOGGER = LoggerFactory.getLogger(BorcService.class);
  private IBorcDao borcDao;
  @Autowired
  private IBorcDetayDao borcDetayDao;

  @Autowired
  private IPaymentDao paymentDao;

  @Autowired
  private IMusteriDao musteriDao;

  @Autowired
  private IProductDao productDao;

  @Autowired
  private ISubeService subeService;

  @Autowired
  public BorcService(@Qualifier("borcDao") GenericDao<Borc, Long> genericDao) {
    super(genericDao);
    this.borcDao = (BorcDao) genericDao;
  }

  public BorcService() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public List<SiparisListesiDTO> getAllSiparis() {
    List<Borc> lstBorc = borcDao.getAllBorcByAuthenticated();
    List<SiparisListesiDTO> lstSiparisListesi = new LinkedList<>();
    for (Borc borc : lstBorc) {
      lstSiparisListesi.add(getSiparisListesiDTObyBorc(borc));
    }
    return lstSiparisListesi;
  }

  private SiparisListesiDTO getSiparisListesiDTObyBorc(Borc borc) {
    SiparisListesiDTO siparisListesiDTO = new SiparisListesiDTO();
    SiparisDTO siparisDTO = new SiparisDTO();
    List<SiparisDTO> lstSiparis = new LinkedList<>();
    siparisListesiDTO.setOid(borc.getOid());

    if (borc.getLstBorceDetay() != null) {
      borc.getLstBorceDetay().stream()
          .filter(x -> x.getProduct() != null && x.getProduct().getPrice() != null)
          .forEach(borcDetay -> {
            siparisDTO.setAdet(borcDetay.getAdet());
            siparisDTO.setMetre(borcDetay.getMetre());
            siparisDTO.setOid(borcDetay.getOid());
            siparisDTO.setBorcOid(borc.getOid());
            siparisDTO.setUcreti(borcDetay.getProduct().getPrice().getFiyati().doubleValue());
            siparisDTO.setCreatedDate(borcDetay.getCreatedDate());
            siparisDTO.setUpdatedDate(borcDetay.getUpdatedDate());
            siparisDTO.setOid(borcDetay.getOid());
            UrunDTO urunDTO = new UrunDTO();
            Product product = borcDetay.getProduct();
            urunDTO.setGelisTarihi(new Date());
            urunDTO.setSonKullanmaTarihi(product.getSonKullanmaTarihi());
            urunDTO.setPrice(product.getPrice().getFiyati().doubleValue());
            urunDTO.setProductName(product.getProductName());
            urunDTO.setCreatedDate(product.getCreatedDate());
            urunDTO.setUpdatedDate(product.getUpdatedDate());
            urunDTO.setOid(product.getOid());
            siparisDTO.setUrun(urunDTO);

            lstSiparis.add(siparisDTO);
          });
      siparisListesiDTO.setSiparisDurum(borc.getSiparisDurum());
      siparisListesiDTO.setMusteriNotu(borc.getMusteriNotu());
      siparisListesiDTO.setSaticiNotu(borc.getSaticiNotu());

      siparisListesiDTO.setLstSiparisDTOS(lstSiparis);
      siparisListesiDTO.setBeklenenTeslimatTarihi(borc.getBeklenenOdemeTarihi());
      siparisListesiDTO.setMusteri(musteriDao.getMusteriDTO(borc.getMusteri().getOid()));
      siparisListesiDTO.setSiparisBorcuToplami(borc.getToplamBorc().doubleValue());

      siparisListesiDTO.setKalanBorc(borc.getKalanBorc().doubleValue());
    }

    Payment currentPayment = paymentDao.getLastPayment(borc.getOid());
    siparisListesiDTO.setSonOdenenTutar(currentPayment.getOdemeTutari().doubleValue());

    // musteri toplam borcunu update edeceğiz  musteriDao da var

    currentMusteriUpdate(borc);

    return siparisListesiDTO;
  }

  private void currentMusteriUpdate(Borc borc) {
    Musteri musteri = borc.getMusteri();
    BigDecimal musteriToplamBorcu = musteriDao.getMusteriToplamBorcu(musteri.getOid());
    musteri.setMusteriToplamBorcu(musteriToplamBorcu);

    musteriDao.update(musteri);

  }

  @Override
  public SiparisListesiDTO getSiparis(Long siparisListesiOid) {
    Borc borc = borcDao.find(siparisListesiOid);
    return getSiparisListesiDTObyBorc(borc);
  }

  @Override
  public boolean addSiparis(SiparisListesiDTO siparisListesiDTO) {
    try {
      Borc borc = new Borc();
      List<BorcDetay> lstBorcDetay = new LinkedList<>();
      for (SiparisDTO siparisDTO : siparisListesiDTO.getLstSiparisDTOS()) {
        BorcDetay borcDetay = new BorcDetay();
        borcDetay.setAdet(siparisDTO.getAdet());
        borcDetay.setMetre(siparisDTO.getMetre());
        Product product = productDao.find(siparisDTO.getUrun().getOid());
        borcDetay.setProduct(product);
        borcDetayDao.add(borcDetay);
        lstBorcDetay.add(borcDetay);
      }
      borc.setOdemeSube(subeService.getUserFirmSube());
      borc.setSiparisDurum(siparisListesiDTO.getSiparisDurum());
      borc.setLstBorceDetay(lstBorcDetay);
      borc.setToplamBorc(BigDecimal.valueOf(siparisListesiDTO.getSiparisBorcuToplami()));
      borc.setMusteri(musteriDao.find(siparisListesiDTO.getMusteri().getOid()));
      borc.setKalanBorc(BigDecimal.valueOf(siparisListesiDTO.getSiparisBorcuToplami()));
      borc.setMusteriNotu(siparisListesiDTO.getMusteriNotu());
      borc.setSaticiNotu(siparisListesiDTO.getSaticiNotu());
      borc.setBeklenenOdemeTarihi(siparisListesiDTO.getBeklenenTeslimatTarihi());
      borcDao.add(borc);

      Payment payment = new Payment();
      payment.setSaticiSube(subeService.getUserFirmSube());
      payment.setBorc(borc);
      payment.setBeklenenOdemeTarihi(siparisListesiDTO.getBeklenenTeslimatTarihi());

      Calendar calendar = Calendar.getInstance();
      payment.setGerceklesenOdemeTarihi(calendar.getTime());
      payment.setOdemeTutari(BigDecimal.valueOf(siparisListesiDTO.getSonOdenenTutar()));

      paymentDao.add(payment);

      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateSiparis(SiparisListesiDTO siparisListesiDTO) {
    try {
      Borc borc = borcDao.find(siparisListesiDTO.getOid());
      List<BorcDetay> lstBorcDetay = new LinkedList<>();
      for (SiparisDTO siparisDTO : siparisListesiDTO.getLstSiparisDTOS()) {
        BorcDetay borcDetay = borcDetayDao.find(siparisDTO.getOid());
        borcDetay.setAdet(siparisDTO.getAdet());
        borcDetay.setMetre(siparisDTO.getMetre());
        Product product = productDao.find(siparisDTO.getUrun().getOid());
        borcDetay.setProduct(product);
        borcDetayDao.update(borcDetay);
        lstBorcDetay.add(borcDetay);
      }
      borc.setLstBorceDetay(lstBorcDetay);
      borc.setToplamBorc(BigDecimal.valueOf(siparisListesiDTO.getSiparisBorcuToplami()));
      borc.setMusteri(musteriDao.find(siparisListesiDTO.getMusteri().getOid()));

      Double kalanBorc = borc.getKalanBorc().doubleValue() - siparisListesiDTO.getSonOdenenTutar();
      borc.setKalanBorc(BigDecimal.valueOf(kalanBorc));

      borc.setMusteriNotu(siparisListesiDTO.getSaticiNotu());
      borc.setSaticiNotu(siparisListesiDTO.getSaticiNotu());
      borc.setBeklenenOdemeTarihi(siparisListesiDTO.getBeklenenTeslimatTarihi());
      borcDao.update(borc);
      // Son Ödeme Güncelleme  true ise poyment guncellenecek false ise yeni payment yapilacak
      if (siparisListesiDTO.isSonGuncelemeOdeme() == true) {
        Payment payment = paymentDao.getLastPayment(borc.getOid());
        payment.setOdemeTutari(BigDecimal.valueOf(siparisListesiDTO.getSonOdenenTutar()));
        paymentDao.update(payment);
      } else {
        Payment payment = new Payment();
        payment.setSaticiSube(subeService.getUserFirmSube());
        payment.setBorc(borc);
        payment.setBeklenenOdemeTarihi(siparisListesiDTO.getBeklenenTeslimatTarihi());

        Calendar calendar = Calendar.getInstance();
        payment.setGerceklesenOdemeTarihi(calendar.getTime());
        payment.setOdemeTutari(BigDecimal.valueOf(siparisListesiDTO.getSonOdenenTutar()));

      }

      currentMusteriUpdate(borc);
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteSiparis(Long siparisListesiOid) {
    borcDetayDao.removeBorc(siparisListesiOid);
    return borcDao.remove(siparisListesiOid);
  }

}