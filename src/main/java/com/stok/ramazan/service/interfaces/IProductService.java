package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.dto.UrunDTO;
import com.stok.ramazan.entity.Product;

import java.util.List;

public interface IProductService extends IGenericService<Product, Long> {
    List<UrunDTO> getAllUrun();

    UrunDTO getUrunDTO(Long urunOid);

    boolean addUrunDTO(UrunDTO urunDTO);

    boolean updateUrunDTO(UrunDTO urunDTO);

    boolean deleteUrunDTO(Long urunOid);
}