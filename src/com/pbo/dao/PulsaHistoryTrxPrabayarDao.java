package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PulsaHistoryTrxPrabayar;
import com.pbo.util.DbConnection;

public class PulsaHistoryTrxPrabayarDao {
	public List<PulsaHistoryTrxPrabayar> getListPulsaHistoryTrxPrabayar() {
		List<PulsaHistoryTrxPrabayar> listPulsaHistoryTrxPrabayar = new ArrayList<PulsaHistoryTrxPrabayar>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PulsaHistoryTrxPrabayar pulsahistorytrxprabayar = new PulsaHistoryTrxPrabayar();
				pulsahistorytrxprabayar.setIdTrxPrabayar(rs.getString("IdTrxPrabayar"));
				pulsahistorytrxprabayar.setId(rs.getLong("id"));
				pulsahistorytrxprabayar.setTgl_Pembelian(rs.getString("Tgl_Pembelian"));
				listPulsaHistoryTrxPrabayar.add(pulsahistorytrxprabayar);
			}
			
			if (listPulsaHistoryTrxPrabayar.size() == 0) {
				listPulsaHistoryTrxPrabayar = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPulsaHistoryTrxPrabayar;
	}
	
	public PulsaHistoryTrxPrabayar getBankById(String id) {
		PulsaHistoryTrxPrabayar pulsahistorytrxprabayar= null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pulsahistorytrxprabayar = new PulsaHistoryTrxPrabayar();
				pulsahistorytrxprabayar.setIdTrxPrabayar(rs.getString("IdTrxPrabayar"));
				pulsahistorytrxprabayar.setId(rs.getLong("id"));
				pulsahistorytrxprabayar.setTgl_Pembelian(rs.getString("Tgl_Pembelian"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pulsahistorytrxprabayar;
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
	
	public boolean saveUpdateBank(PulsaHistoryTrxPrabayar pulsahistorytrxprabayar) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (pulsahistorytrxprabayar.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pulsahistorytrxprabayar.getIdTrxPrabayar());
			ps.setString(2, pulsahistorytrxprabayar.getTgl_Pembelian());
			ps.setString(3, "0");
			
			if (pulsahistorytrxprabayar.getId() != null) {
				ps.setLong(4, pulsahistorytrxprabayar.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
