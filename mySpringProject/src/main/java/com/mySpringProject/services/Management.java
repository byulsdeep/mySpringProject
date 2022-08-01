package com.mySpringProject.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.mySpringProject.beans.AuthB;
import com.mySpringProject.beans.JobB;
import com.mySpringProject.beans.MethodB;
import com.mySpringProject.beans.MoJoB;
import com.mySpringProject.beans.MouB;
import com.mySpringProject.beans.ProjectB;
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
	@Autowired
	private Encryption enc;
	
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
	
	public void backController(int serviceCode, Model model) {
		if(auth.isSession()) {
			System.out.println("Management/backController");
			switch(serviceCode) {
			case 1:
				this.getProjectDetail(model);
				break;
			/*case 2:
				this.getJobList(model);
				break;
			case 3:
				this.getMoJoList(model);
				break;
			case 4:
				this.getMethodList(model);
				break;*/
			}
		}
	}
	
	private void getProjectDetail(Model model) {
		System.out.println("Management/getProjectDetail");
		List<ProjectB> projectDetail = null;
		ProjectB pb = (ProjectB)model.getAttribute("projectB");
		projectDetail = this.session.selectList("getProjectDetail", pb);
		
		for(int i = 0; i < projectDetail.get(0).getMojos().size(); i++) {
			try {
				projectDetail.get(0).getMojos().get(i).setPmbName(enc.aesDecode(projectDetail.get(0).getMojos().get(i).getPmbName(), projectDetail.get(0).getMojos().get(i).getPmbCode()));
			} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
					| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
					| BadPaddingException e) {e.printStackTrace();}
		}
		model.addAttribute("projectDetail", projectDetail);
	}
	/*private void getJobList(Model model) {
		System.out.println("Management/getJobList");
		List<JobB> jobList = null;
		ProjectB pb = (ProjectB)model.getAttribute("projectB");
		jobList = this.session.selectList("getJobList", pb);
		model.addAttribute("jobList", jobList);
	}
	private void getMoJoList(Model model) {
		System.out.println("Management/getMoJoList");
		List<MoJoB> mojoList = null;
		ProjectB pb = (ProjectB)model.getAttribute("projectB");
		mojoList = this.session.selectList("getMoJoList", pb);
		model.addAttribute("mojoList", mojoList);
	}
	private void getMethodList(Model model) {
		System.out.println("Management/getMethodList");
		List<MethodB> methodList = null;
		ProjectB pb = (ProjectB)model.getAttribute("projectB");
		methodList = this.session.selectList("getMethodList", pb);
		model.addAttribute("methodList", methodList);
	}*/
	
	private void mainCtl(ModelAndView mav) {
		System.out.println("Management/mainCtl");
		
		List<ProjectB> hoonList = null;
		AuthB session = null;
		
		try {
			session = (AuthB)this.pu.getAttribute("accessInfo");
		} catch (Exception e) {e.printStackTrace();}
		
		AuthB ab = (AuthB)mav.getModel().get("authB");
		
		hoonList = this.session.selectList("getProjectHoon", session);
		
		
		mav.addObject("hoonList", hoonList);
		mav.setViewName("management");
	}
}
