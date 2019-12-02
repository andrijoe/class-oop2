package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.Pln_PascaBayar;
import com.pbo.util.DbConnection;

public class Pln_PascaBayarDao {
	public List<Pln_PascaBayar> getPln_PascaBayarDaos() {
		List<Pln_PascaBayar> listPln_PascaBayar = new ArrayList<Pln_PascaBayar>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pln_PascaBayar PascaBayar = new Pln_PascaBayar();
				PascaBayar.setId(rs.getLong("id"));
				PascaBayar.setNo_Meter(rs.getString("no_meter"));
				PascaBayar.setNominal_Tagihan(rs.getString("nominal_tagihan"));
				PascaBayar.setTgl_Pembayaran(rs.getString("tgl_pembayaran"));
				PascaBayar.setNo_Bukti_Transaksi(rs.getString("no_bukti_transaksi"));
				listPln_PascaBayar.add(PascaBayar);
			}
			
			if (listPln_PascaBayar.size() == 0) {
				listPln_PascaBayar = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPln_PascaBayar;
	}
	
	public Pln_PascaBayarDao getPln_PascaBayarById(String id) {
		Pln_PascaBayarDao PascaBayar = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pln_PascaBayar pascaBayar  = new Pln_PascaBayar();
				pascaBayar.setId(rs.getLong("id"));
				pascaBayar.setNo_Meter(rs.getString("no_meter"));
				pascaBayar.setNominal_Tagihan(rs.getString("nominal_tagihan"));
				pascaBayar.setTgl_Pembayaran(rs.getString("tgl_pembayaran"));
				pascaBayar.setNo_Bukti_Transaksi(rs.getString("no_bukti_transaksi"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return PascaBayar;
	}
	
	public boolean deleteLeasingTransaction(String id) {
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
	
	public boolean saveUpdatePascaBayar(Pln_PascaBayar PascaBayar) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (PascaBayar.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			/*
			 * ps.setString(1, .getName()); ps.setString(2,
			 * .getAddress());
			 */
			ps.setString(3, "0");
			
			if (PascaBayar.getId() != null) {
				ps.setLong(4, PascaBayar.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
