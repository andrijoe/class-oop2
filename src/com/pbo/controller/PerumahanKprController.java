package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.PerumahanKpr;
import com.pbo.dao.CriteriaDao;
import com.pbo.dao.PerumahanKprDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class PerumahanKprController extends BaseController {
	
	@RequestMapping(value="kpr", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/kpr";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		PerumahanKprDao parameterDao = new PerumahanKprDao(connection);
		List <PerumahanKpr> listParameter = parameterDao.getListParameter();
		response.addObject("PerumahanKpr", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="kpr/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			PerumahanKprDao parameterDao = new PerumahanKprDao(connection);
			PerumahanKpr parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="kpr/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(Long id, String idhome, String harga, String bunga, String tenor, String totalHarga) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		PerumahanKprDao parameterDao = new PerumahanKprDao(connection);
		List<PerumahanKpr> listProperti = parameterDao.getListParameter();
		PerumahanKpr kpr = null;
		Date date = new Date();
		
		if (listKpr != null) {
			kpr = listProperti.get(0);
			kpr.setId(id);
			kpr.setIdhome(idhome);
			kpr.setHarga(harga);
			kpr.setBunga(bunga);
			kpr.setTenor(tenor);
			kpr.setTotalHarga(totalHarga);
			
			kpr.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			kpr.setUpdatedDate(date);
			
			parameterDao.update(kpr);
		} else {
			kpr = new PerumahanKpr();
			kpr.setId(id);
			kpr.setIdhome(idhome);
			kpr.setHarga(harga);
			kpr.setBunga(bunga);
			kpr.setTenor(tenor);
			kpr.setTotalHarga(totalHarga);
			
			parameterDao.save(kpr);
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

		PerumahanKprDao parameterDao = new PerumahanKprDao(connection);
		PerumahanKpr parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}
