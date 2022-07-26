package com.mySpringProject.services;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.mySpringProject.inter.ServicesRule;
import com.mySpringProject.utils.Encryption;
import com.mySpringProject.utils.ProjectUtils;

@Service
public class Management implements ServicesRule{
	
	@Autowired
	private SqlSessionTemplate session;
	@Autowired 
	private ProjectUtils pu;
	@Autowired
	private Authentication auth;
	
	public Management() {}

	public void backController(int serviceCode, ModelAndView mav) {
		if(auth.isSession()) {
		System.out.println("Management/backController");
			switch(serviceCode) {
			case 0:
				this.mainCtl(mav);
				break;
			}
		} else {
			mav.setViewName("home");
		}
	}
	
	public void backController(int serviceCode, Model model) {}
	
	private void mainCtl(ModelAndView mav) {
		System.out.println("Management/mainCtl");
		String page = "management";
		mav.setViewName(page);
	}
}
