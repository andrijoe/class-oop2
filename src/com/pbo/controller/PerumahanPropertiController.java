package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.PerumahanProperti;
import com.pbo.dao.PerumahanPropertiDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class PerumahanPropertiController extends BaseController  {
	
	@RequestMapping(value="properti", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/properti";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		PerumahanPropertiDao parameterDao = new PerumahanPropertiDao(connection);
		List <PerumahanProperti> listParameter = parameterDao.getListParameter();
		response.addObject("criteria", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="properti/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			PerumahanPropertiDao parameterDao = new PerumahanPropertiDao(connection);
			PerumahanProperti parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="properti/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String id, String type, String address, String vendor) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		PerumahanPropertiDao parameterDao = new PerumahanPropertiDao(connection);
		List<PerumahanProperti> listProperti = parameterDao.getListParameter();
		PerumahanProperti properti = null;
		Date date = new Date();
		
		if (listProperti != null) {
			properti = listProperti.get(0);
			properti.setId(Long.parseLong(id));
			properti.setType(Double.parseDouble(type));
			properti.setAddress(Double.parseDouble(address));
			properti.setVendor(Double.parseDouble(vendor));
			
			properti.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			properti.setUpdatedDate(date);
			
			parameterDao.update(properti);
		} else {
			properti = new PerumahanProperti();
			properti.setId(Long.parseLong(id));
			properti.setType(Double.parseDouble(type));
			properti.setAddress(Double.parseDouble(address));
			properti.setVendor(Double.parseDouble(vendor));
			
			parameterDao.save(properti);
		}
		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="properti/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		PerumahanPropertiDao parameterDao = new PerumahanPropertiDao(connection);
		PerumahanProperti parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}
