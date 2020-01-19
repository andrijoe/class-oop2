package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Ht_Transportasi;
import com.pbo.dao.Ht_TransportasiDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class Ht_TransportasiController extends BaseController {

	@RequestMapping(value="Ht_Transportasi", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/Ht_Transportasi";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		Ht_TransportasiDao parameterDao = new Ht_TransportasiDao(connection);
		List <Ht_Transportasi> listParameter = parameterDao.getListParameter();
		response.addObject("Ht_Transportasi", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="Ht_Transportasi/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.HT_TRANSPORTASI_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			Ht_TransportasiDao parameterDao = new Ht_TransportasiDao(connection);
			Ht_Transportasi parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Ht_Transportasi/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(Long id String nama, String tipe, String vendor) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.HT_TRANSPORTASI_INDEX;
		page = Helper.loggedCheck(page);
		Ht_TransportasiDao parameterDao = new Ht_TransportasiDao(connection);
		List<Ht_Transportasi> listHt_Transportasi = parameterDao.getListParameter();
		Ht_Transportasi Ht_Transportasi = null;
		Date date = new Date();
		
		if (listHt_Transportasi != null) {
			Ht_Transportasi = listHt_Transportasi.get(0);
			Ht_Transportasi.setId(id);
			Ht_Transportasi.setNo_tiket(no_tiket);
			Ht_Transportasi.setTgl_transaksi(tgl_transaksi);
			parameterDao.saveUpdateHt_Transportasi(Ht_Transportasi);
		} else {
			Ht_Transportasi = listHt_Transportasi.get(0);
			Ht_Transportasi.setId(id);
			Ht_Transportasi.setNo_tiket(no_tiket);
			Ht_Transportasi.setTgl_transaksi(tgl_transaksi);
			parameterDao.saveUpdateHt_Transportasi(Ht_Transportasi);
		}		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Ht_Transportasi/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		Ht_TransportasiDao parameterDao = new Ht_TransportasiDao(connection);
		Ht_Transportasi parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}