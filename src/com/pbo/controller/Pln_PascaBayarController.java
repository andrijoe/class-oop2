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
public class Pln_PascaBayarController extends BaseController {
	@RequestMapping(value="Pln", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/pln";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		Pln_PascaBayarDao parameterDao = new Pln_PascaBayarDao(connection);
		List <Pln> listParameter = parameterDao.getListParameter();
		response.addObject("pln", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="pln/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			Pln_PascaBayarDao parameterDao = new Pln_PascaBayarDao(connection);
			Pln parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="costumer/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String id, String No_Meter, String Nominal_Tagihan, String Tgl_Pembayaran, String No_Bukti_Transaksi)
	{
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		Pln_PascaBayarDao parameterDao = new Pln_PascaBayarDao(connection);
		List<Pln> listpln = parameterDao.getListParameter();
		Pln pln = null;
		Date date = new Date();
		
		if (listPln != null) {
			pln = listPln.get(0);
			pln.setid(id);
			pln.setNo_Meter(No_Meter);
			pln.setNominal_Tagihan(Nominal_Tagihan);
			pln.setTgl_Pembayaran(Tgl_Pembayaran);
			pln.setTTL(No_Bukti_Transaksi);
				
			pln.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			pln.setUpdatedDate(date);
			
			parameterDao.update(pln);
		} else {
			pln = new Pln();
			pln = listPln.get(0);
			pln.setid(id);
			pln.setNo_Meter(No_Meter);
			pln.setNominal_Tagihan(Nominal_Tagihan);
			pln.setTgl_Pembayaran(Tgl_Pembayaran);
			pln.setTTL(No_Bukti_Transaksi);
			
			
			parameterDao.save(pln);
		}
		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="pln/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		Pln_PascaBayarDao parameterDao = new Pln_PascaBayarDao(connection);
		Pln parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}
