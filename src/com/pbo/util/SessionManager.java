package com.pbo.util;

public class SessionManager {
	
	private static SessionManager instance = new SessionManager();
	
	
	public SessionManager() {
	}

	public static SessionManager getInstance() {
		return instance;
	}

	
}
