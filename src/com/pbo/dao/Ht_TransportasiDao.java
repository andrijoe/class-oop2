package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.Ticketing;
import com.pbo.util.DbConnection;

public class Ht_TransportasiDao {
	private Ht_Transportasi Ht_Transportasi;

	public List<Ht_Transportasi> getHt_TransportasiDaos() {
		List<Ht_Transportasi> listHt_Transportasi = new ArrayList<Ht_Transportasi>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ht_Transportasi Ht_Transportasi = new Ht_Transportasi();
				Ht_Transportasi.setId(rs.getLong("id"));
				Ht_Transportasi.setNo_tiket(rs.getString("no_tiket"));
				Ht_Transportasi.setTgl_transaksi(rs.getString("tgl_transaksi"));
				listHt_Transportasi.add(Ht_Transportasi);
			}
			
			if (listHt_Transportasi.size() == 0) {
				listHt_Transportasi = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listHt_Transportasi;
	}
	
	public Ht_Transportasi getHt_TransportasiById(String id) {
		Ht_Transportasi = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ht_Transportasi  = new Ht_Transportasi();
				Ht_Transportasi.setId(rs.getLong("id"));
				Ht_Transportasi.setNo_tiket(rs.getString("no_tiket"));
				Ht_Transportasi.setId_customer(rs.getString("id_customer"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Ht_Transportasi;
	}
	
	public boolean deleteHt_Transportasi(String id) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "update mbank set isdelete = ? "
					+ "where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "1");
			ps.setInt(2, Integer.parseInt(id));
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean saveUpdateHt_Transportasi(Ht_Transportasi Ht_Transportasi) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (Ht_Transportasi.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			/*
			 * ps.setString(1, .getName()); ps.setString(2,
			 * .getAddress());
			 */
			ps.setString(3, "0");
			
			if (Ht_Transportasi.getId() != null) {
				ps.setLong(4, Ht_Transportasi.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
