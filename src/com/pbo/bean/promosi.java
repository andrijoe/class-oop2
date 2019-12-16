package com.pbo.bean;

@Entity
@Table(name="promosi")
public class promosi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long Id;
	
	@Column(name="id_kendaraan")
	public Long Id_kendaraan;
	
	@Column(name="diskon")
	public String Diskon;
	
	@Column(name="platform")
	public String Platform;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getId_kendaraan() {
		return Id_kendaraan;
	}
	public void setId_kendaraan(Long id_kendaraan) {
		Id_kendaraan = id_kendaraan;
	}
	public String getDiskon() {
		return Diskon;
	}
	public void setDiskon(String diskon) {
		Diskon = diskon;
	}
	public String getPlatform() {
		return Platform;
	}
	public void setPlatform(String platform) {
		Platform = platform;
	}
}
