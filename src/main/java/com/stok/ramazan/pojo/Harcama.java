package com.stok.ramazan.pojo;

public class Harcama extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MoneyType moneyType;
	private String harcamaAciklamasi;
	private Calisan calisan;
	private double spendAmount;

	public String getHarcamaAciklamasi() {
		return harcamaAciklamasi;
	}

	public void setHarcamaAciklamasi(String harcamaAciklamasi) {
		this.harcamaAciklamasi = harcamaAciklamasi;
	}

	public MoneyType getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(MoneyType moneyType) {
		this.moneyType = moneyType;
	}

	public Calisan getCalisan() {
		return calisan;
	}

	public void setCalisan(Calisan calisan) {
		this.calisan = calisan;
	}

	public double getSpendAmount() {
		return spendAmount;
	}

	public void setSpendAmount(double spendAmount) {
		this.spendAmount = spendAmount;
	}

	public enum MoneyType {
		TL, USD, EURO
	}
}
