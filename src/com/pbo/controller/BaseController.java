package com.pbo.controller;

import java.awt.Menu;
import java.util.List;

import javax.persistence.Access;

import org.springframework.web.servlet.ModelAndView;

import com.pbo.util.SessionManager;

public class BaseController {
	
	public static ModelAndView model () {
		SessionManager sessionManager = SessionManager.getInstance();
		ModelAndView model = new ModelAndView();
		/*
		 * Access loggedUser = sessionManager.getLoggedUser();
		 * 
		 * if (loggedUser != null) { List<Menu> listMenu =
		 * sessionManager.getListByAccess(loggedUser.getAccessType());
		 * 
		 * model.addObject("currentUser", loggedUser); model.addObject("listMenu",
		 * listMenu); }
		 */
		
		return model;
	}
}
