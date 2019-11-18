package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.Bank;
import com.pbo.bean.PerumahanHistori;
import com.pbo.util.DbConnection;

public class PerumahanHistoriDao {
	public List<PerumahanHistori> getListPerumahanHistori() {
		List<PerumahanHistori> listPerumahanHistori = new ArrayList<PerumahanHistori>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mPerumahanHistori";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PerumahanHistori PerumahanHistori = new PerumahanHistori();
				PerumahanHistori.HistoriCicilan(rs.getString("HistoriCicilan"));
				PerumahanHistori.setId(rs.getLong("id"));
				PerumahanHistori.setIsDelete(rs.getString("isdelete"));
				bank.setName(rs.getString("name"));
				listBank.add(bank);
			}
			
			if (listBank.size() == 0) {
				listBank = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	

}
