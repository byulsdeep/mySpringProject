package com.mySpringProject.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mySpringProject.beans.AulB;
import com.mySpringProject.beans.AuthB;
import com.mySpringProject.beans.ProListB;
import com.mySpringProject.beans.ProMemB;
import com.mySpringProject.beans.ProjectB;
import com.mySpringProject.services.Main;
import com.mySpringProject.services.Project;

@RestController
public class APIController {
	@Autowired
	private Project pro;
	@Autowired
	private Main main;
	
	@PostMapping("/InsProject")
	public List<AuthB> insProject(Model model, @ModelAttribute ProjectB pb) {
		System.out.println("InsProject");	
		System.out.println(pb.getProjectCode());
		System.out.println(pb.getProjectName());
		System.out.println(pb.getProjectComment());
		System.out.println(pb.getStartDate());
		System.out.println(pb.getEndDate());
		System.out.println(pb.getIsVisible());
		model.addAttribute(pb);	
		this.pro.backController(1, model);
		
		return (List<AuthB>)model.getAttribute("MemberList");
	}	
	@PostMapping("/GetEmailList")
	public List<AuthB> getEmailList(Model model) {
		System.out.println("GetEmailList");	

		this.pro.backController(5, model);
		
		return (List<AuthB>)model.getAttribute("EmailList");
	}	

	@RequestMapping(value = "/IsInvited", method = RequestMethod.POST)
	public List<AulB> isInvited(Model model, @ModelAttribute AuthB ab) {
		System.out.println("IsInvited");
		model.addAttribute(ab);
		
		this.main.backController(1, model);
		
		return (List<AulB>)model.getAttribute("InviteList");
	}
	
	@RequestMapping(value = "/IsInvited2", method = RequestMethod.POST)
	public List<AulB> isInvited2(Model model, @ModelAttribute AuthB ab) {
		System.out.println("IsInvited");
		model.addAttribute(ab);
		
		this.main.backController(2, model);
		
		return (List<AulB>)model.getAttribute("SentList");
	}
	
	@RequestMapping(value = "/GetProjectList", method = RequestMethod.POST)
	public List<ProListB> getProjectList(Model model, @ModelAttribute AuthB ab) {
		System.out.println("GetProjectList");
		model.addAttribute(ab);
		
		this.main.backController(3, model);
		
		return (List<ProListB>)model.getAttribute("ProjectList");
	}
	@RequestMapping(value = "/GetFullProjectList", method = RequestMethod.POST)
	public List<ProListB> getFullProjectList(Model model, @ModelAttribute AuthB ab) {
		System.out.println("GetFullProjectList");
		model.addAttribute(ab);
		
		this.main.backController(5, model);
		
		return (List<ProListB>)model.getAttribute("ProjectList");
	}
	
	@RequestMapping(value = "/GetProjectMembers", method = RequestMethod.POST)
	public String getProjectMembers(Model model, @ModelAttribute ProjectB pb) {
		System.out.println("GetProjectMembers");
		model.addAttribute(pb);
		System.out.println(pb.getProjectCode());
		this.main.backController(4, model);
		
		return (String) model.getAttribute("ProjectMembers");
	}
	
	@RequestMapping(value = "/GetHoonList", method = RequestMethod.POST)
	public List<ProMemB> getHoonList(Model model, @ModelAttribute ProjectB pb) {
		System.out.println("GetHoonList");
		System.out.println(pb.getProjectCode());
		model.addAttribute(pb);
		this.pro.backController(4, model);
		
		return (List<ProMemB>)model.getAttribute("hoonList");
	}
}
