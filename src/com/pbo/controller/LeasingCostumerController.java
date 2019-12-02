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
public class LeasingCostumerController extends BaseController {
	@RequestMapping(value="Costumer", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/costumer";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		LeasingCostumerDao parameterDao = new LeasingCostumerDao(connection);
		List <Costumer> listParameter = parameterDao.getListParameter();
		response.addObject("costumer", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="costumer/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			LeasingCostumerDao parameterDao = new LeasingCostumerDao(connection);
			Costumer parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="costumer/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String no_ktp, String id, String nama, String jenkel, String ttl,
			String alamat, String phone) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		LeasingCostumerDao parameterDao = new LeasingCostumerDao(connection);
		List<Costumer> listCostumer = parameterDao.getListParameter();
		Costumer costumer = null;
		Date date = new Date();
		
		if (listCostumer != null) {
			costumer = listCostumer.get(0);
			costumer.setNo_KTP(no_ktp);
			costumer.setId(id);
			costumer.setNama(nama);
			costumer.setJenkel(jenkel);
			costumer.setTTL(ttl);
			costumer.setAlamat(alamat);
			costumer.setPhone(phone);
			
			
			costumer.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			costumer.setUpdatedDate(date);
			
			parameterDao.update(costumer);
		} else {
			costumer = new Costumer();
			costumer.setNo_KTP(no_ktp);
			costumer.setId(id);
			costumer.setNama(nama);
			costumer.setJenkel(jenkel);
			costumer.setTTL(ttl);
			costumer.setAlamat(alamat);
			costumer.setPhone(phone);
			
			
			parameterDao.save(costumer);
		}
		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="costumer/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		LeasingCostumerDao parameterDao = new LeasingCostumerDao(connection);
		Costumer parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}
