package com.pbo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="Ticketing")
public class Ticketing {
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	public Long Id;
	
	@Column(name="Id_Kendaraan")
	public Long Id_Kendaraan;
	
	@Column(name="Id_Costumer_Transportasi")
	public Long Id_Costumer_Transportasi;
	
	@Column(name="Nominal")
	public Long Nominal;
	
	@Column(name="Tujuan")
	public String Tujuan;
	
	@Column(name="No_Ticket")
	public Long No_Ticket;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getId_Kendaraan() {
		return Id_Kendaraan;
	}
	public void setId_Kendaraan(Long id_Kendaraan) {
		Id_Kendaraan = id_Kendaraan;
	}
	public Long getId_Costumer_Transportasi() {
		return Id_Costumer_Transportasi;
	}
	public void setId_Costumer_Transportasi(Long id_Costumer_Transportasi) {
		Id_Costumer_Transportasi = id_Costumer_Transportasi;
	}
	public Long getNominal() {
		return Nominal;
	}
	public void setNominal(Long nominal) {
		Nominal = nominal;
	}
	public String getTujuan() {
		return Tujuan;
	}
	public void setTujuan(String tujuan) {
		Tujuan = tujuan;
	}
	public Long getNo_Ticket() {
		return No_Ticket;
	}
	public void setNo_Ticket(Long no_Ticket) {
		No_Ticket = no_Ticket;
	}
}
