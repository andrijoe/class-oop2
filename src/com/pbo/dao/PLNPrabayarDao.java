package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PLNPrabayar;
import com.pbo.util.DbConnection;


public class PLNPrabayarDao {
	public List<PLNPrabayar> getPLNPrabayarDaos() {
		List<PLNPrabayar> listPLNPrabayar = new ArrayList<PLNPrabayar>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PLNPrabayar prabayar = new PLNPrabayar();
				prabayar.setId(rs.getLong("id"));
				prabayar.setNolistrik(rs.getString("no listrik"));
				prabayar.setNominal(rs.getString("nominal"));
				prabayar.setTglpembelian(rs.getString("tgl pembelian"));
				prabayar.setNotoken(rs.getString("no token"));
				listPLNPrabayar.add(prabayar);
			}
			
			if (listPLNPrabayar.size() == 0) {
				listPLNPrabayar = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPLNPrabayar;
	}
	public PLNPrabayarDao getPLNPrabayarById(String id) {
		PLNPrabayarDao prabayar = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				prabayar.setId(rs.getLong("id"));
				prabayar.setNolistrik(rs.getString("no listrik"));
				prabayar.setNominal(rs.getString("nominal"));
				prabayar.setTglpembelian(rs.getString("tgl pembelian"));
				prabayar.setNotoken(rs.getString("no token"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prabayar;
	}
	
	public boolean deletePLNPrabayar(String id) {
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
	
	public boolean saveUpdatePLNPrabayar(PLNPrabayar prabayar) {
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
			
			if (prabayar.getId() != null) {
				ps.setLong(4, prabayar.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}

