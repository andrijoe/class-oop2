package com.pbo.bean;

public class PlnNontaglis {

	 private Long id;
	    private String transaksi_type;
	    private String biaya;
	    private String tgl_pembelian;
	    private String bukti_transaksi;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTransaksi_type() {
			return transaksi_type;
		}
		public void setTransaksi_type(String transaksi_type) {
			this.transaksi_type = transaksi_type;
		}
		public String getBiaya() {
			return biaya;
		}
		public void setBiaya(String biaya) {
			this.biaya = biaya;
		}
		public String getTgl_pembelian() {
			return tgl_pembelian;
		}
		public void setTgl_pembelian(String tgl_pembelian) {
			this.tgl_pembelian = tgl_pembelian;
		}
		public String getBukti_transaksi() {
			return bukti_transaksi;
		}
		public void setBukti_transaksi(String bukti_transaksi) {
			this.bukti_transaksi = bukti_transaksi;
		}
}
