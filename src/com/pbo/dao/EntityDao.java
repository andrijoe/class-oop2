package com.pbo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ngalongware.bean.Entyty;
import com.ngalongware.util.Connection;
import com.ngalongware.util.Helper;

public class EntityDao extends BaseDao {
	private Logger logger = Logger.getLogger(EntityDao.class);

	public EntityDao (Connection connection) {
		super.setConnection(connection);
	}

	@SuppressWarnings("unchecked")
	public List<Entyty> getListLecturer() {
		List<Entyty> listLecturer = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			String sql = "select l from Entyty l ";
			Query query = session.createQuery(sql);
			
			listLecturer = query.list();
			if (listLecturer.size() < 1) {
				listLecturer = null;
				logger.info("No Record Found");
			}
		} catch (Exception e) {
			logger.error("Error LecturerDao.getListLecturer. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return listLecturer;
	}

	@SuppressWarnings("unchecked")
	public Entyty getLecturerById(String idEntity) {
		Entyty lecturer = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			String sql = "select l from Entyty l "
					+ "where l.idEntity = :idEntity";
			
			Query query = session.createQuery(sql);
			query.setParameter("idEntity", Long.parseLong(idEntity));
			
			List<Entyty> listLecturer = query.list();
			if (listLecturer.size() > 0) {
				lecturer = listLecturer.get(0);
			}
		} catch (Exception e) {
			logger.error("Error EntityDao.getEntityById. " + e.getMessage(), e);
		} finally {
			Helper.closeSession(session);
		}
		return lecturer;
	}

}