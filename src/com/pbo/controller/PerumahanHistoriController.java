package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Criteria;
import com.pbo.dao.CriteriaDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class CriteriaController extends BaseController {

	@RequestMapping(value="criteria", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/criteria";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		CriteriaDao parameterDao = new CriteriaDao(connection);
		List <Criteria> listParameter = parameterDao.getListParameter();
		response.addObject("criteria", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="criteria/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			CriteriaDao parameterDao = new CriteriaDao(connection);
			Criteria parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="criteria/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String bhsAsing, String disiplinShalat, String doa, String mahfudhot, String muhadarah,
			String pai, String prilaku, String rapor, String tahfidQuran) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		CriteriaDao parameterDao = new CriteriaDao(connection);
		List<Criteria> listCriteria = parameterDao.getListParameter();
		Criteria criteria = null;
		Date date = new Date();
		
		if (listCriteria != null) {
			criteria = listCriteria.get(0);
			criteria.setBhsAsing(Double.parseDouble(bhsAsing));
			criteria.setDisiplinShalat(Double.parseDouble(disiplinShalat));
			criteria.setDoa(Double.parseDouble(doa));
			criteria.setMahfudhot(Double.parseDouble(mahfudhot));
			criteria.setMuhadarah(Double.parseDouble(muhadarah));
			criteria.setPai(Double.parseDouble(pai));
			criteria.setPrilaku(Double.parseDouble(prilaku));
			criteria.setRapor(Double.parseDouble(rapor));
			criteria.setTahfidQuran(Double.parseDouble(tahfidQuran));
			
			criteria.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			criteria.setUpdatedDate(date);
			
			parameterDao.update(criteria);
		} else {
			criteria = new Criteria();
			criteria.setBhsAsing(Double.parseDouble(bhsAsing));
			criteria.setDisiplinShalat(Double.parseDouble(disiplinShalat));
			criteria.setDoa(Double.parseDouble(doa));
			criteria.setMahfudhot(Double.parseDouble(mahfudhot));
			criteria.setMuhadarah(Double.parseDouble(muhadarah));
			criteria.setPai(Double.parseDouble(pai));
			criteria.setPrilaku(Double.parseDouble(prilaku));
			criteria.setRapor(Double.parseDouble(rapor));
			criteria.setTahfidQuran(Double.parseDouble(tahfidQuran));
			
			parameterDao.save(criteria);
		}
		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="criteria/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		CriteriaDao parameterDao = new CriteriaDao(connection);
		Criteria parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}