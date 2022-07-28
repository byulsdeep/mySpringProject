package com.mySpringProject.inter;

import java.util.List;

import com.mySpringProject.beans.AulB;
import com.mySpringProject.beans.AuthB;
import com.mySpringProject.beans.ProListB;
import com.mySpringProject.beans.ProMemB;
import com.mySpringProject.beans.ProjectB;

public interface MapperInter {
	/* auth */
	public String isMemberr(AuthB ab);
	public String isAccess(AuthB ab);
	public int insAsl(AuthB ab);
	public AuthB getAccessInfo(AuthB ab);
	
	/* signup */
	public String getMaxPmbCode();
	public List<AuthB> getLevelList();
	public List<AuthB> getClassList();
	public int regMember(AuthB ab);

	/* project */
	public int insProject(ProjectB pb);
	public List<AuthB> getMemberList(AuthB ab);
	public int insProjectMembers(ProjectB pb);
	
	/* new shit */
	public int insAul(AulB aul);
	public AulB isPrm(AuthB ab);
	
	/* newer shit */
	public AulB receivedInvitationInfo(AuthB ab);
	public AulB sentInvitationInfo(AuthB ab);
	
	/* MYSHIT */
	public String isInvited(AuthB ab);
	public int refusal(ProMemB pm);
	public String getInviteDate(AulB aul);
	public int refusal2(AulB aul);
	public int insSelf(ProMemB pm);
	public List<ProListB> getProjectList(AuthB ab);
	public List<ProListB> getFullProjectList(AuthB ab);
	public String getProjectMembers(ProjectB pb);
	
	public List<ProjectB> getProjectHoon(ProjectB pro);
}
