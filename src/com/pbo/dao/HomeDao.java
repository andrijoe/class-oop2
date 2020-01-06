package com.pbo.dao;

import java.util.List;

import javax.persistence.Access;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.pbo.util.Connection;
import com.pbo.util.Helper;

public class HomeDao extends BaseDao {
	private Logger logger = Logger.getLogger(HomeDao.class);

	public HomeDao(Connection conn) {
		super.setConnection(conn);
	}

	@SuppressWarnings("unchecked")
	public Access checkingUserLoging(String username, String password) {
		Access user = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			String hql = 
					"select u from Access u "
					+ "where u.username = :username and "
					+ "u.password = :password ";

			Query query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("password", password);

			List<Access> listUser = query.list();
			if (listUser.size() > 0) {
				user = listUser.get(0);
			}

		} catch (Exception e) {
			logger.error("Error HomeDaoImp.checkingUserLoging. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public Access getUserById(Long idUser) {
		Access user = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			String sql = "select u from User u "
					+ "where u.idUser = :idUser";

			Query query = session.createQuery(sql);
			query.setParameter("idUser", idUser);

			List<Access> listUser = query.list();
			if (listUser.size() > 0) {
				user = listUser.get(0);
			}

		} catch (Exception e) {
			logger.error("Error HomeDaoImp.getUserById. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return user;
	}

	public Access checkingUserGroup(Access user) {

		return user;
	}

}
