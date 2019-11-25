package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.KtaBankk;
import com.pbo.bean.KtaCustomerr;
import com.pbo.util.DbConnection;

public class KtaCustomerrDao {
	public List<KtaCustomerr> getListKtaCustomerr() {
		List<KtaCustomerr> listBank = new ArrayList<KtaCustomerr>();
		try { 
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				KtaCustomerr KtaCustomerr = new KtaCustomerr();
				KtaCustomerr.setId_cust(rs.getLong("Id_cust"));
				KtaCustomerr.setName(rs.getString("Name"));
				KtaCustomerr.setAddress(rs.getString("Address"));
				KtaCustomerr.setJobs(rs.getString("Jobs"));
				KtaCustomerr.setKTP_Number(rs.getString("KTP_Number"));
				KtaCustomerr.setDateOfBirth(rs.getString("DateOfBirth"));
				KtaCustomerr.setGender(rs.getString("Gender"));

				listBank.add(KtaCustomerr);
			}
			
			if (listBank.size() == 0) {
				listBank = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listBank;
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
	
	public boolean saveUpdateBank(KtaCustomerr Ktacust) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (Ktacust.getId_cust() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, Ktacust.getName());
			ps.setString(2, Ktacust.getAddress());
			ps.setString(3, Ktacust.getJobs());
			ps.setString(4, Ktacust.getKTP_Number());
			ps.setString(5, Ktacust.getDateOfBirth());
			ps.setString(6, Ktacust.getGender());
			ps.setString(7, "0");
			
			if (Ktacust.getId_cust() != null) {
				ps.setLong(4, Ktacust.getId_cust());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}


