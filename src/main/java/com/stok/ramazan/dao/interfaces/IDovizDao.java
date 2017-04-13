package com.stok.ramazan.dao.interfaces;

import com.stok.ramazan.entity.Doviz;

public interface IDovizDao extends GenericDao<Doviz, Long> {
	Doviz getDovizKodunaGore(String dovizKodu);
}
