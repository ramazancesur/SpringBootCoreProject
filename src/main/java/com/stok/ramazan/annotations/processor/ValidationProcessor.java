package com.stok.ramazan.annotations.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ValidationProcessor implements IValidationProcessor {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {

        ValidationProcessor test = new ValidationProcessor();
        HashMap<String, Integer> data = new LinkedHashMap<String, Integer>();

        data.put("data", 12);
        data.put("lenght", 12);
        ValidationProcessor validation = new ValidationProcessor();
        try {
            System.out.println(test.generalControl(12, ValidationProcessor.class.getDeclaredMethods(), data));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean lenghtControl(Object value, int lenght) throws Exception {
        if (lenght != 0) {
            if (value instanceof String) {
                String data = (String) value;
                if (data.length() <= lenght) {
                    return true;
                } else {
                    return false;
                }
            } else {
                try {
                    if (getBasamakSayisi(value) <= lenght) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception ex) {
                    throw new Exception("Error102: Gecersiz İslem Number Olmayan Değer Ataması");
                }
            }
        } else {
            return true;
        }

    }

    @Override
    public boolean minLenghtControl(Object value, int lenght) {
        if (getBasamakSayisi(value) < lenght) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public <T extends Serializable> List<T> getJSonDataControl(String json, List<Class> lstRequaredClass,
                                                               Class masterData) throws Exception {
        List<T> lstSeperatedValue = new LinkedList<T>();
        JSONObject jsonRoot = new JSONObject(json);
        JSONObject jsonObj = new JSONObject();
        ObjectMapper objMapper = new ObjectMapper();
        for (Class clazz : lstRequaredClass) {
            if (jsonObj.isNull(clazz.getSimpleName())) {
                logger.error("Validation sirasinda hata meydana geldi... " + clazz.getSimpleName());
                throw new Exception("Error 103: eksik içerik " + clazz.getSimpleName());
            } else {
                jsonObj = jsonRoot.getJSONObject(clazz.getSimpleName());
                T data = (T) objMapper.readValue(jsonObj.toString(), clazz);
                lstSeperatedValue.add(data);
                jsonRoot.remove(clazz.getSimpleName());
            }
        }

        if (jsonObj.isNull(masterData.getClass().getSimpleName())) {
            logger.error("Validation sirasinda hata meydana geldi... " + masterData.getClass().getSimpleName());
            throw new Exception("Error 103: eksik içerik " + masterData.getClass().getSimpleName());
        } else {
            T data = (T) objMapper.readValue(jsonObj.toString(), masterData);
            lstSeperatedValue.add(data);
        }
        return lstSeperatedValue;
    }

    @Override
    public int getBasamakSayisi(Object value) {
        return (int) (Math.log10(((Integer) value).longValue()) + 1);
    }

    // ileride generic method yazma durumu için yazılmıştır sadece bir tane
    // örnektir
    @Override
    public Boolean generalControl(Object value, Method[] methods, HashMap<String, Integer> lenghtMinMax)
            throws ClassNotFoundException {
        Boolean flag = true;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("rapor/ramazan.txt").getFile());

        Method serviceMethod = null;
        Class<?> base = Class.forName(this.getClass().getName());
        for (Method method : methods) {
            method.setAccessible(true);
            try {
                if (method.getName().equals("minLenghtControl")) {
                    serviceMethod = base.getMethod("minLenghtControl", Object.class, int.class);
                    flag = Boolean.parseBoolean(serviceMethod
                            .invoke(base.newInstance(), lenghtMinMax.get("data"), lenghtMinMax.get("lenght"))
                            .toString());
                } else if (method.getName().equals("lenghtControl")) {
                    serviceMethod = base.getMethod("lenghtControl", Object.class, int.class);
                    flag = Boolean.parseBoolean(serviceMethod
                            .invoke(base.newInstance(), lenghtMinMax.get("data"), lenghtMinMax.get("lenght"))
                            .toString());
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
