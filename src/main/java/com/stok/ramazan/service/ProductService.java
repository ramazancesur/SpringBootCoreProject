package com.stok.ramazan.service;

import com.stok.ramazan.android.dto.UrunDTO;
import com.stok.ramazan.dao.ProductDao;
import com.stok.ramazan.dao.interfaces.GenericDao;
import com.stok.ramazan.dao.interfaces.IPriceDao;
import com.stok.ramazan.dao.interfaces.IProductDao;
import com.stok.ramazan.entity.Price;
import com.stok.ramazan.entity.Product;
import com.stok.ramazan.service.interfaces.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService extends GenericServiceImpl<Product, Long>
    implements IProductService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
  private IProductDao productDao;
  @Autowired
  private IPriceDao priceDao;

  public ProductService() {
    // TODO Auto-generated constructor stub
  }

  @Autowired
  public ProductService(@Qualifier("productDao") GenericDao<Product, Long> genericDao) {
    super(genericDao);
    this.productDao = (ProductDao) genericDao;
  }

  @Override
  public List<UrunDTO> getAllUrun() {
    List<Product> lstProduct = productDao.getAll();
    List<UrunDTO> lstUrunDTO = new LinkedList<>();
    lstProduct.stream()
        .filter(x -> x.getLstPrice() != null)
        .forEach(product -> {
          lstUrunDTO.add(getUrunDTO(product));
        });
    return lstUrunDTO;
  }

  private UrunDTO getUrunDTO(Product product) {
    UrunDTO urunDTO = new UrunDTO();
    urunDTO.setCreatedDate(product.getCreatedDate());
    urunDTO.setProductName(product.getProductName());
    urunDTO.setPrice(product.getLstPrice().get(product.getLstPrice().size() - 1).getFiyati().doubleValue());
    urunDTO.setOid(product.getOid());
    urunDTO.setUpdatedDate(product.getUpdatedDate());
    urunDTO.setGelisTarihi(product.getCommingDate());
    urunDTO.setSonKullanmaTarihi(product.getSonKullanmaTarihi());
    urunDTO.setUrunAciklamasi(product.getAciklama());
    urunDTO.setUnitType(product.getUnitType());
    return urunDTO;
  }

  @Override
  public UrunDTO getUrunDTO(Long urunOid) {
    Product product = productDao.find(urunOid);
    if (product != null) {
      return getUrunDTO(product);
    } else {
      return null;
    }
  }

  @Override
  public boolean addUrunDTO(UrunDTO urunDTO) {
    try {
      Product product = productDao.find(urunDTO.getOid());
      Price price = product.getLstPrice().get(product.getLstPrice().size() - 1);
      price.setAciklamasi("Mobile den gelen veri product name " + urunDTO.getProductName());
      price.setFiyati(BigDecimal.valueOf(urunDTO.getPrice()));
      priceDao.update(price);

      product.setAciklama(urunDTO.getUrunAciklamasi());
      product.setCommingDate(urunDTO.getGelisTarihi());
      product.setSonKullanmaTarihi(urunDTO.getSonKullanmaTarihi());
      product.setProductName(urunDTO.getProductName());
      product.setUnitType(urunDTO.getUnitType());
      product.setLstPrice(Arrays.asList(price));
      productDao.update(product);
      return true;
    } catch (Exception ex) {
      LOGGER.error(getClass().getSimpleName() + " classında hata alındı " + ex.getMessage());
      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateUrunDTO(UrunDTO urunDTO) {
    try {
      Price price = new Price();
      price.setAciklamasi("Mobile den gelen veri product name " + urunDTO.getProductName());
      price.setFiyati(BigDecimal.valueOf(urunDTO.getPrice()));
      priceDao.add(price);
      Product product = new Product();
      product.setAciklama(urunDTO.getUrunAciklamasi());
      product.setCommingDate(urunDTO.getGelisTarihi());
      product.setSonKullanmaTarihi(urunDTO.getSonKullanmaTarihi());
      product.setProductName(urunDTO.getProductName());
      product.setUnitType(urunDTO.getUnitType());
      product.setLstPrice(Arrays.asList(price));
      productDao.add(product);
      return true;
    } catch (Exception ex) {
      LOGGER.error(getClass().getSimpleName() + " classında hata alındı " + ex.getMessage());
      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteUrunDTO(Long urunOid) {
    return productDao.remove(urunOid);
  }
}
