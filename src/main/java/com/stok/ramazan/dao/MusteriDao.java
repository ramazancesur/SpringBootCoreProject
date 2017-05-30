package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.dto.AdresTelefon;
import com.stok.ramazan.dto.MusteriDTO;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.helper.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("musteriDao")
public class MusteriDao extends GenericDaoImpl<Musteri, Long> implements IMusteriDao {
    @Autowired
    private IBorcDao borcDao;
    @Autowired
    private IPaymentDao paymentDao;

    private Double getToplamBorcByMusteriOid(Long musteriOid) {
        return borcDao.getToplamBorcByMusteriOid(musteriOid) - paymentDao.getToplamOdemeByMusteriOid(musteriOid);
    }

    @Override
    public List<MusteriDTO> getAllMusteri() {
        List<MusteriDTO> lstMusteriDto = new LinkedList<>();
        List<Musteri> lstMusteri = this.getAll();
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

        return musteriDTO;
    }

    @Override
    public MusteriDTO getMusteriDTO(Long musteriOid) {
        return getMusteriDTO(this.find(musteriOid));
    }
}