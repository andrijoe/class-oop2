package com.pbo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mcriteria")
public class Criteria extends AuditBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcriteria")
	private Long idCriteria;
	
	@Column(name="rapor")
	private Double rapor;
	
	@Column(name="prilaku")
	private Double prilaku;
	
	@Column(name="tahfidquran")
	private Double tahfidQuran;
	
	@Column(name="bhsasing")
	private Double bhsAsing;
	
	@Column(name="muhadarah")
	private Double muhadarah;
	
	@Column(name="disiplinshalat")
	private Double disiplinShalat;
	
	@Column(name="mahfudhot")
	private Double mahfudhot;
	
	@Column(name="pai")
	private Double pai;
	
	@Column(name="doa")
	private Double doa;

	public Long getIdCriteria() {
		return idCriteria;
	}

	public void setIdCriteria(Long idCriteria) {
		this.idCriteria = idCriteria;
	}

	public Double getRapor() {
		return rapor;
	}

	public void setRapor(Double rapor) {
		this.rapor = rapor;
	}

	public Double getPrilaku() {
		return prilaku;
	}

	public void setPrilaku(Double prilaku) {
		this.prilaku = prilaku;
	}

	public Double getTahfidQuran() {
		return tahfidQuran;
	}

	public void setTahfidQuran(Double tahfidQuran) {
		this.tahfidQuran = tahfidQuran;
	}

	public Double getBhsAsing() {
		return bhsAsing;
	}

	public void setBhsAsing(Double bhsAsing) {
		this.bhsAsing = bhsAsing;
	}

	public Double getMuhadarah() {
		return muhadarah;
	}

	public void setMuhadarah(Double muhadarah) {
		this.muhadarah = muhadarah;
	}

	public Double getDisiplinShalat() {
		return disiplinShalat;
	}

	public void setDisiplinShalat(Double disiplinShalat) {
		this.disiplinShalat = disiplinShalat;
	}

	public Double getMahfudhot() {
		return mahfudhot;
	}

	public void setMahfudhot(Double mahfudhot) {
		this.mahfudhot = mahfudhot;
	}

	public Double getPai() {
		return pai;
	}

	public void setPai(Double pai) {
		this.pai = pai;
	}

	public Double getDoa() {
		return doa;
	}

	public void setDoa(Double doa) {
		this.doa = doa;
	}
	
}
