package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Leasing;
import com.pbo.dao.LeasingDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class LeasingController extends BaseController {

	@RequestMapping(value="Leasing", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/Leasing";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		LeasingDao parameterDao = new LeasingDao(connection);
		List <Leasing> listParameter = parameterDao.getListParameter();
		response.addObject("Leasing", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="Leasing/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.LEASING_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			LeasingDao parameterDao = new LeasingDao(connection);
			Leasing parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Leasing/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String nama_leasing, String alamat, String tipe, String phone, String email) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.LEASING_INDEX;
		page = Helper.loggedCheck(page);
		LeasingDao parameterDao = new LeasingDao(connection);
		List<Leasing> listLeasing = parameterDao.getListParameter();
		Leasing leasing = null;
		Date date = new Date();
		
		if (listLeasing != null) {
			leasing = listLeasing.get(0);
			leasing.setNama_leasing(nama_leasing);
			leasing.setAlamat(alamat);
			leasing.setTipe(tipe);
			leasing.setPhone(phone);
			leasing.setEmail(email);
			parameterDao.saveUpdateLeasing(leasing);
		} else {
			leasing = listLeasing.get(0);
			leasing.setNama_leasing(nama_leasing);
			leasing.setAlamat(alamat);
			leasing.setTipe(tipe);
			leasing.setPhone(phone);
			leasing.setEmail(email);
			parameterDao.saveUpdateLeasing(leasing);
		}		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="leasing/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		LeasingDao parameterDao = new LeasingDao(connection);
		Leasing parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}