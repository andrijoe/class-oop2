package com.pbo.bean;

public class KtaTransaction {
public long id;
public long id_Bank; 
public long id_cust;
public long min_money;
public long tenor; 
public String tempo;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getId_Bank() {
	return id_Bank;
}
public void setId_Bank(long id_Bank) {
	this.id_Bank = id_Bank;
}
public long getId_cust() {
	return id_cust;
}
public void setId_cust(long id_cust) {
	this.id_cust = id_cust;
}
public long getMin_money() {
	return min_money;
}
public void setMin_money(long min_money) {
	this.min_money = min_money;
}
public long getTenor() {
	return tenor;
}
public void setTenor(long tenor) {
	this.tenor = tenor;
}
public String getTempo() {
	return tempo;
}
public void setTempo(String tempo) {
	this.tempo = tempo;
}

}
