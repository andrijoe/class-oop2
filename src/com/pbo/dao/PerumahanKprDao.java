package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PerumahanKpr;
import com.pbo.util.DbConnection;

public class PerumahanKprDao {
	public List<PerumahanKpr> getListPerumahanKpr() {
		List<PerumahanKpr> listPerumahanKpr = new ArrayList<PerumahanKpr>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PerumahanKpr perumahankpr = new PerumahanKpr();
				perumahankpr.setId(rs.getLong("id"));
				perumahankpr.setIdhome(rs.getString("idhome"));
				perumahankpr.setHarga(rs.getString("harga"));
				perumahankpr.setBunga(rs.getString("bunga"));
				perumahankpr.setTenor(rs.getString("tenor"));
				perumahankpr.setTotalHarga(rs.getString("totalharga"));
				listPerumahanKpr.add(perumahankpr);
			}
			
			if (listPerumahanKpr.size() == 0) {
				listPerumahanKpr = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPerumahanKpr;
	}
	
	public PerumahanKpr getBankById(String id) {
		PerumahanKpr perumahankpr = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				perumahankpr = new PerumahanKpr();
				perumahankpr.setId(rs.getLong("id"));
				perumahankpr.setIdhome(rs.getString("idhome"));
				perumahankpr.setHarga(rs.getString("harga"));
				perumahankpr.setBunga(rs.getString("bunga"));
				perumahankpr.setTenor(rs.getString("tenor"));
				perumahankpr.setTotalHarga(rs.getString("totalharga"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return perumahankpr;
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
	
	public boolean saveUpdateBank(PerumahanKpr perumahankpr) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (perumahankpr.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, perumahankpr.getIdhome());
			ps.setString(2, perumahankpr.getHarga());
			ps.setString(3, perumahankpr.getBunga());
			ps.setString(3, perumahankpr.getTenor());
			ps.setString(3, perumahankpr.getTotalHarga());
			ps.setString(4, "0");
			
			if (perumahankpr.getId() != null) {
				ps.setLong(5, perumahankpr.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
