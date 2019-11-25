package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.LeasingCostumer;
import com.pbo.util.DbConnection;

public class LeasingCostumerDao {
	public List<LeasingCostumer> getLeasingCostumerDaos() {
		List<LeasingCostumer> listLeasingCostumer = new ArrayList<LeasingCostumer>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LeasingCostumer Costumer = new LeasingCostumer();
				Costumer.setNo_KTP(rs.getString("no_ktp"));
				Costumer.setId(rs.getLong("id"));
				Costumer.setNama(rs.getString("nama"));
				Costumer.setJenkel(rs.getString("jenkel"));
				Costumer.setTTL(rs.getString("ttl"));
				Costumer.setAlamat(rs.getString("alamat"));
				Costumer.setPhone(rs.getString("Phone"));
				listLeasingCostumer.add(Costumer);
			}
			
			if (listLeasingCostumer.size() == 0) {
				listLeasingCostumer = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listLeasingCostumer;
	}
	
	public LeasingCostumerDao getLeasingCostumerById(String id) {
		LeasingCostumerDao costumer = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LeasingCostumer Costumer = new LeasingCostumer();
				Costumer.setNo_KTP(rs.getString("no_ktp"));
				Costumer.setId(rs.getLong("id"));
				Costumer.setNama(rs.getString("nama"));
				Costumer.setJenkel(rs.getString("jenkel"));
				Costumer.setTTL(rs.getString("ttl"));
				Costumer.setAlamat(rs.getString("alamat"));
				Costumer.setPhone(rs.getString("Phone"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return costumer;
	}
	
	public boolean deleteLeasingTransaction(String id) {
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
	
	public boolean saveUpdateLeasingCostumer(LeasingCostumer Costumer) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (Costumer.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			/*
			 * ps.setString(1, Costumer.getName()); ps.setString(2,
			 * Costumer.getAddress());
			 */
			ps.setString(3, "0");
			
			if (Costumer.getId() != null) {
				ps.setLong(4, Costumer.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
