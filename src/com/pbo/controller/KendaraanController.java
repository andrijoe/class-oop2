package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Kendaraan;
import com.pbo.dao.KendaraanDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class KendaraanController extends BaseController {

	@RequestMapping(value="Leasing", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/Kendaraan";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		KendaraanDao parameterDao = new KendaraanDao(connection);
		List <Kendaraan> listParameter = parameterDao.getListParameter();
		response.addObject("Kendaraan", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="Kendaraan/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.KENDARAAN_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			KendaraanDao parameterDao = new KendaraanDao(connection);
			Kendaraan parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Leasing/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(Long id String nama, String tipe, String vendor) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.KENDARAAN_INDEX;
		page = Helper.loggedCheck(page);
		KendaraanDao parameterDao = new KendaraanDao(connection);
		List<Kendaraan> listKendaraan = parameterDao.getListParameter();
		Kendaraan kendaraan = null;
		Date date = new Date();
		
		if (listKendaraan != null) {
			kendaraan = listKendaraan.get(0);
			kendaraan.setId(id);
			kendaraan.setNama(nama);
			kendaraan.setTipe(tipe);
			kendaraan.setVendor(vendor);
			parameterDao.saveUpdateKendaraan(kendaraan);
		} else {
			kendaraan = listKendaraan.get(0);
			kendaraan.setId(id);
			kendaraan.setNama(nama);
			kendaraan.setTipe(tipe);
			kendaraan.setVendor(vendor);
			parameterDao.saveUpdateKendaraan(kendaraan);
		}		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Kendaraan/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		KendaraanDao parameterDao = new KendaraanDao(connection);
		Kendaraan parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}