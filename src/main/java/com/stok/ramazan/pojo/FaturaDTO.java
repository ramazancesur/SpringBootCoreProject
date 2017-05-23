package com.stok.ramazan.pojo;

public class FaturaDTO extends BaseDTO {
	/**
	 * 
	 */
	private String customNameSurname;
	private double totalPayment;
	private String LogoPath;
	private String customAdress;
	private SiparisListesiDTO siparisListesi;

	public String getCustomNameSurname() {
		return customNameSurname;
	}

	public void setCustomNameSurname(String customNameSurname) {
		this.customNameSurname = customNameSurname;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public String getLogoPath() {
		return LogoPath;
	}

	public void setLogoPath(String logoPath) {
		LogoPath = logoPath;
	}

	public String getCustomAdress() {
		return customAdress;
	}

	public void setCustomAdress(String customAdress) {
		this.customAdress = customAdress;
	}

	public SiparisListesiDTO getSiparisListesi() {
		return siparisListesi;
	}

	public void setSiparisListesi(SiparisListesiDTO siparisListesi) {
		this.siparisListesi = siparisListesi;
	}
}
