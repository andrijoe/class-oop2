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
public class BpjsPendaftaran extends BaseController {
	@RequestMapping(value="Bpjs", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView response = BaseController.model();
		response = initParameter(response);
		String page = "/bpjs";

		response.setViewName(page);
		return response;
	}

	public ModelAndView initParameter(ModelAndView response) {
		Connection connection = Connection.getInstance();
		BpjsPendaftaranDao parameterDao = new BpjsTrxHistoryDao(connection);
		List <Bpjs> listParameter = parameterDao.getListParameter();
		response.addObject("Bpjs", listParameter.get(0));
		return response;
	}

	@RequestMapping(value="Bpjs/form", method = RequestMethod.GET)
	public ModelAndView form(String idParameter) {
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_FORM;
		page = Helper.loggedCheck(page);

		if (idParameter != null && !idParameter.isEmpty()) {
			BpjsPendaftaranDao parameterDao = new BpjsPendaftaranDao(connection);
			BpjsPendaftaranController parameter = parameterDao.getParameterById(idParameter);
			response.addObject("parameter", parameter);
		}

		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Bpjs/save_update", method = RequestMethod.POST)
	public ModelAndView insertUpdate(long id_trx, String tgl_trx, boolean isvalid) {
		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		String page = Constant.CRITERIA_INDEX;
		page = Helper.loggedCheck(page);
		BpjsPendaftaranDao parameterDao = new BpjsPendaftaranDao(connection);
		List<Bpjs> listCostumer = parameterDao.getListParameter();
		Bpjs BpjsPendaftaran  = null;
		Date date = new Date();
		
		if (listBpjsPendaftaran != null) {
			BpjsPendaftaran  = listBpjsPendaftaran.get(0);
			BpjsPendaftaran .setId(id);
			BpjsPendaftaran .setId_Costumer(Id_Costumer);
			BpjsPendaftaran .setJumlah_Anggota(jumlah_anggota);
			BpjsPendaftaran .setKelas(kelas);
			BpjsPendaftaran .setTgl_Daftar(Tgl_Daftar);
			BpjsPendaftaran .setNo_Daftar(no_daftar);
			
			BpjsPendaftaran .setUpdatedAccess(sessionManager.getLoggedUser().getIdAccess());
			BpjsPendaftaran .setUpdatedDate(date);
			
			parameterDao.update(BpjsPendaftaran );
		} else {
			BpjsPendaftaran  = new Bpjs();
			BpjsPendaftaran .setId(id);
			BpjsPendaftaran .setId_Costumer(Id_Costumer);
			BpjsPendaftaran .setJumlah_Anggota(jumlah_anggota);
			BpjsPendaftaran .setKelas(kelas);
			BpjsPendaftaran .setTgl_Daftar(Tgl_Daftar);
			BpjsPendaftaran .setNo_Daftar(no_daftar);

			
			parameterDao.save(BpjsPendaftaran);
		}
		
		response = initParameter(response);
		response.setViewName(page);
		return response;
	}

	@RequestMapping(value="Bpjs/delete", method = RequestMethod.GET)
	public ModelAndView delete(String idParameter) {
//		SessionManager sessionManager = SessionManager.getInstance();
		Connection connection = Connection.getInstance();
		ModelAndView response = BaseController.model();
		response = initParameter(response);

		BpjsPendaftaranDao parameterDao = new BpjsPendaftaranDao(connection);
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

