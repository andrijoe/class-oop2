package com.pbo.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.pbo.bean.PulsaTrxPascabayar;

import com.pbo.util.DbConnection;


public class PulsaTrxPascabayarDao {
	public List<PulsaTrxPascabayar> getListTrxPascabayar() {
		List<PulsaTrxPascabayar> listPulsaTrxPascabayar = new ArrayList<PulsaTrxPascabayar>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PulsaTrxPascabayar pulsatrxpascabayar = new PulsaTrxPascabayar();
				pulsatrxpascabayar.setNoTlp(rs.getString("NoTlp"));
				pulsatrxpascabayar.setId(rs.getLong("id"));
				pulsatrxpascabayar.setBilling(rs.getString("Billing"));
				pulsatrxpascabayar.setFine(rs.getString("Fine"));
				pulsatrxpascabayar.setDateTrx(rs.getString("DateTrx"));
				listPulsaTrxPascabayar.add(pulsatrxpascabayar);
			}
			
			if (listPulsaTrxPascabayar.size() == 0) {
				listPulsaTrxPascabayar = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPulsaTrxPascabayar;
	}
	
	public PulsaTrxPascabayar getBankById(String id) {
		PulsaTrxPascabayar pulsatrxpascabayar = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pulsatrxpascabayar = new PulsaTrxPascabayar();
				pulsatrxpascabayar.setNoTlp(rs.getString("NoTlp"));
				pulsatrxpascabayar.setId(rs.getLong("id"));
				pulsatrxpascabayar.setBilling(rs.getString("Billing"));
				pulsatrxpascabayar.setFine(rs.getString("Fine"));
				pulsatrxpascabayar.setDateTrx(rs.getString("DateTrx"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pulsatrxpascabayar;
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
	
	public boolean saveUpdateBank(PulsaTrxPascabayar pulsatrxpascabayar) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (pulsatrxpascabayar.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pulsatrxpascabayar.getNoTlp());
			ps.setString(2, pulsatrxpascabayar.getBilling());
			ps.setString(3, pulsatrxpascabayar.getFine());
			ps.setString(4, pulsatrxpascabayar.getDateTrx());
			ps.setString(5, "0");
			
			if (pulsatrxpascabayar.getId() != null) {
				ps.setLong(4, pulsatrxpascabayar.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
