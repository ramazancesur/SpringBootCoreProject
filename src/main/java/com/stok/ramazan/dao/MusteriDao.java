package com.stok.ramazan.dao;

import com.stok.ramazan.android.dto.AdresTelefon;
import com.stok.ramazan.android.dto.MusteriDTO;
import com.stok.ramazan.dao.interfaces.*;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.ISubeService;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Repository("musteriDao")
@Transactional
public class MusteriDao extends GenericDaoImpl<Musteri, Long> implements IMusteriDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusteriDao.class);

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

    public List<Musteri> getAllMusteriByUser(Long firmOid) {
        Criteria criteria = currentSession().createCriteria(Musteri.class, "musteri");
        criteria.createAlias("musteri.firma", "firma");
        criteria.add(Restrictions.eq("firma.oid", firmOid));
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
        musteri.setMusteriToplamBorcu(this.getMusteriToplamBorcu(musteri.getOid()));
        this.update(musteri);
        musteri.getLstConduct().stream()
                .filter(y -> y != null)
                .forEach(y -> {
                    AdresTelefon adresTelefon = new AdresTelefon();
                    adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
                    adresTelefon.setDeger(y.getTelNo());
                    adresTelefon.setTelTipi(EnumUtil.ContactTipi.GENEL);
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
        try {
            musteriDTO.setToplamBorc(Double.valueOf(musteri.getMusteriToplamBorcu().longValue()));
        } catch (Exception ex) {
            LOGGER.error("musteri dao da musteri toplam borcu hesaplanamadi ", ex.getMessage());
            if (ex instanceof NullPointerException) {
                musteri.setMusteriToplamBorcu(BigDecimal.ZERO);
                this.update(musteri);
            }
            musteriDTO.setToplamBorc(0.0);
        }

        musteriDTO.setFirmMusteriOid(musteri.getFirma().getOid());

        return musteriDTO;
    }

    @Override
    public MusteriDTO getMusteriDTO(Long musteriOid) {
        return getMusteriDTO(this.find(musteriOid));
    }

    private BigDecimal musteriToplamBorc(Long musteriOid, Long firmaOid) {
        String hql = "select sum(borc.toplamBorc) as totalBorc from Borc as borc" +
                " inner join  borc.musteri as musteri " +
                " inner join musteri.firma as firma " +
                "  where musteri.oid= :musteriOid  " +
                " and musteri.entityState= :entityState " +
                " and firma.oid= :firmaOid \n " +
                " and borc.entityState= :entityState group by musteri.oid , borc.oid";
        Query query = currentSession().createQuery(hql);
        query.setParameter("musteriOid", musteriOid);
        query.setParameter("firmaOid", firmaOid);
        query.setParameter("entityState", EnumUtil.EntityState.ACTIVE);
        BigDecimal totalBorc = BigDecimal.ZERO;
        List<BigDecimal> lstTotalBorc = query.list();
        for (BigDecimal decimal : lstTotalBorc) {
            totalBorc = totalBorc.add(decimal);
        }
        return totalBorc;
    }

    @Override
    public BigDecimal getMusteriToplamBorcu(Long musteriOid) {
        // Borclar Toplamını Bulacan
        // Paymentlar Toplamını Bulacan ikisini çıkartacan
        Firma firma = subeService.getFirmByUser();

        String hql = "select sum (payment.odemeTutari) as totalPayment \n " +
                " from Payment as payment" +
                " inner join payment.borc as borc  " +
                " inner join borc.musteri as musteri  inner join musteri.firma as firma \n " +
                " where musteri.entityState= :entityState and borc.entityState= :entityState \n" +
                " and payment.entityState= :entityState and musteri.oid= :musteriOid  " +
                " and firma.oid= :firmaOid \n " +
                "group by musteri.oid, payment.oid";

        Query query = currentSession().createQuery(hql);
        query.setParameter("musteriOid", musteriOid);
        query.setParameter("entityState", EnumUtil.EntityState.ACTIVE);
        query.setParameter("firmaOid", firma.getOid());
        try {
            List<BigDecimal> lstTotalOdeme = query.list();
            BigDecimal totalOdeme = BigDecimal.ZERO;

            for (BigDecimal decimal : lstTotalOdeme) {
                totalOdeme = totalOdeme.add(decimal);
            }

            BigDecimal toplamBorc = this.musteriToplamBorc(musteriOid, firma.getOid());
            return toplamBorc.subtract(totalOdeme);
        } catch (Exception ex) {
            LOGGER.error("musteri Kalan Borcu Hesaplarken hata ", ex.getMessage());
            return BigDecimal.ZERO;
        }
    }
}