package com.pbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pbo.bean.PulsaTrxPascabayar;
import com.pbo.dao.PulsaTrxPascabayarDao;
import com.pbo.util.Connection;
import com.pbo.util.Constant;
import com.pbo.util.Helper;
import com.pbo.util.SessionManager;

@Controller
public class PulsaTrxPascabayarController extends BaseController {

	@RequestMapping(value="criteria", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/criteria";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		PulsaTrxPascabayarDao parameterDao = new PulsaTrxPascabayarDao(connection);
		List <Criteria> listParameter = parameterDao.getListParameter();
		response.addObject("criteria", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="criteria/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			PulsaTrxPascabayarDao parameterDao = new PulsaTrxPascabayarDao(connection);
			Criteria parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="criteria/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(String bhsAsing, String disiplinShalat, String doa, String mahfudhot, String muhadarah,
			String pai, String prilaku, String rapor, String tahfidQuran) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		PulsaTrxPascabayarDao parameterDao = new PulsaTrxPascabayarDao(connection);
		List<Criteria> listCriteria = parameterDao.getListParameter();
		Criteria pulsatrxpascabayar = null;
		Date date = new Date();
		
		if (listCriteria != null) {
			pulsatrxpascabayar = listCriteria.get(0);
			pulsatrxpascabayar.setBhsAsing(Double.parseDouble(bhsAsing));
			pulsatrxpascabayar.setDisiplinShalat(Double.parseDouble(disiplinShalat));
			pulsatrxpascabayar.setDoa(Double.parseDouble(doa));
			pulsatrxpascabayar.setMahfudhot(Double.parseDouble(mahfudhot));
			pulsatrxpascabayar.setMuhadarah(Double.parseDouble(muhadarah));
			pulsatrxpascabayar.setPai(Double.parseDouble(pai));
			pulsatrxpascabayar.setPrilaku(Double.parseDouble(prilaku));
			pulsatrxpascabayar.setRapor(Double.parseDouble(rapor));
			pulsatrxpascabayar.setTahfidQuran(Double.parseDouble(tahfidQuran));
			
			pulsatrxpascabayar.setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			pulsatrxpascabayar.setUpdatedDate(date);
			
			parameterDao.update(pulsatrxpascabayar);
		} else {
			pulsatrxpascabayar = new Criteria();
			pulsatrxpascabayar.setBhsAsing(Double.parseDouble(bhsAsing));
			pulsatrxpascabayar.setDisiplinShalat(Double.parseDouble(disiplinShalat));
			pulsatrxpascabayar.setDoa(Double.parseDouble(doa));
			pulsatrxpascabayar.setMahfudhot(Double.parseDouble(mahfudhot));
			pulsatrxpascabayar.setMuhadarah(Double.parseDouble(muhadarah));
			pulsatrxpascabayar.setPai(Double.parseDouble(pai));
			pulsatrxpascabayar.setPrilaku(Double.parseDouble(prilaku));
			pulsatrxpascabayar.setRapor(Double.parseDouble(rapor));
			pulsatrxpascabayar.setTahfidQuran(Double.parseDouble(tahfidQuran));
			
			parameterDao.save(pulsatrxpascabayar);
		}
		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="criteria/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		PulsaTrxPascabayarDao parameterDao = new PulsaTrxPascabayarDao(connection);
		PulsaTrxPascabayar parameter = parameterDao.getParameterById(idParameter);

		if (!parameterDao.update(parameter)) {
			System.out.println("Error database");
		}

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		response.setViewName(page);
		return response;
	}
}
