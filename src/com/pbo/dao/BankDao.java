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
}
