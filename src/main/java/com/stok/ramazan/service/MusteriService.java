package com.stok.ramazan.service;

import com.stok.ramazan.dao.MusteriDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dto.MusteriDTO;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.service.interfaces.IMusteriService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusteriService extends GenericServiceImpl<Musteri, Long> implements IMusteriService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MusteriService.class);
    private MusteriDao musteriDao;

    public MusteriService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public MusteriService(@Qualifier("musteriDao") GenericDao<Musteri, Long> genericDao) {
        super(genericDao);
        this.musteriDao = (MusteriDao) genericDao;
    }

    @Override
    public List<MusteriDTO> getAllMusteriDTO() {
        return musteriDao.getAllMusteri();
    }

    @Override
    public boolean deleteMusteriDto(Long oid) {
        try {
            this.remove(this.get(oid));
            return true;
        } catch (Exception ex) {
            LOGGER.error("Hata meydana geldi  " + this.getClass().getSimpleName() + ex.getMessage());
            return false;
        }
    }

    @Override
    public MusteriDTO getMusteriDTO(Long musteriOid) {
        return musteriDao.getMusteriDTO(musteriOid);
    }
}