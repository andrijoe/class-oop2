package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Criteria;
import com.pbo.bean.PlnNontaglis;
import com.pbo.dao.CriteriaDao;
import com.pbo.dao.PlnNontaglisDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class PlnNontaglisController extends BaseController {
	@RequestMapping(value="PlnNontaglis", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/plnnontaglis";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		PlnNontaglisDao parameterDao = new PlnNontaglisDao(connection);
		List <PlnNontaglis> listParameter = parameterDao.getListParameter();
		response.addObject("plnnontaglis", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="plnnontaglis/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			PlnNontaglisDao parameterDao = new PlnNontaglisDao(connection);
			PlnNontaglis parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="plnnontaglis/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String id, String transaksi_type, String biaya, String tgl_pembelian,
			String bukti_transaksi) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		PlnNontaglisDao parameterDao = new PlnNontaglisDao(connection);
		List<PlnNontaglis> listPlnNontaglis = parameterDao.getListParameter();
		PlnNontaglis plnnontaglis = null;
		Date date = new Date();
		
		if (listPlnNontaglis != null) {
			plnnontaglis = listPlnNontaglis.get(0);
			plnnontaglis.setId(id);
			plnnontaglis.setTransaksi_type(transaksi_type);
			plnnontaglis.setBiaya(biaya);
			plnnontaglis.setTgl_pembelian(tgl_pembelian);
			plnnontaglis.setBukti_transaksi(bukti_transaksi);
			
			
			
			plnnontaglis.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			plnnontaglis.setUpdatedDate(date);
			
			parameterDao.update(plnnontaglis);
		} else {
			plnnontaglis = new PlnNontaglis();
			plnnontaglis.setId(id);
			plnnontaglis.setTransaksi_type(transaksi_type);
			plnnontaglis.setBiaya(biaya);
			plnnontaglis.setTgl_pembelian(tgl_pembelian);
			plnnontaglis.setBukti_transaksi(bukti_transaksi);
			
			
			
			parameterDao.save(plnnontaglis);
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

		PlnNontaglisDao parameterDao = new PlnNontaglisDao(connection);
		PlnNontaglis parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}
