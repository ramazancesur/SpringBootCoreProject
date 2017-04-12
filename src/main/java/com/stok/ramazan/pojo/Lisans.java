package com.stok.ramazan.pojo;

import java.util.Date;

public class Lisans extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Digest Algoritma Sonucu Nance kullanarak
	private String licenseKey;
	private Satici satici;
	private Date licenseStartTime;
	private Date licenseFinishTime;

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public Satici getSatici() {
		return satici;
	}

	public void setSatici(Satici satici) {
		this.satici = satici;
	}

	public Date getLicenseStartTime() {
		return licenseStartTime;
	}

	public void setLicenseStartTime(Date licenseStartTime) {
		this.licenseStartTime = licenseStartTime;
	}

	public Date getLicenseFinishTime() {
		return licenseFinishTime;
	}

	public void setLicenseFinishTime(Date licenseFinishTime) {
		this.licenseFinishTime = licenseFinishTime;
	}
}