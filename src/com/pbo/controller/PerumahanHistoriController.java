package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.Criteria;
import com.pbo.bean.PerumahanHistori;
import com.pbo.dao.CriteriaDao;
import com.pbo.dao.PerumahanHistoriDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class PerumahanHistoriController extends BaseController {
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
		PerumahanHistoriDao parameterDao = new PerumahanHistoriDao(connection);
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
			PerumahanHistoriDao parameterDao = new PerumahanHistoriDao(connection);
			PerumahanHistoriController parameter = parameterDao.getParameterById(idParameter);
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
		PerumahanHistoriDao parameterDao = new PerumahanHistoriDao(connection);
		List<Costumer> listCostumer = parameterDao.getListParameter();
		Costumer perumahanhistori  = null;
		Date date = new Date();
		
		if (listCostumer != null) {
			perumahanhistori  = listCostumer.get(0);
			perumahanhistori .setNo_KTP(no_ktp);
			perumahanhistori .setId(id);
			perumahanhistori .setNama(nama);
			perumahanhistori .setJenkel(jenkel);
			perumahanhistori .setTTL(ttl);
			perumahanhistori .setAlamat(alamat);
			perumahanhistori .setPhone(phone);
			
			
			perumahanhistori .setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			perumahanhistori .setUpdatedDate(date);
			
			parameterDao.update(perumahanhistori );
		} else {
			perumahanhistori  = new Costumer();
			perumahanhistori .setNo_KTP(no_ktp);
			perumahanhistori .setId(id);
			perumahanhistori .setNama(nama);
			perumahanhistori .setJenkel(jenkel);
			perumahanhistori .setTTL(ttl);
			perumahanhistori .setAlamat(alamat);
			perumahanhistori .setPhone(phone);
			
			
			parameterDao.save(perumahanhistori );
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

		PerumahanHistoriDao parameterDao = new LeasingCostumerDao(connection);
		PerumahanHistori parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}
