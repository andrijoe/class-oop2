package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PulsaProvider;

import com.pbo.util.DbConnection;

public class PulsaProviderDao {
	public List<PulsaProvider> getListBank() {
		List<PulsaProvider> listPulsaProvider = new ArrayList<PulsaProvider>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PulsaProvider pulsaprovider = new PulsaProvider();
				pulsaprovider.setName(rs.getString("name"));
				pulsaprovider.setId(rs.getLong("id"));
				pulsaprovider.setDescription(rs.getString("description"));
				listPulsaProvider.add(pulsaprovider);
			}
			
			if (listPulsaProvider.size() == 0) {
				listPulsaProvider = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPulsaProvider;
	}
	
	public PulsaProvider getBankById(String id) {
		PulsaProvider pulsaprovider = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pulsaprovider = new PulsaProvider();
				pulsaprovider.setName(rs.getString("name"));
				pulsaprovider.setId(rs.getLong("id"));
				pulsaprovider.setDescription(rs.getString("description"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pulsaprovider;
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
	
	public boolean saveUpdateBank(PulsaProvider pulsaprovider) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (pulsaprovider.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pulsaprovider.getName());
			ps.setString(2, pulsaprovider.getDescription());
			ps.setString(3, "0");
			
			if (pulsaprovider.getId() != null) {
				ps.setLong(4, pulsaprovider.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
