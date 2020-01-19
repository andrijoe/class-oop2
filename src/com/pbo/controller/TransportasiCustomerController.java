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
public class TransportasiCostumerController extends BaseController {
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
		TransportasiCostumerDao parameterDao = new TransportasiCostumerDao(connection);
		List <Transportasi> listParameter = parameterDao.getListParameter();
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
			TransportasiCostumerDao parameterDao = new TransportasiCostumerDao(connection);
			Costumer parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="costumer/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(Long (id), String no_ktp, String nama, String address, String phone, String alamat, String phone, String email) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		TransportasiCostumerDao parameterDao = new TransportasiCostumerDao(connection);
		List<Costumer> listCostumer = parameterDao.getListParameter();
		Costumer costumer = null;
		Date date = new Date();
		
		if (listCostumer != null) {
			costumer = listCostumer.get(0);
			costumer.setId(id);
			costumer.setNo_Ktp(no_ktp);
			costumer.setNama(nama);
			costumer.setAddress(address);
			costumer.setPhone(phone);
			costumer.setEmail(email);
			
			
			costumer.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			costumer.setUpdatedDate(date);
			
			parameterDao.update(costumer);
		} else {
			costumer.setId(id);
			costumer.setNo_Ktp(no_ktp);
			costumer.setNama(nama);
			costumer.setAddress(address);
			costumer.setPhone(phone);
			costumer.setEmail(email);
			
			
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

		TransportasiCostumerDao parameterDao = new TransportasiCostumerDao(connection);
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
