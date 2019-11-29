package com.pbo.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import java.util.List;

	import com.pbo.bean.PerumahanHistori;
import com.pbo.bean.PlnNontaglis;
import com.pbo.util.DbConnection;

	public class PlnNontaglisDao {
		public List<PlnNontaglis> getListPlnNontaglis() {
			List<PlnNontaglis> listPlnNontaglis = new ArrayList<PlnNontaglis>();
			try {
				Connection conn = DbConnection.getConnection();
				String query = "select * from mbank";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					PlnNontaglis plnnontaglis = new PlnNontaglis();
					plnnontaglis.setId(rs.getLong("id"));
					plnnontaglis.setTransaksi_type(rs.getString("transaksi_type"));
					plnnontaglis.setBiaya(rs.getString("biaya"));
					plnnontaglis.setTgl_pembelian(rs.getString("tgl_pembelian"));
					plnnontaglis.setBukti_transaksi(rs.getString("bukti_transaksi"));
					listPlnNontaglis.add(plnnontaglis);
				}
				
				if (listPlnNontaglis.size() == 0) {
					listPlnNontaglis = null;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return listPlnNontaglis;
		}
		
		public PlnNontaglis getBankById(String id) {
			PlnNontaglis plnnontaglis = null;
			try {
				Connection conn = DbConnection.getConnection();
				String query = "select * from mbank where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(id));
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					plnnontaglis = new PlnNontaglis();
					plnnontaglis.setId(rs.getLong("id"));
					plnnontaglis.setTransaksi_type(rs.getString("transaksi_type"));
					plnnontaglis.setBiaya(rs.getString("biaya"));
					plnnontaglis.setTgl_pembelian(rs.getString("tgl_pembelian"));
					plnnontaglis.setBukti_transaksi(rs.getString("bukti_transaksi"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return plnnontaglis;
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
		
		public boolean saveUpdateBank(PlnNontaglis plnnontaglis) {
			try {
				Connection conn = DbConnection.getConnection();
				String query = "insert into mbank "
						+ "(name, address, isdelete) values "
						+ "(?, ?, ?)";
				
				if (plnnontaglis.getId() != null) {
					query = "update mbank set name = ?, "
							+ "address = ?, isdelete = ? "
							+ "where id = ?";
				}
				
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, plnnontaglis.getTransaksi_type());
				ps.setString(2, plnnontaglis.getBiaya());
				ps.setString(3, plnnontaglis.getTgl_pembelian());
				ps.setString(3, plnnontaglis.getBukti_transaksi());
				ps.setString(4, "0");
				
				if (plnnontaglis.getId() != null) {
					ps.setLong(5, plnnontaglis.getId());
				}
				
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
	}