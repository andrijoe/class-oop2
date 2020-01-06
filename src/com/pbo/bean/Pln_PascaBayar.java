package com.pbo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mleasingpln")

public class Pln_PascaBayar {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="No_Meter")
	private Double No_Meter;
	
	@Column(name="Nominal_Tagihan")
	private Double Nominal_Tagihan;
	
	@Column(name="Tgl_Pembayaran")
	private Double Tgl_Pembayaran;
	
	@Column(name="No_Bukti_Transaksi")
	private Double No_Bukti_Transaksi;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getNo_Meter() {
		return No_Meter;
	}
	public void setNo_Meter(Double no_Meter) {
		No_Meter = no_Meter;
	}
	public Double getNominal_Tagihan() {
		return Nominal_Tagihan;
	}
	public void setNominal_Tagihan(Double nominal_Tagihan) {
		Nominal_Tagihan = nominal_Tagihan;
	}
	public Double getTgl_Pembayaran() {
		return Tgl_Pembayaran;
	}
	public void setTgl_Pembayaran(Double tgl_Pembayaran) {
		Tgl_Pembayaran = tgl_Pembayaran;
	}
	public Double getNo_Bukti_Transaksi() {
		return No_Bukti_Transaksi;
	}
	public void setNo_Bukti_Transaksi(Double no_Bukti_Transaksi) {
		No_Bukti_Transaksi = no_Bukti_Transaksi;
	}
	
	
}
