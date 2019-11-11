package com.pbo.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Connection {
	private static Logger logger = Logger.getLogger(Connection.class);
	private static Connection instance = new Connection();
	
	private SessionFactory sessionFactory;

    public Connection() {
        try {
            this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
        	logger.error("Error generate hibernate connection." + ex.getMessage() , ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Connection getInstance() {
		return instance;
	}
    
	public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
