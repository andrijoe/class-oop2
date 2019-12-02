package com.pbo.bean;

public class Kendaraan {
	public Long Id;
	public String nama;
	public String Tipe;
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
