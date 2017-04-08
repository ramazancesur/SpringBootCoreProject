package com.stok.ramazan.helper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Helper {
	private Helper() {

	}

	private static Helper instance;

	public static Helper getInstance() {
		if (instance == null) {
			instance = new Helper();
		}
		return instance;
	}

	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		if (email != null) {
			try {
				InternetAddress emailAddr = new InternetAddress(email);
				emailAddr.validate();
			} catch (AddressException ex) {
				result = false;
			}
			return result;
		} else {
			return false;
		}
	}
}
