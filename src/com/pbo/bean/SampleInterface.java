package com.pbo.bean;

import java.util.List;
import java.util.Map;

public interface SampleInterface {
	public boolean isSaved();
	public boolean isDeleted();
	public List<String> listData();
	public Map<String, Nasabah> mapNasabah();
	public Map<String, Bank> mapBank();
	
}
