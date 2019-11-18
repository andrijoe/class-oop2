package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.KtaTransaction;
import com.pbo.util.DbConnection;

public class KtaTransacationDao {
	public List<KtaTransaction> getListKtaTransaction() {
		List<KtaTransaction> listBank = new ArrayList<KtaTransaction>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				KtaTransaction KtaTransaction = new KtaTransaction();
				KtaTransaction.setId(rs.getLong("Id"));
				KtaTransaction.setId_Bank(rs.getLong("Id_Bank"));
				KtaTransaction.setId_cust(rs.getLong("Id_cust"));
				KtaTransaction.setMin_money(rs.getLong("Min_money"));
				KtaTransaction.setTenor(rs.getLong("Tenor"));
				KtaTransaction.setTempo(rs.getString("Tempo"));
				listBank.add(KtaTransaction);
			}
			
			if (listBank.size() == 0) {
				listBank = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listBank;
	}
}
