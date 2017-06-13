package com.stok.ramazan.helper;

import java.util.Date;

/**
 * Created by LocalAdmin on 13.06.2017.
 */
public class LicansGenerator {

    public String generatePassword(String username) {
        String password = Helper.generateUnique();
        Date date = new Date(System.currentTimeMillis());
        System.out.println("PaswordString :" + password);
        String nonce = NonceAndDiggestPassword.buildNonce(date);
        String passDiggest = NonceAndDiggestPassword.buildDiggest(password, nonce, date.toString());
        return passDiggest;
    }
}