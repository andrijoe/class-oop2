package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.LeasingTransaction;
import com.pbo.dao.LeasingTransactionDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

public class LeasingTransactionController extends BaseController{

	@RequestMapping(value="LeasingTransaction", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/LeasingTransaction";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		LeasingTransactionDao parameterDao = new LeasingTransactionDao(connection);
		List <LeasingTransaction> listParameter = parameterDao.getListParameter();
		response.addObject("LeasingTransaction", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="LeasingTransaction/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			LeasingTransactionDao parameterDao = new LeasingTransactionDao(connection);
			LeasingTransaction parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="LeasingTransaction/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String Nokontrak, String Id, String IdLeasing , String IdCustomer, String Nominal, String TglTry, String IdVehicle) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		LeasingTransactionDao parameterDao = new LeasingTransactionDao(connection);
		List<LeasingTransaction> listTransaction = parameterDao.getListParameter();
		LeasingTransaction transaction = null;
		Date date = new Date();
		
		if (listTransaction != null) {
			transaction = listTransaction.get(0);
			transaction.setNokontrak(nokontrak);
			transaction.setId(id);
			transaction.setIdCustomer(idCustomer);
			transaction.setNominal(nominal);
			transaction.setTglTry(tglTry);
			transaction.setIdVehicle(idVehicle);
			parameterDao.saveUpdateLeasingTransaction(transaction);
		} else {
			transaction = listTransaction.get(0);
			transaction.setNokontrak(nokontrak);
			transaction.setId(id);
			transaction.setIdCustomer(idCustomer);
			transaction.setNominal(nominal);
			transaction.setTglTry(tglTry);
			transaction.setIdVehicle(idVehicle);
			parameterDao.saveUpdateLeasingTransaction(transaction);
		}		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="LeasingTransaction/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		LeasingTransactionDao parameterDao = new LeasingTransactionDao(connection);
		LeasingTransaction parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.saveUpdateLeasingTransaction(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
	
}
