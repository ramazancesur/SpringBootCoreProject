package com.stok.ramazan.service;

import com.stok.ramazan.dao.MessageLogDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IMessageLogDao;
import com.stok.ramazan.entity.MessageLog;
import com.stok.ramazan.helper.SmsSender;
import com.stok.ramazan.service.interfaces.IMessageLogService;
import com.stok.ramazan.settings.SmtpMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * Created by LocalAdmin on 13.06.2017.
 */
@Service
public class MessageLogService extends GenericServiceImpl<MessageLog, Long>
        implements IMessageLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageLog.class);
    @Autowired
    SmtpMailSender mailSender;
    SmsSender send = new SmsSender();
    private IMessageLogDao messageLogDao;

    @Autowired
    public MessageLogService(@Qualifier("messageLogDao") GenericDao<MessageLog, Long> genericDao) {
        super(genericDao);
        this.messageLogDao = (MessageLogDao) genericDao;
    }

    public void sendSMS(MessageLog messageLog) {
        String content = "Sayın " + messageLog.getMusteriAdSoyad() + " " + messageLog.getMessageContent();
        try {
            send.sendData(content, messageLog.getPhoneNumber());
            LOGGER.info("mesaj basari ile gonderildi ");
        } catch (Exception ex) {
            LOGGER.error("MESSAGE LOG SERVİSİNDE HATA MEYDANA GELDİ " + ex.getMessage());
            ex.printStackTrace();
            try {
                mailSender.send("ramazancesur3@gmail.com", "Halı Yıkama Uygulaması Mesaj Gonderim Hatası", content);
            } catch (MessagingException e) {
                LOGGER.error("mail atarken hata olustu  " + ex.getMessage());
                e.printStackTrace();
            }
        }

    }
}