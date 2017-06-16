package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.FaturaDTO;
import com.stok.ramazan.android.dto.MusteriDTO;
import com.stok.ramazan.android.dto.SiparisListesiDTO;
import com.stok.ramazan.dao.FaturaDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IEmployeeDao;
import com.stok.ramazan.dao.interfaces.IFaturaDao;
import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.entity.Employee;
import com.stok.ramazan.entity.Fatura;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.service.interfaces.IBorcService;
import com.stok.ramazan.service.interfaces.IFaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LocalAdmin on 02.06.2017.
 */
@Service
public class FaturaService extends GenericServiceImpl<Fatura, Long>
    implements IFaturaService {
  private static final Logger LOGGER = LoggerFactory.getLogger(FaturaService.class);
  @Autowired
  IBorcService borcService;
  @Autowired
  private IBorcDao borcDao;
  @Autowired
  private IEmployeeDao employeeDao;
  @Autowired
  private IMusteriDao musteriDao;
  @Autowired
  private IFirmaDao firmaDao;

  private IFaturaDao faturaDao;


  @Autowired
  public FaturaService(@Qualifier("faturaDao") GenericDao<Fatura, Long> genericDao) {
    super(genericDao);
    this.faturaDao = (FaturaDao) genericDao;
  }

  @Override
  public List<FaturaDTO> getAllFatura() {
    List<FaturaDTO> lstFaturaDTO = new LinkedList<>();
    faturaDao.getAll().stream()
        .filter(fatura -> fatura.getBorc() != null && fatura.getBorc().getMusteri() != null)
        .forEach(fatura -> {
          lstFaturaDTO.add(getFaturaDTO(fatura));
        });
    return lstFaturaDTO;
  }

  @Override
  public FaturaDTO getFatura(Long faturaOid) {
    Fatura fatura = faturaDao.find(faturaOid);
    return getFaturaDTO(fatura);
  }

  private FaturaDTO getFaturaDTO(Fatura fatura) {
    SiparisListesiDTO siparisListesiDTO = borcService.getSiparis(fatura.getBorc().getOid());
    FaturaDTO faturaDTO = new FaturaDTO();
    MusteriDTO musteriDTO = musteriDao.getMusteriDTO(fatura.getBorc().getMusteri().getOid());

    faturaDTO.setSirketAdi(fatura.getFirma().getFirmaAdi());
    faturaDTO.setSirketLogoYolu(fatura.getFirma().getFirmaLogoYolu());
    faturaDTO.setMusteri(musteriDTO);

    faturaDTO.setFaturaNotu(fatura.getFaturaNotu());
    faturaDTO.setSiparisListesi(siparisListesiDTO);
    faturaDTO.setBorcOid(fatura.getBorc().getOid());
    faturaDTO.setEmployeeOid(fatura.getEmployee().getOid());

    faturaDTO.setCreatedDate(fatura.getCreatedDate());
    faturaDTO.setUpdatedDate(fatura.getUpdatedDate());
    faturaDTO.setOid(fatura.getOid());

    return faturaDTO;
  }


  @Override
  public boolean addFatura(FaturaDTO faturaDTO) {
    try {
      Fatura fatura = new Fatura();
      Borc borc = borcDao.find(faturaDTO.getBorcOid());

      Employee employee = employeeDao.find(faturaDTO.getEmployeeOid());
      Firma firma = firmaDao.getFirma(faturaDTO.getSirketAdi(), faturaDTO.getSirketLogoYolu());

      fatura.setEmployee(employee);
      fatura.setBorc(borc);
      fatura.setFaturaNotu(faturaDTO.getFaturaNotu());
      fatura.setFirma(firma);

      fatura.setFaturaTutari(BigDecimal.valueOf(faturaDTO.getFaturaTutari()));

      faturaDao.add(fatura);


    } catch (Exception ex) {
      LOGGER.error("Fatura service classınıda hata meydana geldi " + ex.getMessage());
      ex.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean updateFatura(FaturaDTO faturaDTO) {
    try {
      Fatura fatura = faturaDao.find(faturaDTO.getOid());

      Borc borc = borcDao.find(faturaDTO.getBorcOid());

      Employee employee = employeeDao.find(faturaDTO.getEmployeeOid());
      Firma firma = firmaDao.getFirma(faturaDTO.getSirketAdi(), faturaDTO.getSirketLogoYolu());

      fatura.setEmployee(employee);
      fatura.setBorc(borc);
      fatura.setFaturaNotu(faturaDTO.getFaturaNotu());
      fatura.setFirma(firma);

      fatura.setFaturaTutari(BigDecimal.valueOf(faturaDTO.getFaturaTutari()));

      faturaDao.update(fatura);
    } catch (Exception ex) {
      LOGGER.error("Fatura service classınıda hata meydana geldi " + ex.getMessage());
      ex.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean removeFatura(Long faturaOid) {
    return faturaDao.remove(faturaOid);
  }
}