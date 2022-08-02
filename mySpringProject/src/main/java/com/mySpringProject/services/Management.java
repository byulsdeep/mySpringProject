package com.mySpringProject.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
			case 2:
				this.updModule(model);
				break;
			case 3:
				this.delModule(model);
				break;
			case 4:
				this.insModule(model);
				break;
			case 5:
				this.updJob(model);
				break;
			case 6:
				this.delJob(model);
				break;
			case 7:
				this.insJob(model);
				break;
			case 8:
				this.updMoJo(model);
				break;
			case 9:
				this.deleteMoJo(model);
				break;
			case 10:
				this.insMoJo(model);
				break;
			case 11:
				this.updMethod(model);
				break;
			case 12:
				this.delMethod(model);
				break;
			case 13:
				this.insMethod(model);
				break;
			}
		}
	}
	
	private void updModule(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MouB mb = (MouB)model.getAttribute("mouB");
		this.session.update("updModule", mb);
		map.put("projectCode", mb.getProjectCode());
		List<MouB> moduleList = this.session.selectList("getModuleList", map);
		model.addAttribute("moduleList", moduleList);
	}
	private void delModule(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MouB mb = (MouB)model.getAttribute("mouB");
		this.session.update("delModule", mb);
		map.put("projectCode", mb.getProjectCode());
		List<MouB> moduleList = this.session.selectList("getModuleList", map);
		model.addAttribute("moduleList", moduleList);
	}
	private void insModule(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MouB mb = (MouB)model.getAttribute("mouB");
		this.session.update("insModule", mb);
		map.put("projectCode", mb.getProjectCode());
		List<MouB> moduleList = this.session.selectList("getModuleList", map);
		model.addAttribute("moduleList", moduleList);
	}
	private void updJob(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		JobB jb = (JobB)model.getAttribute("jobB");
		this.session.update("updJobs", jb);
		map.put("projectCode", jb.getProjectCode());
		List<MouB> jobList = this.session.selectList("getJobList", map);
		model.addAttribute("jobList", jobList);
	}
	private void delJob(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		JobB jb = (JobB)model.getAttribute("jobB");
		this.session.update("delJobs", jb);
		map.put("projectCode", jb.getProjectCode());
		List<MouB> jobList = this.session.selectList("getJobList", map);
		model.addAttribute("jobList", jobList);
	}
	private void insJob(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		JobB jb = (JobB)model.getAttribute("jobB");
		this.session.update("insJobs", jb);
		map.put("projectCode", jb.getProjectCode());
		List<MouB> jobList = this.session.selectList("getJobList", map);
		model.addAttribute("jobList", jobList);
	}
	private void updMoJo(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MoJoB mj = (MoJoB)model.getAttribute("moJoB");
		this.session.update("updMoJo", mj);
		map.put("projectCode", mj.getProjectCode());
		List<MoJoB> mojoList = this.session.selectList("getMoJoList", map);
		model.addAttribute("mojoList", mojoList);
	}
	private void deleteMoJo(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MoJoB mj = (MoJoB)model.getAttribute("moJoB");
		this.session.update("delMoJo", mj);
		map.put("projectCode", mj.getProjectCode());
		List<MoJoB> mojoList = this.session.selectList("getMoJoList", map);
		model.addAttribute("mojoList", mojoList);
	}
	private void insMoJo(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MoJoB mj = (MoJoB)model.getAttribute("moJoB");
		this.session.update("insMoJo", mj);
		map.put("projectCode", mj.getProjectCode());
		List<MoJoB> mojoList = this.session.selectList("getMoJoList", map);
		model.addAttribute("mojoList", mojoList);
	}
	private void updMethod(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MethodB mt = (MethodB)model.getAttribute("methodB");
		this.session.update("updMethods", mt);
		map.put("projectCode", mt.getProjectCode());
		List<MethodB> methodList = this.session.selectList("getMethodList", map);
		model.addAttribute("methodList", methodList);
	}
	private void delMethod(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MethodB mt = (MethodB)model.getAttribute("methodB");
		this.session.update("delMethods", mt);
		map.put("projectCode", mt.getProjectCode());
		List<MethodB> methodList = this.session.selectList("getMethodList", map);
		model.addAttribute("methodList", methodList);
	}
	private void insMethod(Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		MethodB mt = (MethodB)model.getAttribute("methodB");
		this.session.update("insMethods", mt);
		map.put("projectCode", mt.getProjectCode());
		List<MethodB> methodList = this.session.selectList("getMethodList", map);
		model.addAttribute("methodList", methodList);
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
	
	private void temp(ModelAndView mav) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("projectCode", ((ProjectB)mav.getModel().get("projectB")).getProjectCode());
	}
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
