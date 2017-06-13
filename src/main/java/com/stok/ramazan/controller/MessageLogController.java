package com.stok.ramazan.controller;

import com.stok.ramazan.entity.MessageLog;
import com.stok.ramazan.service.MessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LocalAdmin on 13.06.2017.
 */
@RestController
public class MessageLogController extends BaseController<MessageLog> {

    @Autowired
    private MessageLogService messageLogService;

    @Override
    @GetMapping("MessageLog/all")
    public ResponseEntity<List<MessageLog>> getAll() {
        List<MessageLog> lstMessageLog = messageLogService.getAll();
        return new ResponseEntity<List<MessageLog>>(lstMessageLog, HttpStatus.OK);
    }

    @Override
    @GetMapping("/MessageLog/{id}")
    public ResponseEntity<MessageLog> getDataById(Long id) {
        MessageLog messageLog = messageLogService.get(id);
        return new ResponseEntity<MessageLog>(messageLog, HttpStatus.OK);
    }

    @Override
    @PostMapping("/MessageLog")
    public ResponseEntity<Boolean> addData(MessageLog data) {
        boolean flag = false;
        try {
            messageLogService.add(data);
            messageLogService.sendSMS(data);
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
    }

    @Override
    @PutMapping("/MessageLog")
    public ResponseEntity<Boolean> updateData(MessageLog data) {
        boolean flag = false;
        try {
            messageLogService.update(data);
            messageLogService.sendSMS(data);
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/MessageLog")
    public ResponseEntity<Boolean> deleteData(MessageLog data) {
        boolean flag = false;
        try {
            messageLogService.remove(data);
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
    }
}