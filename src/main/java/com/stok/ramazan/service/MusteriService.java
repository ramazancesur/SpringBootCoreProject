package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.AdresTelefon;
import com.stok.ramazan.android.dto.MusteriDTO;
import com.stok.ramazan.dao.MusteriDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IAdressDao;
import com.stok.ramazan.dao.interfaces.IConductDao;
import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.entity.Address;
import com.stok.ramazan.entity.Conduct;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.IMusteriService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MusteriService extends GenericServiceImpl<Musteri, Long> implements IMusteriService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MusteriService.class);
    private MusteriDao musteriDao;

    @Autowired
    private IAdressDao adresDao;

    @Autowired
    private IConductDao conductDao;

    @Autowired
    private IFirmaDao firmaDao;

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

    @Override
    public MusteriDTO addMusteriDTO(MusteriDTO musteriDTO) {
        Musteri musteri = new Musteri();
        musteri.setAdi(musteriDTO.getAd());
        musteri.setSoyadi(musteriDTO.getSoyad());
        List<Address> lstAddres = new LinkedList<>();
        List<Conduct> lstConduct = new LinkedList<>();
        List<AdresTelefon> lstAdresTel = musteriDTO.getLstAdresTel();
        lstAdresTel.stream()
                .filter(x -> x != null)
                .forEach(x -> {
                    if (x.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
                        Address address = new Address();
                        address.setAdresTipi(x.getAddresTipi());
                        address.setAdresKullaniciTipi(EnumUtil.AdresKullaniciTipi.MUSTERI);
                        address.setAdres(x.getDeger());
                        adresDao.add(address);
                        lstAddres.add(address);
                    } else if (x.getTelOrAddres() == EnumUtil.TelOrAddres.TELEFON) {
                        Conduct conduct = new Conduct();
                        conduct.setTelNo(x.getDeger());
                        conduct.setContactType(x.getTelTipi());
                        conductDao.add(conduct);
                        lstConduct.add(conduct);
                    }
                });
        musteri.setLstAddress(lstAddres);
        musteri.setLstConduct(lstConduct);
        Firma firma= firmaDao.find(musteriDTO.getFirmMusteriOid());

        musteri.setFirma(firma);

        musteriDao.add(musteri);
        return musteriDTO;
    }

    @Override
    public MusteriDTO updateMusteriDTO(MusteriDTO musteriDTO) {
        Musteri musteri = musteriDao.find(musteriDTO.getOid());
        if (musteri == null) {
            LOGGER.error("musteri bulunamadı " + this.getClass().getSimpleName() + " " + musteriDTO.getOid());
            return null;
        } else {
            musteri.setAdi(musteriDTO.getAd());
            musteri.setSoyadi(musteriDTO.getSoyad());
            List<Conduct> lstConduct = new LinkedList<>();
            List<Address> lstAdres = new LinkedList<>();
            musteriDTO.getLstAdresTel().stream()
                    .filter(c -> c.getDeger() != null)
                    .forEach(x -> {
                        if (x.getTelOrAddres() == EnumUtil.TelOrAddres.TELEFON) {
                            Conduct conduct = conductDao.find(x.getOid());
                            conduct.setContactType(x.getTelTipi());
                            conduct.setTelNo(x.getDeger());
                            lstConduct.add(conduct);
                        } else if (x.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
                            Address address = adresDao.find(x.getOid());
                            address.setAdres(x.getDeger());
                            address.setAdresKullaniciTipi(EnumUtil.AdresKullaniciTipi.MUSTERI);
                            address.setAdresTipi(x.getAddresTipi());
                            lstAdres.add(address);
                        }
                    });
            musteri.setLstConduct(lstConduct);
            musteri.setLstAddress(lstAdres);
            return musteriDTO;
        }
    }
}