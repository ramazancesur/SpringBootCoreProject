package com.stok.ramazan.pojo;

import com.stok.ramazan.helper.EnumUtil;

import java.time.LocalDate;
import java.util.Date;

public class Calisan extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnumUtil.EmployeeType employeeType;
	private String nameSurname;
	private String adress;
	private Date birthdate;
	private double payment;
	private String phoneNumber;

	public EnumUtil.EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EnumUtil.EmployeeType employeeType) {
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
