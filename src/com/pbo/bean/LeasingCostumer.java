package com.pbo.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mleasingcostumer")

public class LeasingCostumer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="no_ktp")
	private Double no_KTP;
	
	@Column(name="nama")
	private Double Nama;
	
	@Column(name="jenkel")
	private Double Jenkel;
	
	@Column(name="ttl")
	private Double TTL;
	
	@Column(name="alamat")
	private Double Alamat;
	
	@Column(name="phone")
	private Double Phone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getNo_KTP() {
		return no_KTP;
	}
	public void setNo_KTP(Double no_ktp) {
		no_KTP = no_ktp;
	}
	public Double getNama() {
		return Nama;
	}
	public void setNama(Double nama) {
		Nama = nama;
	}
	public Double getJenkel() {
		return Jenkel;
	}
	public void setJenkel(Double jenkel) {
		Jenkel = jenkel;
	}
	public Double getTTL() {
		return TTL;
	}
	public void setTTL(Double tTL) {
		TTL = tTL;
	}
	public Double getAlamat() {
		return Alamat;
	}
	public void setAlamat(Double alamat) {
		Alamat = alamat;
	}
	public Double getPhone() {
		return Phone;
	}
	public void setPhone(Double phone) {
		Phone = phone;
	}
	
}
