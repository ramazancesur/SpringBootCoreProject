package com.stok.ramazan.pojo;

public class MusteriDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private String phoneNumber;
	private String phoneNumber2;
	private String identityNumber;
	private String Adress;
	//Bakiye
	private Double duesAmmount;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public Double getDuesAmmount() {
		return duesAmmount;
	}

	public void setDuesAmmount(Double duesAmmount) {
		this.duesAmmount = duesAmmount;
	}
}
