package com.pbo.bean;

public class LeasingCicilan {
 
	private Long id;
	private String No_Trx;
	private String No_kontrak;
	private String Nominal;
	private String Denda;
	private String Tgl_Cicilan;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo_Trx() {
		return No_Trx;
	}
	public void setNo_Trx(String no_Trx) {
		No_Trx = no_Trx;
	}
	public String getNo_kontrak() {
		return No_kontrak;
	}
	public void setNo_kontrak(String no_kontrak) {
		No_kontrak = no_kontrak;
	}
	public String getNominal() {
		return Nominal;
	}
	public void setNominal(String nominal) {
		Nominal = nominal;
	}
	public String getDenda() {
		return Denda;
	}
	public void setDenda(String denda) {
		Denda = denda;
	}
	public String getTgl_Cicilan() {
		return Tgl_Cicilan;
	}
	public void setTgl_Cicilan(String tgl_Cicilan) {
		Tgl_Cicilan = tgl_Cicilan;
	}
	
}