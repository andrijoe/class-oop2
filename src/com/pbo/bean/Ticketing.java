package com.pbo.bean;

public class Ticketing {
	 private Long id;
	    private String id_kendaraan;
	    private String id_custumer;
	    private String trasportasi;
	    private String nominal;
	    private String tujuan;
	    private String no_tiket;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getId_kendaraan() {
			return id_kendaraan;
		}
		public void setId_kendaraan(String id_kendaraan) {
			this.id_kendaraan = id_kendaraan;
		}
		public String getId_custumer() {
			return id_custumer;
		}
		public void setId_custumer(String id_custumer) {
			this.id_custumer = id_custumer;
		}
		public String getTrasportasi() {
			return trasportasi;
		}
		public void setTrasportasi(String trasportasi) {
			this.trasportasi = trasportasi;
		}
		public String getNominal() {
			return nominal;
		}
		public void setNominal(String nominal) {
			this.nominal = nominal;
		}
		public String getTujuan() {
			return tujuan;
		}
		public void setTujuan(String tujuan) {
			this.tujuan = tujuan;
		}
		public String getNo_tiket() {
			return no_tiket;
		}
		public void setNo_tiket(String no_tiket) {
			this.no_tiket = no_tiket;
		}
	    
}