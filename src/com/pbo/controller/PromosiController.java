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
public class PromosiController extends BaseController {

	@RequestMapping(value="Promosi", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/Promosi";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		LeasingDao parameterDao = new PromosiDao(connection);
		List <Promosi> listParameter = parameterDao.getListParameter();
		response.addObject("Promosi", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="Promosi/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.PROMOSI_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			PromosiDao parameterDao = new PromosiDao(connection);
			Promosi parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Promosi/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String nama_leasing, String alamat, String tipe, String phone, String email) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.PROMOSI_INDEX;
		page = Helper.loggedCheck(page);
		PromosiDao parameterDao = new PromosiDao(connection);
		List<Promosi> listPromosi = parameterDao.getListParameter();
		Promosi promosi = null;
		Date date = new Date();
		
		if (listPromosi != null) {
			promosi = listPromosi.get(0);
			promosi.setId(id);
			promosi.setId_Kendaraan(id_kendaraan);
			promosi.setDiskon(diskon);
			promosi.setPlatform(platform);
			
			parameterDao.saveUpdatePromosi(promosi);
		} else {
			promosi = listPromosi.get(0);
			promosi = listPromosi.get(0);
			promosi.setId(id);
			promosi.setId_Kendaraan(id_kendaraan);
			promosi.setDiskon(diskon);
			promosi.setPlatform(platform);
			parameterDao.saveUpdatePromosi(promosi);
		}		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="promosi/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		promosiDao parameterDao = new PromosiDao(connection);
		promosi parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}