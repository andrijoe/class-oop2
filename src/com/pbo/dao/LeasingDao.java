package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.Leasing;
import com.pbo.util.DbConnection;

public class LeasingDao {
	public List<Leasing> getLeasings() {
		List<Leasing> listLeasing = new ArrayList<Leasing>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from Leasing";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Leasing leasing = new Leasing();
				leasing.setNama_leasing(rs.getString("nama_leasing"));
				leasing.setId(rs.getLong("id"));
				leasing.setAlamat(rs.getString("alamat"));
				leasing.setTipe(rs.getString("tipe"));
				leasing.setPhone(rs.getString("phone"));
				listLeasing.add(leasing);
			}
			

			if (listLeasing.size() == 0) {
				listLeasing = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listLeasing;
	}

	public Leasing getLeasingById(String Id) {
		Leasing leasing = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from Leasing where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(Id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				leasing = new Leasing();
				leasing.setNama_leasing(rs.getString("nama_leasing"));
				leasing.setId(rs.getLong("id"));
				leasing.setAlamat(rs.getString("alamat"));
				leasing.setTipe(rs.getString("tipe"));
				leasing.setPhone(rs.getString("phone"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return leasing;
	}

	public boolean deleteLeasing(String Id) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "update Leasing set isdelete = ? " + "where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "1");
			ps.setInt(2, Integer.parseInt(Id));
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean saveUpdateLeasing(Leasing leasing) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into Leasing " + "(nama_leasing, alamat, tipe, phone, email) values " + "(?, ?, ?)";

			if (leasing.getId() != null) {
				query = "update mbank set nama_leasing = ?, " + "alamat = ?, tipe = ?, phone = ?, email = ? "
						+ "where id = ?";
			}

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, leasing.getNama_leasing());
			ps.setString(2, leasing.getAlamat());
			ps.setString(3, "0");

			if (leasing.getId() != null) {
				ps.setLong(4, leasing.getId());
			}

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
