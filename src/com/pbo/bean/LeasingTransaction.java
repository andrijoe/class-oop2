package com.pbo.bean;

public class LeasingTransaction {

	private Long id;
	private String Nokontrak;
	private String idLeasing;
	private String idCustomer;
	private String Nominal;
	private String TglTry;
	private String idVehicle;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNokontrak() {
		return Nokontrak;
	}
	public void setNokontrak(String nokontrak) {
		Nokontrak = nokontrak;
	}
	public String getIdLeasing() {
		return idLeasing;
	}
	public void setIdLeasing(String idLeasing) {
		this.idLeasing = idLeasing;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getNominal() {
		return Nominal;
	}
	public void setNominal(String nominal) {
		Nominal = nominal;
	}
	public String getTglTry() {
		return TglTry;
	}
	public void setTglTry(String tglTry) {
		TglTry = tglTry;
	}
	public String getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
	}
}
