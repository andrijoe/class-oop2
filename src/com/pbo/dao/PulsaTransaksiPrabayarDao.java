package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.PulsaTransaksiPrabayar;
import com.pbo.util.DbConnection;

public class PulsaTransaksiPrabayarDao {
	public List<PulsaTransaksiPrabayar> getListPulsaTransaksiPrabayar() {
		List<PulsaTransaksiPrabayar> listPulsaTransaksiPrabayar = new ArrayList<PulsaTransaksiPrabayar>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PulsaTransaksiPrabayar pulsatransaksiprabayar = new PulsaTransaksiPrabayar();
				pulsatransaksiprabayar.setNo_Tlp(rs.getString("No_Tlp"));
				pulsatransaksiprabayar.setId(rs.getLong("id"));
				pulsatransaksiprabayar.setNominal(rs.getString("Nominal"));
				pulsatransaksiprabayar.setValid(rs.getString("Valid"));
				pulsatransaksiprabayar.setTgl_Trx(rs.getString("Tgl_Trx"));
				listPulsaTransaksiPrabayar.add(pulsatransaksiprabayar);
			}
			
			if (listPulsaTransaksiPrabayar.size() == 0) {
				listPulsaTransaksiPrabayar = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listPulsaTransaksiPrabayar;
	}
	
	public PulsaTransaksiPrabayar getBankById(String id) {
		PulsaTransaksiPrabayar pulsatransaksiprabayar= null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pulsatransaksiprabayar = new PulsaTransaksiPrabayar();
				pulsatransaksiprabayar.setNo_Tlp(rs.getString("No_Tlp"));
				pulsatransaksiprabayar.setId(rs.getLong("id"));
				pulsatransaksiprabayar.setNominal(rs.getString("Nominal"));
				pulsatransaksiprabayar.setValid(rs.getString("Valid"));
				pulsatransaksiprabayar.setTgl_Trx(rs.getString("Tgl_Trx"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pulsatransaksiprabayar;
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
	
	public boolean saveUpdateBank(PulsaTransaksiPrabayar pulsatransaksiprabayar) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (pulsatransaksiprabayar.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pulsatransaksiprabayar.getNo_Tlp());
			ps.setString(2, pulsatransaksiprabayar.getNominal());
			ps.setString(3, pulsatransaksiprabayar.getValid());
			ps.setString(4, pulsatransaksiprabayar.getTgl_Trx());
			ps.setString(5, "0");
			
			if (pulsatransaksiprabayar.getId() != null) {
				ps.setLong(4, pulsatransaksiprabayar.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
