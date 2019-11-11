package com.pbo.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.ngalongware.bean.Access;

public class Helper {
	private static Logger logger = Logger.getLogger(Helper.class);

	public static String convertingNull (String value) {
		if (value.equalsIgnoreCase(Constant.NULL_VALUE)) {
			value = null;
		}
		return value;
	}

	public static void closeSession(Session session) {
		try {
			if (session != null) {
				try {
					session.flush();
				} catch (Exception ex) {
					session.getTransaction().rollback();
					logger.error("Error hibernate session flush. " + ex.getMessage(), ex);
				} finally {
					session.getTransaction().commit();
					session.clear();
					session.close();
				}
			}
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error("Error close hibernate session. " + ex.getMessage(), ex);
		}
	}

	public static String loggedCheck(String page) {
		SessionManager sessionManager = SessionManager.getInstance();
		if (sessionManager.getLoggedUser() == null) {
			page = Constant.LOGIN_PAGE;
		}
		return page;
	}

	@SuppressWarnings("null")
	public static Boolean verifyPassword(String idUser, String password, String new_password,
			String verify_password) {
		Boolean isPasswordValid = false;
		if ((idUser != null && !idUser.isEmpty()) &&
				(new_password != null && !new_password.isEmpty()) && 
				(verify_password != null && !verify_password.isEmpty()) && 
				(password != null && !password.isEmpty())) {
			
			if (new_password.equals(verify_password)) {
				if (!password.equals(new_password)) {
					isPasswordValid = true;
				}
			}
		} else if (((idUser == null || idUser.isEmpty()) && idUser.isEmpty()) && 
				(password != null && !password.isEmpty())) {
			isPasswordValid = true;
		}
		return isPasswordValid;
	}

	public static Boolean verifyUsername(String username, List<Access> listUser, Access loggedUser) {
		Boolean isUsernameValid = true;

		if (!loggedUser.getUsername().equals(username)) {
			for (int i = 0; i < listUser.size(); i++) {
				Access user = listUser.get(i);
				if (user.getUsername().equals(username)) {
					isUsernameValid = false;
					break;
				}
			}
		}

		return isUsernameValid;
	}

}
