package com.pbo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="History_transaksi_transportasi")
public class History_transaksi_transportasi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="notiket")
	public Long notiket;
	
	@Column(name="tgltransaksi")
	public Long tgltransaksi;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id; 
	}
	public Long getNotiket() {
		return notiket;
	}
	public void setNotiket(Long notiket) {
		this.notiket = notiket;
	}
	public Long getTgltransaksi() {
		return tgltransaksi;
	}
	public void setTgltransaksi(Long tgltransaksi) {
		this.tgltransaksi = tgltransaksi;
	}
}
