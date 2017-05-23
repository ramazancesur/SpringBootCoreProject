package com.stok.ramazan.pojo;

public class UrunDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productName;
	private double price;
	//Geliş Tarihi
	private String commingDate;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCommingDate() {
		return commingDate;
	}

	public void setCommingDate(String commingDate) {
		this.commingDate = commingDate;
	}
}