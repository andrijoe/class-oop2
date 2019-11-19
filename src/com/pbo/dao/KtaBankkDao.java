package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.pbo.bean.KtaBankk;
import com.pbo.util.DbConnection;

public class KtaBankkDao {
	public List<KtaBankk> getListBank() {
		List<KtaBankk> listKtaBankk = new ArrayList<KtaBankk>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				KtaBankk Bankk = new KtaBankk();
				Bankk.setId(rs.getLong("id"));
				Bankk.setBank_name(rs.getString("bank_name"));
				Bankk.setAddress(rs.getString("address"));
				Bankk.setPhone(rs.getString("phone"));
				Bankk.setEmail(rs.getString("email"));
				listKtaBankk.add(Bankk);
			}
			
			if (listKtaBankk.size() == 0) {
				listKtaBankk = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listKtaBankk;
	}
}
