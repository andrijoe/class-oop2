package com.pbo.testing;

import com.pbo.bean.Nasabah;

public class TestingModel {
	public static void main(String args[]) {
		Nasabah nasabahSatu = new Nasabah();
		nasabahSatu.setId(1l);
		nasabahSatu.setName("UwU");
		nasabahSatu.setAccountNo("123456");
		nasabahSatu.setAddress("Cimone");
		nasabahSatu.setCity("Tangerang");
		
		System.out.println("Data: \n"
				+ "id:" + nasabahSatu.getId() 
				+ "\nnama:" + nasabahSatu.getName()
				);
		
		
		
	}
}
