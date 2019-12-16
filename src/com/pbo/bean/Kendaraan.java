package com.pbo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="Kendaraan") 
public class Kendaraan {
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long Id;
	
	@Column(name="nama")
	public String nama;
	
	@Column(name="tipe")
	public String Tipe;
	
	@Column(name="vendor")
	public String Vendor;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getTipe() {
		return Tipe;
	}
	public void setTipe(String tipe) {
		Tipe = tipe;
	}
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		Vendor = vendor;
	}

}
