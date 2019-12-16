package com.pbo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Leasingcicilan")
public class LeasingCicilan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="no_trx")
	private String No_Trx;
	
	@Column(name="no_kontrak")
	private String No_kontrak;
	
	@Column(name="nominal")
	private String Nominal;
	
	@Column(name="denda")
	private String Denda;
	
	@Column(name="tgl_cicilan")
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