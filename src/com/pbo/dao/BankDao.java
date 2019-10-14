package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.Bank;
import com.pbo.util.DbConnection;

public class BankDao {
	public List<Bank> getListBank() {
		List<Bank> listBank = new ArrayList<Bank>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Bank bank = new Bank();
				bank.setAddress(rs.getString("address"));
				bank.setId(rs.getLong("id"));
				bank.setIsDelete(rs.getString("isdelete"));
				bank.setName(rs.getString("name"));
				listBank.add(bank);
			}
			
			if (listBank.size() == 0) {
				listBank = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listBank;
	}
	
	public Bank getBankById(String id) {
		Bank bank = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bank = new Bank();
				bank.setAddress(rs.getString("address"));
				bank.setId(rs.getLong("id"));
				bank.setIsDelete(rs.getString("isdelete"));
				bank.setName(rs.getString("name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bank;
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
	
	public boolean saveUpdateBank(Bank bank) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (bank.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bank.getName());
			ps.setString(2, bank.getAddress());
			ps.setString(3, "0");
			
			if (bank.getId() != null) {
				ps.setLong(4, bank.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
