package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Criteria;
import com.pbo.bean.PerumahanHistori;
import com.pbo.bean.BpjsTrxHistory;
import com.pbo.dao.CriteriaDao;
import com.pbo.dao.PerumahanHistoriDao;
import com.pbo.dao.BpjsTrxHistory;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class BpjsTrxHistory extends BaseController {
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
		BpjsTrxHistoryDao parameterDao = new BpjsTrxHistoryDao(connection);
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
			BpjsTrxHistoryDao parameterDao = new BpjsTrxHistoryDao(connection);
			BpjsTrxHistoryController parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="costumer/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(long id_trx, String tgl_trx, boolean isvalid) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		BpjsTrxHistoryDao parameterDao = new BpjsTrxHistoryDao(connection);
		List<Costumer> listCostumer = parameterDao.getListParameter();
		Costumer BpjsTrxHistory  = null;
		Date date = new Date();
		
		if (listCostumer != null) {
			BpjsTrxHistory  = listCostumer.get(0);
			BpjsTrxHistory .setId_Trx(id_trx);
			BpjsTrxHistory .setTgl_Trx(tgl_trx);
			BpjsTrxHistory .setIsvalid(isvalid);
			
			
			BpjsTrxHistory .setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			BpjsTrxHistory .setUpdatedDate(date);
			
			parameterDao.update(BpjsTrxHistory );
		} else {
			BpjsTrxHistory  = new Costumer();
			BpjsTrxHistory .setId_Trx(id_trx);
			BpjsTrxHistory .setTgl_Trx(tgl_trx);
			BpjsTrxHistory .setIsvalid(isvalid);
			
			parameterDao.save(BpjsTrxHistory);
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

		BpjsTrxHistoryDao parameterDao = new LeasingCostumerDao(connection);
		BpjsTrxHistory parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}

