package com.pbo.controller;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ngalongware.bean.Access;
import com.ngalongware.bean.UserGenerate;
import com.ngalongware.type.Menu;
import com.ngalongware.util.Connection;
import com.ngalongware.util.SessionManager;

public class BaseController {
	
	public static ModelAndView model () {
		SessionManager sessionManager = SessionManager.getInstance();
		ModelAndView model = new ModelAndView();
		Access loggedUser = sessionManager.getLoggedUser();
		
		if (loggedUser != null) {
			List<Menu> listMenu = sessionManager.getListByAccess(loggedUser.getAccessType());
			
			model.addObject("currentUser", loggedUser);
			model.addObject("listMenu", listMenu);
		}
		
		return model;
	}
}
