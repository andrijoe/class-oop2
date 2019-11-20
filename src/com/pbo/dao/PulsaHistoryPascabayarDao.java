package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PulsaHistoryPascabayar;
import com.pbo.util.DbConnection;

public class PulsaHistoryPascabayarDao {
	public List<PulsaHistoryPascabayar> getListPulsaHistoryPascabayar() {
		List<PulsaHistoryPascabayar> listPulsaHistoryPascabayar = new ArrayList<PulsaHistoryPascabayar>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PulsaHistoryPascabayar pulsahistorypascabayar = new PulsaHistoryPascabayar();
				pulsahistorypascabayar.setIdtrxpostpaid(rs.getString("Idtrxpostpaid"));
				pulsahistorypascabayar.setId(rs.getLong("id"));
				pulsahistorypascabayar.setPaymentdate(rs.getString("paymentdate"));
				listPulsaHistoryPascabayar.add(pulsahistorypascabayar);
			}
			
			if (listPulsaHistoryPascabayar.size() == 0) {
				listPulsaHistoryPascabayar = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPulsaHistoryPascabayar;
	}
	
	public PulsaHistoryPascabayar getBankById(String id) {
		PulsaHistoryPascabayar pulsahistorypascabayar= null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pulsahistorypascabayar = new PulsaHistoryPascabayar();
				pulsahistorypascabayar.setIdtrxpostpaid(rs.getString("Idtrxpostpaid"));
				pulsahistorypascabayar.setId(rs.getLong("id"));
				pulsahistorypascabayar.setPaymentdate(rs.getString("paymentdate"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pulsahistorypascabayar;
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
	
	public boolean saveUpdateBank(PulsaHistoryPascabayar pulsahistorypascabayar) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (pulsahistorypascabayar.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pulsahistorypascabayar.getIdtrxpostpaid());
			ps.setString(2, pulsahistorypascabayar.getPaymentdate());
			ps.setString(3, "0");
			
			if (pulsahistorypascabayar.getId() != null) {
				ps.setLong(4, pulsahistorypascabayar.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
