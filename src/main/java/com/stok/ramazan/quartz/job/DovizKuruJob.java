package com.stok.ramazan.quartz.job;

import com.stok.ramazan.entity.Doviz;
import com.stok.ramazan.helper.Helper;
import com.stok.ramazan.jaxb.doviz.Currency;
import com.stok.ramazan.jaxb.doviz.TarihXML;
import com.stok.ramazan.service.interfaces.IDovizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.transaction.Transactional;

@Component
@Transactional
public class DovizKuruJob {
  private static final Logger LOGGER = LoggerFactory.getLogger(DovizKuruJob.class);
  @Autowired
  private IDovizService dovizService;

  Helper helper = Helper.getInstance();

  @Scheduled(cron = "0 0 2,18 * * ?")
  public void dovizKuruGuncelle() throws Exception {
    try {
      Doviz kayitliDoviz = new Doviz();
      List<Doviz> lstDoviz = getGuncelDovizKuru();
      for (Doviz doviz : lstDoviz) {
        kayitliDoviz = dovizService.getDovizKodunaGore(doviz.getDovizKodu());
        System.out.println("Doviz Alis ve Satıs Fiyatlari Guncellendi...");
        if (kayitliDoviz != null) {
          kayitliDoviz.setDovizAlis(doviz.getDovizAlis());
          kayitliDoviz.setDovizSatis(doviz.getDovizSatis());
          dovizService.update(kayitliDoviz);
        } else {
          dovizService.add(doviz);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      LOGGER.error("Doviz Kurları çekilirken hata oluştu... " + e.getMessage());
    }

  }

  public List<Doviz> getGuncelDovizKuru() throws Exception {
    List<Doviz> lstDoviz = new LinkedList<Doviz>();
    String xml = helper.readWebUrl("http://www.tcmb.gov.tr/kurlar/today.xml");
    TarihXML tarihXML = (TarihXML) Helper.unmarshal(xml, TarihXML.class);
    for (Currency currency : tarihXML.getLstCurrency()) {
      Doviz doviz = new Doviz();
      doviz.setBirim(currency.getUnit());
      doviz.setDovizAlis(BigDecimal.valueOf(currency.getAlisFiyati()));
      doviz.setDovizKodu(currency.getKod());
      doviz.setDovizSatis(BigDecimal.valueOf(currency.getSatisFiyati()));
      dovizService.add(doviz);
      lstDoviz.add(doviz);
    }
    return lstDoviz;
  }
}
