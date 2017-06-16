package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.entity.MessageLog;

/**
 * Created by LocalAdmin on 13.06.2017.
 */
public interface IMessageLogService extends IGenericService<MessageLog, Long> {
  void sendSMS(MessageLog messageLog);
}