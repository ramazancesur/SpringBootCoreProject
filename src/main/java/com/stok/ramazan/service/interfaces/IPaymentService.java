package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.android.dto.OdemeDTO;
import com.stok.ramazan.entity.Payment;

import java.util.List;

public interface IPaymentService extends IGenericService<Payment, Long> {
    List<OdemeDTO> getAllOdemeDTO();

    OdemeDTO getOdemeDTO(Long oid);

    boolean deleteOdeme(OdemeDTO odemeDTO);

    OdemeDTO addOdeme(OdemeDTO odemeDTO);

    OdemeDTO updateOdeme(OdemeDTO odemeDTO);
}