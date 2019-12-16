package com.pbo.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="leasing")
public class Leasing {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;
	
	@Column(name="nama_leasing")
	private Double namaLeasing;
	
	@Column(name="alamat")
	private Double Alamat;
	
	@Column(name="tipe")
	private Double Tipe;
	
	@Column(name="phone")
	private Double Phone;
	
	@Column(name="email")
	private Double Email;
	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	public Double getNama_leasing() {
		return namaLeasing;
	}

	public void setNama_leasing(Double nama_leasing) {
		this.namaLeasing = nama_leasing;
	}

	public Double getAlamat() {
		return Alamat;
	}

	public void setAlamat(Double alamat) {
		this.Alamat = alamat;
	}

	public Double getTipe() {
		return Tipe;
	}

	public void setTipe(Double tipe) {
		this.Tipe = tipe;
	}

	public Double getPhone() {
		return Phone;
	}

	public void setPhone(Double phone) {
		this.Phone = phone;
	}

	public Double getEmail() {
		return Email;
	}

	public void setEmail(Double email) {
		this.Email = email;
	}

}
