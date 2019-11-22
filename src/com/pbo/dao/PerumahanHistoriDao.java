package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PerumahanHistori;
import com.pbo.util.DbConnection;

public class PerumahanHistoriDao {
	public List<PerumahanHistori> getListPerumahanHistori() {
		List<PerumahanHistori> listPerumahanHistori = new ArrayList<PerumahanHistori>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PerumahanHistori perumahanhistori = new PerumahanHistori();
				perumahanhistori.setId(rs.getLong("id"));
				perumahanhistori.setCicilan(rs.getString("cicilan"));
				perumahanhistori.setNominal(rs.getString("nominal"));
				perumahanhistori.setDenda(rs.getString("denda"));
				perumahanhistori.setTglcicil(rs.getString("tglcicil"));
				listPerumahanHistori.add(perumahanhistori);
			}
			
			if (listPerumahanHistori.size() == 0) {
				listPerumahanHistori = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPerumahanHistori;
	}
	
	public PerumahanHistori getBankById(String id) {
		PerumahanHistori perumahanhistori = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				perumahanhistori = new PerumahanHistori();
				perumahanhistori.setId(rs.getLong("id"));
				perumahanhistori.setCicilan(rs.getString("cicilan"));
				perumahanhistori.setNominal(rs.getString("nominal"));
				perumahanhistori.setDenda(rs.getString("denda"));
				perumahanhistori.setTglcicil(rs.getString("tglcicil"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return perumahanhistori;
	}
	
	public boolean deleteBank(String id) {
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
	
	public boolean saveUpdateBank(PerumahanHistori perumahanhistori) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (perumahanhistori.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, perumahanhistori.getCicilan());
			ps.setString(2, perumahanhistori.getNominal());
			ps.setString(3, perumahanhistori.getDenda());
			ps.setString(3, perumahanhistori.getTglcicil());
			ps.setString(4, "0");
			
			if (perumahanhistori.getId() != null) {
				ps.setLong(5, perumahanhistori.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
