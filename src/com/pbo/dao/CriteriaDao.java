package com.pbo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ngalongware.bean.Criteria;
import com.ngalongware.util.Connection;
import com.ngalongware.util.Helper;

public class CriteriaDao extends BaseDao {
	private Logger logger = Logger.getLogger(CriteriaDao.class);

	public CriteriaDao (Connection connection) {
		super.setConnection(connection);
	}

	@SuppressWarnings("unchecked")
	public List<Criteria> getListParameter() {
		List<Criteria> listParameter = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			String sql = "select p from Criteria p ";
			Query query = session.createQuery(sql);
			
			listParameter = query.list();
			if (listParameter.size() < 1) {
				listParameter = null;
				logger.info("No Record Found");
			}
		} catch (Exception e) {
			logger.error("Error CriteriaDao.getListParameter. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return listParameter;
	}

	@SuppressWarnings("unchecked")
	public Criteria getParameterById(String idParameter) {
		Criteria parameter = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			String sql = "select p from Parameter p "
					+ "where p.idParameter = :idParameter and "
					+ "p.isActive = :status";
			Query query = session.createQuery(sql);
			query.setParameter("idParameter", Long.parseLong(idParameter));
			query.setParameter("status", true);
			
			List<Criteria> listParameter = query.list();
			if (listParameter.size() > 0) {
				parameter = listParameter.get(0);
			}
		} catch (Exception e) {
			logger.error("Error ParameterDao.getParameterById. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return parameter;
	}

}
