package com.stok.ramazan.dao;

import com.stok.ramazan.dao.interfaces.IMessageLogDao;
import com.stok.ramazan.entity.MessageLog;
import org.springframework.stereotype.Repository;

/**
 * Created by LocalAdmin on 13.06.2017.
 */
@Repository("messageLogDao")
public class MessageLogDao extends GenericDaoImpl<MessageLog, Long>
    implements IMessageLogDao {
}