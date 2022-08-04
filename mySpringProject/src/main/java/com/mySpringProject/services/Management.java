package com.mySpringProject.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import com.mySpringProject.beans.ProMemB;
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
			case 1:
				this.progressMgr(mav);
				break;
			case 2:
				this.resultMgr(mav);
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
			case 14:
				this.getMethodsOnMJ(model);
				break;	
			case 15:
				this.getMethodsOnMJMC(model);
				break;
			case 16:
				this.BF(model);
				break;	
			case 17:
				this.IN(model);
				break;	
			case 18:
				this.CP(model);
				break;	
			}
		}
	}
	
	private void BF(Model model) {
		System.out.println("BACKBF");	
		MethodB mt = (MethodB)model.getAttribute("methodB");
		session.update("BF", mt);
		List<MethodB> list = session.selectList("getMethodList", mt);
		model.addAttribute("methods", list);
	}
	private void IN(Model model) {
		System.out.println("BACKIN");	

		MethodB mt = (MethodB)model.getAttribute("methodB");
		session.update("IN", mt);
		List<MethodB> list = session.selectList("getMethodList", mt);
		model.addAttribute("methods", list);
	}
	private void CP(Model model) {
		System.out.println("BACKCP");	

		MethodB mt = (MethodB)model.getAttribute("methodB");
		session.update("CP", mt);
		List<MethodB> list = session.selectList("getMethodList", mt);
		model.addAttribute("methods", list);
	}
	private void getMethodsOnMJ(Model model) {
		MethodB mt = (MethodB)model.getAttribute("methodB");
		List<MethodB> list = session.selectList("getMethodsOnMJ", mt);
		model.addAttribute("methodsOnMJ", list);
	}
	
	private void getMethodsOnMJMC(Model model) {
		MethodB mt = (MethodB)model.getAttribute("methodB");
		List<MethodB> list = session.selectList("getMethodsOnMJMC", mt);
		model.addAttribute("methodsOnMJMC", list);
	}
	/*private void getProjectDetail(Model model) {
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
	}*/
	
	private void progressMgr(ModelAndView mav) {
		System.out.println("Management/progressMgr");
		mav.setViewName("progress");
		ProjectB pb = (ProjectB)mav.getModel().get("projectB");
		AuthB ab = null;
		try {ab = (AuthB)pu.getAttribute("accessInfo");} catch (Exception e1) {}
		
		List<ProjectB> projectDetail = this.session.selectList("getProjectDetail", pb);	
		List<ProjectB> fullProject = this.session.selectList("getProjectHoon", ab);
		for(int i = 0; i < projectDetail.get(0).getMojos().size(); i++) {
			try {
				projectDetail.get(0).getMojos().get(i).setPmbName(enc.aesDecode(projectDetail.get(0).getMojos().get(i).getPmbName(), projectDetail.get(0).getMojos().get(i).getPmbCode()));
			} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
					| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
					| BadPaddingException e) {e.printStackTrace();}
		}
		mav.addObject("projectDetail", makeProjectList(projectDetail.get(0)));
		mav.addObject("actionName", makeActionList(projectDetail.get(0)));
		//mav.addObject("mvcStyle", makeMvcStyle(projectDetail.get(0)));
		mav.addObject("methodName", makeMethodName(projectDetail.get(0)));
		mav.addObject("projectOptions", makeProjectOptions(fullProject));
	}
	
	private String makeProjectOptions(List<ProjectB> list) {
		StringBuffer sb = new StringBuffer();
		for(ProjectB pb: list) {
			sb.append("<option value=\"" + pb.getProjectCode() + "\"> " + pb.getProjectName() + " </option>");
		}
		return sb.toString();
	}
	
	private String makeMethodName(ProjectB pb) {
		StringBuffer sb = new StringBuffer();
		ArrayList<String> arl = new ArrayList<String>();
		String st;
		int i = 0;
		for(MethodB mt: pb.getMethods()) {		
			//if(!arl.contains(mt.getMethodName())) {
				st = mt.getModuleCode() + ":" + mt.getJobCode() + ":" + mt.getMethodCode() + ":" + mt.getMcCode();
				sb.append("<div class=\"stn button\" \">" + mt.getMethodName() + ""			
						+ "<input id=\"method" + i + "\" type=\"hidden\" value=\"" + st + "\">"
						+ "<div><input class=\"button stn\" type=\"button\" value=\"상태변경\" onClick=\"changeState(" + i + ")\">"
								+ "</div></div>");
				//arl.add(mt.getMethodName());
			//}
			i++;	
		}
		sb.append("<input type=\"hidden\" name=\"ang\" value=\"" + i + "\">");
		return sb.toString();
	}
	/*private String makeMvcStyle(ProjectB pb) {
		StringBuffer sb = new StringBuffer();
		sb.append("		   	<div class=\"btn button\"> CONTROLLER </div>\r\n"
				+ "  		<div class=\"btn button\"> SERVICES </div>\r\n"
				+ "  		<div class=\"btn button\"> DAO </div>\r\n"
				+ "  		<div class=\"btn button\"> VIEW </div>");
		return sb.toString();
	}*/
	private String makeActionList(ProjectB pb) {
		StringBuffer sb = new StringBuffer();
		ArrayList<String> arl = new ArrayList<String>();
		String st;
		int i = 0;
		for(MoJoB mb: pb.getMojos()) {
			
			//if(!arl.contains(mb.getModuleName() + " " + mb.getJobName())) {
				st = mb.getModuleCode() + ":" + mb.getJobCode();
				sb.append("<div onClick=\"getMethodsOnMJ(this)\" class=\"stn button\">" + mb.getModuleName() + " " + mb.getJobName() + ""
						+ "<input id=\"action" + i + "\" type=\"hidden\" value=\"" + st + "\"></div>");
				//arl.add(mb.getModuleName() + " " + mb.getJobName());
			//}
			i++;
		}	
		sb.append("<input type=\"hidden\" name=\"gimotti\" value=\"" + i + "\">");
		return sb.toString();
	}
	
	private String makeProjectList(ProjectB pb) {
		StringBuffer sb = new StringBuffer();
		String st = null;
		sb.append("  			<div id=\"subProjectList\" class=\"null\" value=\"null\">\r\n");	
		for(int i = pb.getProjectMemberss().size() - 1; i >= 0; i--) {
			if(!pb.getProjectMemberss().get(i).getIsAccept().equals("AC")) {
				pb.getProjectMemberss().remove(i);
			}
		}
		sb.append("  				<div id=\"projectThumb0\" class=\"projectThumbOn\" value=\"null\">\r\n"
				+ "  					<div id=\"projectName\" class=\"bigAss\" value=\"null\">"+ pb.getProjectName() +"</div>\r\n"
				+ "  					<div id=\"projectCode0\" class=\"projectCode\" value=\"null\">" + pb.getProjectCode() + "</div>\r\n");		
		sb.append("  					<div id=\"memberCount0\" class=\"memberCount\" value=\"null\">멤버수 :" + pb.getProjectMemberss().size() + "</div>\r\n");
		for(int i = 0; i < pb.getProjectMemberss().size(); i++) {
			try {
				st = ((pb.getProjectMemberss().get(i).getPosition()).equals("MG"))?
								       "<div id=\"managerName0\" class=\"managerName\" value=\"null\">매니저 :" + enc.aesDecode(pb.getProjectMemberss().get(i).getPmbName(), pb.getProjectMemberss().get(i).getPmbCode()) + "</div>\r\n" : null;
			} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
					| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
					| BadPaddingException e) {e.printStackTrace();}
		}
		sb.append(String.valueOf(st));
		sb.append("  					<div id=\"period0\" class=\"period\" value=\"null\">기간: " + pb.getStartDate().substring(0,10) + " ~ " + pb.getEndDate().substring(0,10) + "</div>\r\n"
				+ "  					<div id=\"projectComment0\" class=\"projectComment\" value=\"null\">상세: " + pb.getProjectComment() + "</div>\r\n"
				+ "  					<div id=\"projectComment0\" class=\"projectComment\" value=\"null\">모듈수: " + pb.getModules().size() + "</div>\r\n"
				+ "  					<div id=\"projectComment0\" class=\"projectComment\" value=\"null\">잡수: " + pb.getJobs().size() + "</div>\r\n"
				+ "  					<div id=\"projectComment0\" class=\"projectComment\" value=\"null\">액션수: " + pb.getMojos().size() + "</div>\r\n"
				+ "  					<div id=\"projectComment0\" class=\"projectComment\" value=\"null\">메소드수: " + pb.getMethods().size() + "</div>\r\n"
				+ "  				</div>\r\n");
		sb.append( "			</div>");
		return sb.toString();
	}
	private void resultMgr(ModelAndView mav) {
		System.out.println("Management/resultMgr");
		mav.setViewName("result");
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
