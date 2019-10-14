package com.pbo.bean;

import java.util.Date;

public class Trx {
	private Long id;
	private Nasabah nasabah;
	private Bank bankFrom;
	private Bank bankTo;
	private Integer nominal;
	private Date dateTrx;
	private String status;
	private String trxType;
	private String noTrx;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Nasabah getNasabah() {
		return nasabah;
	}
	public void setNasabah(Nasabah nasabah) {
		this.nasabah = nasabah;
	}
	public Bank getBankFrom() {
		return bankFrom;
	}
	public void setBankFrom(Bank bankFrom) {
		this.bankFrom = bankFrom;
	}
	public Bank getBankTo() {
		return bankTo;
	}
	public void setBankTo(Bank bankTo) {
		this.bankTo = bankTo;
	}
	public Integer getNominal() {
		return nominal;
	}
	public void setNominal(Integer nominal) {
		this.nominal = nominal;
	}
	public Date getDateTrx() {
		return dateTrx;
	}
	public void setDateTrx(Date dateTrx) {
		this.dateTrx = dateTrx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrxType() {
		return trxType;
	}
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}
	public String getNoTrx() {
		return noTrx;
	}
	public void setNoTrx(String noTrx) {
		this.noTrx = noTrx;
	}
	
}
