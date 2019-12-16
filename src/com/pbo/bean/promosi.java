package com.pbo.bean;

public class promosi {

	public Long Id;
	public Long Id_kendaraan;
	public String Diskon;
	public String Platform;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getId_kendaraan() {
		return Id_kendaraan;
	}
	public void setId_kendaraan(Long id_kendaraan) {
		Id_kendaraan = id_kendaraan;
	}
	public String getDiskon() {
		return Diskon;
	}
	public void setDiskon(String diskon) {
		Diskon = diskon;
	}
	public String getPlatform() {
		return Platform;
	}
	public void setPlatform(String platform) {
		Platform = platform;
	}
}
