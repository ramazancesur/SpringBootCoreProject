package com.stok.ramazan.annotations.processor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public interface IValidationProcessor extends Serializable {
  boolean lenghtControl(Object value, int lenght) throws Exception;

  boolean minLenghtControl(Object value, int lenght);

  <T extends Serializable> List<T> getJSonDataControl(String json, List<Class> lstRequaredClass, Class masterData)
      throws Exception;

  int getBasamakSayisi(Object value);

  Boolean generalControl(Object value, Method[] methods, HashMap<String, Integer> lenghtMinMax)
      throws ClassNotFoundException;
}
