package com.stok.ramazan.quartz.job;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stok.ramazan.entity.Doviz;
import com.stok.ramazan.helper.Helper;
import com.stok.ramazan.service.interfaces.IDovizService;

@Component
@Transactional
public class DovizKuruJob {
	@Autowired
	private IDovizService dovizService;
	private static final Logger LOGGER = LoggerFactory.getLogger(DovizKuruJob.class);

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
		String url = "http://www.tcmb.gov.tr/kurlar/today.xml";
		Document doc1 = Jsoup.parse(Helper.readUrlNonCookie(url), "UTF-8", Parser.xmlParser());
		int sayac = 0;
		for (Element e : doc1.select("isim")) {
			if (sayac < 8) {
				Doviz doviz = new Doviz();
				doviz.setDovizKodu(e.ownText());
				doviz.setDovizCinsi(e.ownText());
				lstDoviz.add(doviz);
				sayac++;
			}
		}
		sayac = 0;
		for (Element e : doc1.select("forexbuying")) {
			if (sayac < 8) {
				lstDoviz.get(sayac).setDovizAlis(new BigDecimal(e.ownText()));
				sayac++;
			}
		}
		sayac = 0;
		for (Element e : doc1.select("forexselling")) {
			if (sayac < 8) {
				lstDoviz.get(sayac).setDovizSatis(new BigDecimal(e.ownText()));
				sayac++;
			}
		}
		sayac = 0;
		for (Element e : doc1.select("unit")) {
			if (sayac < 8) {
				lstDoviz.get(sayac).setBirim(Integer.parseInt(e.ownText()));
				sayac = sayac + 1;
			}
		}
		return lstDoviz;
	}
}
