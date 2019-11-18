package com.pbo.bean;

public class Bank {
	private Long id_bank;
	private String name;
	private String address;
	private String isDelete;
	public Long getId() {
		return id_bank;
	}
	public void setId( Long id_bank) {
		this.id_bank = id_bank;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
