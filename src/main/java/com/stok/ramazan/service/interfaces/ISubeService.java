package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.entity.Firma;
import com.stok.ramazan.entity.Sube;

public interface ISubeService extends IGenericService<Sube, Long> {
   Sube getUserFirmSube();
   Firma getFirmByUser();
}