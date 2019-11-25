package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.Bank;
import com.pbo.bean.KtaBankk;
import com.pbo.util.DbConnection;

public class KtaBankkDao {
	public List<KtaBankk> getListBank() {
		List<KtaBankk> listKtaBankk = new ArrayList<KtaBankk>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				KtaBankk Bankk = new KtaBankk();
				Bankk.setId(rs.getLong("id"));
				Bankk.setBank_name(rs.getString("bank_name"));
				Bankk.setAddress(rs.getString("address"));
				Bankk.setPhone(rs.getString("phone"));
				Bankk.setEmail(rs.getString("email"));
				listKtaBankk.add(Bankk);
			}
			
			if (listKtaBankk.size() == 0) {
				listKtaBankk = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listKtaBankk;
	}
public KtaBankk getBankById(String id) {
		KtaBankk bankk = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				KtaBankk Bankk = new KtaBankk();
				Bankk.setId(rs.getLong("id"));
				Bankk.setBank_name(rs.getString("bank_name"));
				Bankk.setAddress(rs.getString("address"));
				Bankk.setPhone(rs.getString("phone"));
				Bankk.setEmail(rs.getString("email"));

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bankk;
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
	
	public boolean saveUpdateBank(KtaBankk bankk) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (bankk.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bankk.getBank_name());
			ps.setString(2, bankk.getAddress());
			ps.setString(3, bankk.getPhone());
			ps.setString(4, bankk.getEmail());
			ps.setString(5, "0");
			
			if (bankk.getId() != null) {
				ps.setLong(4, bankk.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}


