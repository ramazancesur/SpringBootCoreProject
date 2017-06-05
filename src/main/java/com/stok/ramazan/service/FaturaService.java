package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.FaturaDTO;
import com.stok.ramazan.android.dto.MusteriDTO;
import com.stok.ramazan.android.dto.SiparisListesiDTO;
import com.stok.ramazan.dao.FaturaDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IFaturaDao;
import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.entity.Fatura;
import com.stok.ramazan.service.interfaces.IBorcService;
import com.stok.ramazan.service.interfaces.IFaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by LocalAdmin on 02.06.2017.
 */
@Service
public class FaturaService extends GenericServiceImpl<Fatura, Long>
        implements IFaturaService {
    @Autowired
    IMusteriDao musteriDao;
    @Autowired
    IBorcService borcService;
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
        faturaDTO.setMusteri(musteriDTO);
        faturaDTO.setSirketAdi(fatura.getSirketAdi());
        faturaDTO.setSirketLogoYolu(fatura.getSirketLogoPath());
        faturaDTO.setSiparisListesi(siparisListesiDTO);
        faturaDTO.setCreatedDate(fatura.getCreatedDate());
        faturaDTO.setUpdatedDate(fatura.getUpdatedDate());
        faturaDTO.setOid(fatura.getOid());

        return faturaDTO;
    }


    @Override
    public boolean addFatura(FaturaDTO faturaDTO) {
        return false;
    }

    @Override
    public boolean updateFatura(FaturaDTO faturaDTO) {
        return false;
    }

    @Override
    public boolean removeFatura(Long faturaOid) {
        return faturaDao.remove(faturaOid);
    }
}