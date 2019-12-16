package com.pbo.bean;

@Entity
@Table(name="bpjstrxhistrory")
public class BpjsTrxHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_trx")
	public long id_trx;
	
	@Column(name="tgl_trx")
	public String tgl_trx;
	
	@Column(name="isvalid")
	public boolean isvalid;
	
	
	
	public long getId_trx() {
		return id_trx;
	}
	public void setId_trx(long id_trx) {
		this.id_trx = id_trx;
	}
	public String getTgl_trx() {
		return tgl_trx;
	}
	public void setTgl_trx(String tgl_trx) {
		this.tgl_trx = tgl_trx;
	}
	public boolean isIsvalid() {
		return isvalid;
	}
	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}

}
