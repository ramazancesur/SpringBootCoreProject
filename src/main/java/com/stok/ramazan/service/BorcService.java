package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.SiparisDTO;
import com.stok.ramazan.android.dto.SiparisListesiDTO;
import com.stok.ramazan.android.dto.UrunDTO;
import com.stok.ramazan.dao.BorcDao;
import com.stok.ramazan.dao.interfaces.*;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.entity.BorcDetay;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Product;
import com.stok.ramazan.service.interfaces.IBorcService;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class BorcService extends GenericServiceImpl<Borc, Long>
        implements IBorcService {
    private IBorcDao borcDao;

    @Autowired
    private IBorcDetayDao borcDetayDao;

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
                    .filter(x -> x.getProduct() != null && x.getProduct().getLstPrice() != null)
                    .forEach(borcDetay -> {
                        siparisDTO.setAdet(borcDetay.getAdet());
                        siparisDTO.setMetre(borcDetay.getMetre());
                        siparisDTO.setOid(borcDetay.getOid());
                        siparisDTO.setBorcOid(borc.getOid());
                        siparisDTO.setUcreti(borcDetay.getProduct().getLstPrice().
                                get(borcDetay.getProduct().getLstPrice().size() - 1).getFiyati().doubleValue());
                        siparisDTO.setCreatedDate(borcDetay.getCreatedDate());
                        siparisDTO.setUpdatedDate(borcDetay.getUpdatedDate());

                        UrunDTO urunDTO = new UrunDTO();
                        Product product = borcDetay.getProduct();
                        urunDTO.setGelisTarihi(new Date());
                        urunDTO.setSonKullanmaTarihi(product.getSonKullanmaTarihi());
                        urunDTO.setPrice(product.getLstPrice().get(product.getLstPrice().size() - 1).getFiyati().doubleValue());
                        urunDTO.setProductName(product.getProductName());
                        urunDTO.setCreatedDate(product.getCreatedDate());
                        urunDTO.setUpdatedDate(product.getUpdatedDate());
                        siparisDTO.setUrun(urunDTO);

                        lstSiparis.add(siparisDTO);
                    });
            siparisListesiDTO.setSiparisDurum(borc.getSiparisDurum());
            siparisListesiDTO.setLstSiparisDTOS(lstSiparis);
            siparisListesiDTO.setBeklenenTeslimatTarihi(borc.getBeklenenOdemeTarihi());
            siparisListesiDTO.setMusteri(musteriDao.getMusteriDTO(borc.getMusteri().getOid()));
            siparisListesiDTO.setToplamSiparisBorcu(borc.getToplamBorc().doubleValue());
        }
        return siparisListesiDTO;
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
                lstBorcDetay.add(borcDetay);
            }
            borc.setOdemeSube(subeService.getUserFirmSube());
            borc.setSiparisDurum(siparisListesiDTO.getSiparisDurum());
            borc.setLstBorceDetay(lstBorcDetay);
            borc.setToplamBorc(BigDecimal.valueOf(siparisListesiDTO.getToplamSiparisBorcu()));
            borc.setMusteri(musteriDao.find(siparisListesiDTO.getMusteri().getOid()));
            borc.setKalanBorc(BigDecimal.valueOf(siparisListesiDTO.getToplamSiparisBorcu()));
            borc.setMusteriNotu(siparisListesiDTO.getMusteriNotu());
            borc.setSaticiNotu(siparisListesiDTO.getSaticiNotu());
            borc.setBeklenenOdemeTarihi(siparisListesiDTO.getBeklenenTeslimatTarihi());
            borcDao.add(borc);
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
                lstBorcDetay.add(borcDetay);
            }
            borc.setLstBorceDetay(lstBorcDetay);
            borc.setToplamBorc(BigDecimal.valueOf(siparisListesiDTO.getToplamSiparisBorcu()));
            borc.setMusteri(musteriDao.find(siparisListesiDTO.getMusteri().getOid()));
            borc.setKalanBorc(BigDecimal.valueOf(siparisListesiDTO.getToplamSiparisBorcu()));
            borc.setMusteriNotu(siparisListesiDTO.getSaticiNotu());
            borc.setSaticiNotu(siparisListesiDTO.getSaticiNotu());
            borc.setBeklenenOdemeTarihi(siparisListesiDTO.getBeklenenTeslimatTarihi());
            borcDao.add(borc);
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