package com.pbo.util;

import java.util.ArrayList;
import java.util.List;

import com.ngalongware.bean.Access;
import com.ngalongware.type.AccessType;
import com.ngalongware.type.Menu;

public class SessionManager {
	
	private static SessionManager instance = new SessionManager();
	private Access loggedUser;
	private List<AccessType> listAccessType = new ArrayList<AccessType>();
	private List<Menu> listMenuStudent = new ArrayList<Menu>();
	private List<Menu> listMenuHead = new ArrayList<Menu>();
	
	
	public SessionManager() {
		setListAccessType();
		setListMenuHead();
		setListMenuStudent();
	}

	public static SessionManager getInstance() {
		return instance;
	}

	public Access getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Access loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	private void setListAccessType() {
		listAccessType.add(AccessType.HEADMASTER);
		listAccessType.add(AccessType.HEADPRODY);
		listAccessType.add(AccessType.ADMIN);
		
	}
	
	private void setListMenuStudent() {
		listMenuStudent.add(Menu.RESULT);
	}
	
	private void setListMenuHead() {
		listMenuHead.add(Menu.ACCESS);
		listMenuHead.add(Menu.CRITERIA);
		listMenuHead.add(Menu.ALTERNATIVE);
		listMenuHead.add(Menu.ENTITY);
		listMenuHead.add(Menu.AHP);
		listMenuHead.add(Menu.RESULT);
	}
	
	public List<Menu> getListByAccess(String code) {
		List<Menu> listMenu = null;
		if (code.equalsIgnoreCase(AccessType.ADMIN.getCode())) {
			listMenu = listMenuStudent;
		} else if (code.equalsIgnoreCase(AccessType.HEADMASTER.getCode()) ||
				code.equalsIgnoreCase(AccessType.HEADPRODY.getCode())) {
			listMenu = listMenuHead;
		}
		
		return listMenu;
	}
	
	public String getCodeAccessType(String code) {
		String codeAccessType = null;
		for (int i = 0; i < listAccessType.size(); i++) {
			if (code.equalsIgnoreCase(listAccessType.get(i).getCode())) {
				codeAccessType = listAccessType.get(i).getCode();
			}
		}
		
		return codeAccessType;
	}

	public List<AccessType> getListAccessType() {
		return listAccessType;
	}
	
}
