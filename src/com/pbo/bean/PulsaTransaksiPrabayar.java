package com.pbo.bean;

public class PulsaTransaksiPrabayar {
	private Long id;
	private String No_Tlp;
	private String Nominal;
	private String Valid;
	private String Tgl_Trx;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo_Tlp() {
		return No_Tlp;
	}
	public void setNo_Tlp(String no_Tlp) {
		No_Tlp = no_Tlp;
	}
	public String getNominal() {
		return Nominal;
	}
	public void setNominal(String nominal) {
		Nominal = nominal;
	}
	public String getValid() {
		return Valid;
	}
	public void setValid(String valid) {
		Valid = valid;
	}
	public String getTgl_Trx() {
		return Tgl_Trx;
	}
	public void setTgl_Trx(String tgl_Trx) {
		Tgl_Trx = tgl_Trx;
	}
}
