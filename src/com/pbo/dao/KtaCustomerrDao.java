package com.pbo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pbo.bean.KtaCustomerr;
import com.pbo.util.DbConnection;

public class KtaCustomerrDao {
	public List<KtaCustomerr> getListKtaCustomerr() {
		List<KtaCustomerr> listBank = new ArrayList<KtaCustomerr>();
		try { 
			Connection conn = DbConnection.getConnection();
			String query = "select * from mbank";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				KtaCustomerr KtaCustomerr = new KtaCustomerr();
				KtaCustomerr.setId_cust(rs.getLong("Id_cust"));
				KtaCustomerr.setName(rs.getString("Name"));
				KtaCustomerr.setAddress(rs.getString("Address"));
				KtaCustomerr.setJobs(rs.getString("Jobs"));
				KtaCustomerr.setKTP_Number(rs.getString("KTP_Number"));
				KtaCustomerr.setDateOfBirth(rs.getString("DateOfBirth"));
				KtaCustomerr.setGender(rs.getString("Gender"));

				listBank.add(KtaCustomerr);
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
