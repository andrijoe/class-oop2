package com.pbo.bean;

public class PerumahanKpr {
	private Long id;
	private String idhome;
	private String harga;
	private String bunga;
	private String tenor;
	private String totalHarga;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdhome() {
		return idhome;
	}
	public void setIdhome(String idhome) {
		this.idhome = idhome;
	}
	public String getHarga() {
		return harga;
	}
	public void setHarga(String harga) {
		this.harga = harga;
	}
	public String getBunga() {
		return bunga;
	}
	public void setBunga(String bunga) {
		this.bunga = bunga;
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public String getTotalHarga() {
		return totalHarga;
	}
	public void setTotalHarga(String totalHarga) {
		this.totalHarga = totalHarga;
	}
	
}
