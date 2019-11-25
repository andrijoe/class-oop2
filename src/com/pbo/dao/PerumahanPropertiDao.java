package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PerumahanProperti;
import com.pbo.util.DbConnection;

public class PerumahanPropertiDao {
	public List<PerumahanProperti> getListPerumahanProperti() {
		List<PerumahanProperti> listPerumahanProperti = new ArrayList<PerumahanProperti>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PerumahanProperti perumahanproperti = new PerumahanProperti();
				perumahanproperti.setId(rs.getLong("id"));
				perumahanproperti.setType(rs.getString("type"));
				perumahanproperti.setAddress(rs.getString("address"));
				perumahanproperti.setVendor(rs.getString("vendor"));
				listPerumahanProperti.add(perumahanproperti);
			}
			
			if (listPerumahanProperti.size() == 0) {
				listPerumahanProperti = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPerumahanProperti;
	}
	
	public PerumahanProperti getBankById(String id) {
		PerumahanProperti perumahanproperti = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				perumahanproperti = new PerumahanProperti();
				perumahanproperti.setId(rs.getLong("id"));
				perumahanproperti.setType(rs.getString("type"));
				perumahanproperti.setAddress(rs.getString("address"));
				perumahanproperti.setVendor(rs.getString("vendor"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return perumahanproperti;
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
	
	public boolean saveUpdateBank(PerumahanProperti perumahanproperti) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (perumahanproperti.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, perumahanproperti.getType());
			ps.setString(2, perumahanproperti.getAddress());
			ps.setString(3, perumahanproperti.getVendor());
			ps.setString(4, "0");
			
			if (perumahanproperti.getId() != null) {
				ps.setLong(5, perumahanproperti.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}

