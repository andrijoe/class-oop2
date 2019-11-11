package com.pbo.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditBean {
	@Column(name="updatedaccess")
	private Long updatedAccess;
	
	@Column(name="updateddate")
	private Date updatedDate;
	
	public Long getUpdatedAccess() {
		return updatedAccess;
	}
	public void setUpdatedAccess(Long updatedAccess) {
		this.updatedAccess = updatedAccess;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public String getFormattedDate() {
		String sDate = null;
		if (updatedDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			sDate = sdf.format(updatedDate);
		}
		return sDate;
	}
}
