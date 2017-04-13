package com.stok.ramazan.service.interfaces;

import com.stok.ramazan.entity.Doviz;

public interface IDovizService extends IGenericService<Doviz, Long> {
	Doviz getDovizKodunaGore(String dovizKodu);
}
