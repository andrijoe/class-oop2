package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.LeasingTransaction;
import com.pbo.util.DbConnection;

public class LeasingTransactionDao {
	public List<LeasingTransaction> getListLeasingTransaction() {
		List<LeasingTransaction> listLeasingTransaction = new ArrayList<LeasingTransaction>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LeasingTransaction Transaction = new LeasingTransaction();
				Transaction.setNokontrak(rs.getString("nokontrak"));
				Transaction.setId(rs.getLong("id"));
				Transaction.setIdLeasing(rs.getString("idLeasing"));
				Transaction.setIdCustomer(rs.getString("idCustomer"));
				Transaction.setNominal(rs.getString("nominal"));
				Transaction.setTglTry(rs.getString("tglTry"));
				Transaction.setIdVehicle(rs.getString("IdVehicle"));
				listLeasingTransaction.add(Transaction);
			}
			
			if (listLeasingTransaction.size() == 0) {
				listLeasingTransaction = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listLeasingTransaction;
	}
	
	public LeasingTransaction getLeasingTransactionById(String id) {
		LeasingTransaction transaction = null;
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank where id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				transaction = new LeasingTransaction();
				transaction.setNokontrak(rs.getString("nokontrak"));
				transaction.setId(rs.getLong("id"));
				transaction.setIdLeasing(rs.getString("idLeasing"));
				transaction.setIdCustomer(rs.getString("idCustomer"));
				transaction.setNominal(rs.getString("nominal"));
				transaction.setTglTry(rs.getString("tglTry"));
				transaction.setIdVehicle(rs.getString("IdVehicle"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transaction;
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
	
	public boolean saveUpdateLeasingTransaction(LeasingTransaction Transaction) {
		try {
			Connection conn = DbConnection.getConnection();
			String query = "insert into mbank "
					+ "(name, address, isdelete) values "
					+ "(?, ?, ?)";
			
			if (Transaction.getId() != null) {
				query = "update mbank set name = ?, "
						+ "address = ?, isdelete = ? "
						+ "where id = ?";
			}
			
			PreparedStatement ps = conn.prepareStatement(query);
			/*
			 * ps.setString(1, Transaction.getName()); ps.setString(2,
			 * Transaction.getAddress());
			 */
			ps.setString(3, "0");
			
			if (Transaction.getId() != null) {
				ps.setLong(4, Transaction.getId());
			}
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
