package com.stok.ramazan.pojo;

public class Calisan extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeType employeeType;
	private String nameSurname;
	private String adress;
	private int age;
	private double payment;

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public enum EmployeeType {
		PATRON, ISCİ, ARAC
	}
}
