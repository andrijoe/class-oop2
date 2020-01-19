package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Ticketing;
import com.pbo.dao.TicketingDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class TicketingController extends BaseController {

	@RequestMapping(value="Ticketing", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/Ticketing";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		TicketingDao parameterDao = new TicketingDao(connection);
		List <Ticketing> listParameter = parameterDao.getListParameter();
		response.addObject("Ticketing", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="Ticketing/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.TICKETING_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			TicketingDao parameterDao = new TicketingDao(connection);
			Ticketing parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Ticketing/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(Long id String nama, String tipe, String vendor) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.TICKETING_INDEX;
		page = Helper.loggedCheck(page);
		TicketingDao parameterDao = new TicketingDao(connection);
		List<Ticketing> listTicketing = parameterDao.getListParameter();
		Ticketing Ticketing = null;
		Date date = new Date();
		
		if (listTicketing != null) {
			Ticketing = listTicketing.get(0);
			Ticketing.setId(id);
			Ticketing.setId_kendaraan(id_kendaraan);
			Ticketing.setId_custumer(id_custumer);
			Ticketing.setTransportasi(trasportasi);
			Ticketing.setNominal(nominal);
			Ticketing.setTujuan(tujuan);
			Ticketing.setNo_tiket(no_tiket);
			parameterDao.saveUpdateTicketing(Ticketing);
		} else {
			Ticketing = listTicketing.get(0);
			Ticketing.setId(id);
			Ticketing.setId_kendaraan(id_kendaraan);
			Ticketing.setId_custumer(id_custumer);
			Ticketing.setTransportasi(trasportasi);
			Ticketing.setNominal(nominal);
			Ticketing.setTujuan(tujuan);
			Ticketing.setNo_tiket(no_tiket);
			parameterDao.saveUpdateTicketing(Ticketing);
		}		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Ticketing/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		TicketingDao parameterDao = new TicketingDao(connection);
		Ticketing parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}