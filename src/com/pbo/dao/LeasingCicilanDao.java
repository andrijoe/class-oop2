package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.LeasingCicilan;
import com.pbo.util.DbConnection;

public class LeasingCicilanDao {
	public List<LeasingCicilan> getListLeasingCicilan() {
		List<LeasingCicilan> listLeasingCicilan = new ArrayList<LeasingCicilan>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mleasing";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LeasingCicilan leasingcicilan = new LeasingCicilan();
				leasingcicilan.setId(rs.getLong("id"));
				leasingcicilan.setNo_Trx(rs.getString("no_trx"));
				leasingcicilan.setNo_kontrak(rs.getString("no_kontrak"));
				leasingcicilan.setNominal(rs.getString("nominal"));
				leasingcicilan.setDenda(rs.getString("denda"));
				leasingcicilan.setTgl_Cicilan(rs.getString("tgl_cicilan"));
				listLeasingCicilan.add(leasingcicilan);
			}
			
			if (listLeasingCicilan.size() == 0) {
				listLeasingCicilan = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listLeasingCicilan;
	}
	
	public LeasingCicilan getLeasingById(String id) {
		LeasingCicilan leasingcicilan = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mleasing where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				leasingcicilan = new LeasingCicilan();
				leasingcicilan.setId(rs.getLong("id"));
				leasingcicilan.setNo_Trx(rs.getString("no_trx"));
				leasingcicilan.setNo_kontrak(rs.getString("no_kontrak"));
				leasingcicilan.setNominal(rs.getString("nominal"));
				leasingcicilan.setDenda(rs.getString("denda"));
				leasingcicilan.setTgl_Cicilan(rs.getString("tgl_cicilan"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return leasingcicilan;
	}
	
	public boolean deleteLeasingCicilan(String id) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "update mleasing set isdelete = ? "
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
	
	public boolean saveUpdateLeasing(LeasingCicilan leasingcicilan) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mleasing "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (leasingcicilan.getId() != null) {
				query = "update mleasing set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, leasingcicilan.getId());
			ps.setString(2, leasingcicilan.getNo_Trx());
			ps.setString(3, leasingcicilan.getNo_kontrak());
			ps.setString(4, leasingcicilan.getNominal());
			ps.setString(5, leasingcicilan.getDenda());
			ps.setString(6, leasingcicilan.getTgl_Cicilan());
			ps.setString(7, "0");
			
			if (leasingcicilan.getId() != null) {
				ps.setLong(4, leasingcicilan.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
