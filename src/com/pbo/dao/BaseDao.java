package com.pbo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.ngalongware.util.Connection;
import com.ngalongware.util.Helper;

public class BaseDao {
	private Logger logger = Logger.getLogger(BaseDao.class);
	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public Session getSession() {
		return connection.getSession();
	}

	public Boolean save (Object object) {
		Boolean isSaved = false;
		Session session = null; 

		try {
			session = getSession();
			session.beginTransaction();
			
			session.save(object);
			isSaved = true;
		} catch (Exception e) {
			isSaved = false;
			logger.error("Error while insert. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return isSaved;
	}
	
	public Boolean saveAll (List<Object> listObject) {
		Boolean isSaved = false;
		Session session = null; 

		try {
			session = getSession();
			session.beginTransaction();
			
			for (int i = 0; i < listObject.size(); i++) {
				Object object = listObject.get(i);
				session.save(object);
				isSaved = true;
			}
		} catch (Exception e) {
			isSaved = false;
			logger.error("Error while insert. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return isSaved;
	}
	
	public Boolean update (Object object) {
		Session session = null; 
		Boolean isUpdate = false;
		try {
			session = getSession();
			session.beginTransaction();
			session.update(object);
			isUpdate = true;
		} catch (Exception e) {
			isUpdate = false;
			logger.error("Error while update. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return isUpdate;
	}
	
	public Boolean updateAll (List<Object> listObject) {
		Boolean isSaved = false;
		Session session = null; 

		try {
			session = getSession();
			session.beginTransaction();
			
			for (int i = 0; i < listObject.size(); i++) {
				Object object = listObject.get(i);
				session.update(object);
				isSaved = true;
			}
		} catch (Exception e) {
			isSaved = false;
			logger.error("Error while insert. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return isSaved;
	}
	
	
	public void delete (Object object) {
		Session session = null; 

		try {
			session = getSession();
			session.beginTransaction();
			session.delete(object);
			
		} catch (Exception e) {
			logger.error("Error while delete. " + e.getMessage(), e);
			
		} finally {
			Helper.closeSession(session);
		}
	}
	
	public Boolean deleteAll (List<Object> listObject) {
		Boolean isSaved = false;
		Session session = null; 

		try {
			session = getSession();
			session.beginTransaction();
			
			for (int i = 0; i < listObject.size(); i++) {
				Object object = listObject.get(i);
				session.delete(object);
				isSaved = true;
			}
		} catch (Exception e) {
			isSaved = false;
			logger.error("Error while insert. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return isSaved;
	}
}
