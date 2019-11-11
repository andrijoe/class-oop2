package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ngalongware.bean.Entyty;
import com.ngalongware.bean.Result;
import com.ngalongware.dao.EntityDao;
import com.ngalongware.dao.ResultDao;
import com.ngalongware.util.Connection;
import com.ngalongware.util.Constant;
import com.ngalongware.util.Helper;
import com.ngalongware.util.SessionManager;

@Controller
public class EntityController extends BaseController {
	private Logger logger = Logger.getLogger(EntityController.class);

	@RequestMapping(value=Constant.ADMIN_EXTENDED + "entity", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initLecturer(response);
		String page = Constant.ENTITY_INDEX;
		page = Helper.loggedCheck(page);

		response.setViewName(page);
		return response;
	}

	public ModelAndView initLecturer(ModelAndView response) {
		Connection connection = Connection.getInstance();
		EntityDao lecturerDao = new EntityDao(connection);
		List <Entyty> listLecturer = lecturerDao.getListLecturer();
		response.addObject("listEntity", listLecturer);
		return response;
	}

	@RequestMapping(value=Constant.ADMIN_EXTENDED + "entity/form", method = RequestMethod.GET)
	public ModelAndView form(String idEntity) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initLecturer(response);

		String page = Constant.ENTITY_FORM;
		page = Helper.loggedCheck(page);

		if (idEntity != null && !idEntity.isEmpty()) {
			EntityDao lecturerDao = new EntityDao(connection);
			Entyty lecturer = lecturerDao.getLecturerById(idEntity);
			response.addObject("entity", lecturer);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value=Constant.ADMIN_EXTENDED + "entity/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String idEntity, String fullName, String bhsAsing, String disiplinShalat,
			String doa, String gender, String mahfudhot, String muhadarah, String pai, String prilaku, String rapor,
			String tahfidQuran) {

		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();

		String page = Constant.ENTITY_INDEX;
		page = Helper.loggedCheck(page);
		EntityDao lecturerDao = new EntityDao(connection);
		Entyty entity = null;

		try {
			if  (idEntity == null || idEntity.isEmpty()) {
				entity = new Entyty();
				entity.setFullName(fullName);
				entity.setGender(gender);
				entity.setBhsAsing(Double.parseDouble(bhsAsing));
				entity.setDisiplinShalat(Double.parseDouble(disiplinShalat));
				entity.setDoa(Double.parseDouble(doa));
				entity.setMahfudhot(Double.parseDouble(mahfudhot));
				entity.setMuhadarah(Double.parseDouble(muhadarah));
				entity.setPai(Double.parseDouble(pai));
				entity.setPrilaku(Double.parseDouble(prilaku));
				entity.setRapor(Double.parseDouble(rapor));
				entity.setTahfidQuran(Double.parseDouble(tahfidQuran));
				
				entity.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
				entity.setUpdatedDate(new Date());
				
				lecturerDao.save(entity);
				
			} else {
				entity = lecturerDao.getLecturerById(idEntity);
				entity.setFullName(fullName);
				entity.setGender(gender);
				entity.setBhsAsing(Double.parseDouble(bhsAsing));
				entity.setDisiplinShalat(Double.parseDouble(disiplinShalat));
				entity.setDoa(Double.parseDouble(doa));
				entity.setMahfudhot(Double.parseDouble(mahfudhot));
				entity.setMuhadarah(Double.parseDouble(muhadarah));
				entity.setPai(Double.parseDouble(pai));
				entity.setPrilaku(Double.parseDouble(prilaku));
				entity.setRapor(Double.parseDouble(rapor));
				entity.setTahfidQuran(Double.parseDouble(tahfidQuran));
				
				entity.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
				entity.setUpdatedDate(new Date());
				
				lecturerDao.update(entity);
			}
		} catch (Exception e) {
			logger.error("Error parse date. " + e.getMessage(), e);
		}

		response = initLecturer(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value=Constant.ADMIN_EXTENDED + "entity/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idEntity) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();

		EntityDao lecturerDao = new EntityDao(connection);
		ResultDao resultDao = new ResultDao(connection);
		Entyty lecturer = lecturerDao.getLecturerById(idEntity);
		Result result = resultDao.getResultByIdEntity(idEntity);
		
		resultDao.delete(result);
		lecturerDao.delete(lecturer);

		String page = Constant.ENTITY_INDEX;
		page = Helper.loggedCheck(page);
		response = initLecturer(response);
		response.setViewName(page);
		return response;
	}
}
