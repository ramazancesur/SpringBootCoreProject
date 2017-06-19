package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.OdemeDTO;
import com.stok.ramazan.dao.PaymentDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IBorcDao;
import com.stok.ramazan.dao.interfaces.IFirmaDao;
import com.stok.ramazan.dao.interfaces.IMusteriDao;
import com.stok.ramazan.dao.interfaces.IPaymentDao;
import com.stok.ramazan.dao.interfaces.ISubeDao;
import com.stok.ramazan.entity.Borc;
import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Musteri;
import com.stok.ramazan.entity.Payment;
import com.stok.ramazan.entity.Sube;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.service.interfaces.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class PaymentService extends GenericServiceImpl<Payment, Long> implements IPaymentService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private IPaymentDao paymentDao;
    @Autowired
    private IBorcDao borcDao;
    @Autowired
    private IMusteriDao musteriDao;

    @Autowired
    private IFirmaDao firmaDao;

    @Autowired
    private ISubeDao subeDao;

    public PaymentService() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    public PaymentService(@Qualifier("paymentDao") GenericDao<Payment, Long> genericDao) {
        super(genericDao);
        this.paymentDao = (PaymentDao) genericDao;
    }

    @Override
    public List<OdemeDTO> getAllOdemeDTO() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println(name);
        List<OdemeDTO> lstOdemeDTO = new LinkedList<>();
        paymentDao.getAll().stream()
                .filter(x -> x.getBorc() != null && x.getBorc().getMusteri() != null && x.getBorc().getKalanBorc() != null)
                .forEach(x -> {
                    lstOdemeDTO.add(getOdemeDTO(x));
                });
        return lstOdemeDTO;
    }


    private OdemeDTO getOdemeDTO(Payment payment) {
        OdemeDTO odemeDTO = new OdemeDTO();
        odemeDTO.setBorcOid(payment.getBorc().getOid());
        odemeDTO.setKalanMiktar(payment.getBorc().getKalanBorc().doubleValue());
        odemeDTO.setMusteriAdi(payment.getBorc().getMusteri().getAdi());
        odemeDTO.setMusteriSoyadi(payment.getBorc().getMusteri().getSoyadi());
        odemeDTO.setMusteriOid(payment.getBorc().getMusteri().getOid());
        odemeDTO.setOid(payment.getOid());
        odemeDTO.setOdemeTarihi(payment.getGerceklesenOdemeTarihi());
        odemeDTO.setOdemeMiktari(payment.getOdemeTutari().doubleValue());
        odemeDTO.setKalanMiktar(payment.getBorc().getKalanBorc().doubleValue());

        return odemeDTO;
    }

    @Override
    public OdemeDTO getOdemeDTO(Long oid) {
        Payment payment = paymentDao.find(oid);
        return getOdemeDTO(payment);
    }

    @Override
    public boolean deleteOdeme(OdemeDTO odemeDTO) {
        try {
            this.remove(paymentDao.find(odemeDTO.getOid()));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(getClass().getSimpleName() + " classında hata meydana geldi  " + ex.getMessage());
            return false;
        }
    }

    @Override
    public OdemeDTO addOdeme(OdemeDTO odemeDTO) {
        Borc borc = borcDao.find(odemeDTO.getBorcOid());
        borc.setKalanBorc(BigDecimal.valueOf(borc.getKalanBorc().doubleValue() - odemeDTO.getOdemeMiktari()));
        borcDao.update(borc);

        Payment payment = new Payment();
        payment.setBeklenenOdemeTarihi(odemeDTO.getOdemeTarihi());
        payment.setGerceklesenOdemeTarihi(odemeDTO.getOdemeTarihi());
        payment.setBorc(borc);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        Firma firma= firmaDao.getFirma(userName);
        List<Sube> lstSube= subeDao.getAllSube(firma.getOid());

        if (lstSube.size()==0){
            Sube sube=new Sube();
            sube.setAdress(firma.getAdress());
            sube.setFirma(firma);
            sube.setFirmaAdi(firma.getFirmaAdi());
            sube.setFirmaTipi(EnumUtil.FirmaTipi.KAYITLI_FIRMA);
            sube.setLstConduct(firma.getLstConduct());
            subeDao.add(sube);

            payment.setSaticiSube(sube);
        }


        // Düzeltilecek

        payment.setSaticiSube(lstSube.get(0));

        paymentDao.add(payment);
        return odemeDTO;
    }

    @Override
    public OdemeDTO updateOdeme(OdemeDTO odemeDTO) {
        Musteri musteri = musteriDao.find(odemeDTO.getMusteriOid());
        musteri.setAdi(odemeDTO.getMusteriAdi());
        musteri.setSoyadi(odemeDTO.getMusteriSoyadi());
        musteriDao.update(musteri);

        Borc borc = borcDao.find(odemeDTO.getBorcOid());
        borc.setKalanBorc(BigDecimal.valueOf(borc.getKalanBorc().doubleValue() - odemeDTO.getOdemeMiktari()));
        borc.setMusteri(musteri);
        borcDao.update(borc);

        Payment payment = paymentDao.find(odemeDTO.getOid());
        payment.setBeklenenOdemeTarihi(odemeDTO.getOdemeTarihi());
        payment.setGerceklesenOdemeTarihi(odemeDTO.getOdemeTarihi());
        payment.setBorc(borc);
        paymentDao.update(payment);

        return odemeDTO;
    }
}
