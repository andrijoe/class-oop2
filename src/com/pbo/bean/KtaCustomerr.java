package com.pbo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mktacustomerr")
public class KtaCustomerr {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cust")
	private Long Id_cust;
	
	@Column(name="name")
	private String Name; 
	
	@Column(name="address")
	private String Address;
	
	@Column(name="jobs")
	private String Jobs;
	
	@Column(name="ktp_number")
	private String KTP_Number;
	
	@Column(name="dateofbirth")
	private String DateOfBirth;
	
	@Column(name="gender")
	private String Gender;
	public Long getId_cust() {
		return Id_cust;
	}
	public void setId_cust(Long id_cust) {
		Id_cust = id_cust;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getJobs() {
		return Jobs;
	}
	public void setJobs(String jobs) {
		Jobs = jobs;
	}
	public String getKTP_Number() {
		return KTP_Number;
	}
	public void setKTP_Number(String kTP_Number) {
		KTP_Number = kTP_Number;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	
}