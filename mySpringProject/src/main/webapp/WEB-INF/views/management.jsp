<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: PMS Management ::</title>
<style>
	@import url("/res/css/common.css");
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet" />
<script src="/res/js/common.js"></script>
<script>
let clientData;
let k = 0;
let projectThumb = [];
let pmbCode = [];

function init() {
	const message = "${message}";
	if(message != "") {
		//alert(message);
	}
	postAjaxJson("GetFullProjectList", null, "callBack3");
}
function callBack3(ajaxData) {
	let projectList = JSON.parse(ajaxData);
	
	if(projectList != null && projectList != "") {	
		createProjectList(projectList);
	}
}
function callBack4(ajaxData) {
	let memberCount = JSON.parse(ajaxData);

	projectThumb[k].insertBefore(createDiv("memberCount" + k, "memberCount", "멤버수 :" + memberCount, null), projectThumb[k].children[2]);
	k++;
}
function createProjectList(projectList) {
	let projectDiv = document.getElementById("projectDiv");
	let subProjectList = createDiv("subProjectList", null, "", null);
	
	let up = createInput("button", "up", null, "btn button", "▲");
	projectDiv.appendChild(up);
	up.addEventListener("click", function(){
		moveUp();
		});
	let down = createInput("button", "down", null, "btn button", "▼");
	projectDiv.appendChild(down);
	down.addEventListener("click", function(){
		moveDown();
		});
	for(i=0; i<projectList.length; i++) {	
		let box = [];
		let projectCode;
		postAjaxJson("GetProjectMembers", "projectCode=" + projectList[i].projectCode, "callBack4");
		
		projectThumb[i] = createDiv("projectThumb" + i, "projectThumbOn", "", null);
		box.push(createDiv("projectName" + i, "bigAss", projectList[i].projectName, null)); 
		box.push(createDiv("managerName" + i, "managerName", "매니저 :" + projectList[i].managerName, null));	
		
		box.push(createDiv("period" + i, "period", "기간: " + projectList[i].startDate.substr(0,10) + " ~ " + projectList[i].endDate.substr(0,10), null));
		box.push(createDiv("projectComment" + i, "projectComment", "상세: " + projectList[i].projectComment, null));
		box.push(createInput("button", "member" + i, null, "btn button", "멤버관리"));
		projectCode = projectList[i].projectCode;
		box[4].addEventListener("click", function(){
			memberMgr(projectCode);
			});
		box.push(createInput("button", "job" + i, null, "btn button", "업무관리"));
		box[5].addEventListener("click", function(){
			moveMgr();
			});
		box.push(createInput("button", "progress" + i, null, "btn button", "결과관리"));
		box[6].addEventListener("click", function(){
			showProgress();
			});	
		
		for(j=0; j<box.length; j++) {		
			projectThumb[i].appendChild(box[j]);
		}	
		projectThumb[i].setAttribute("class", (projectThumb.length >= 4) ? "projectThumbOff": "projectThumbOn");
		subProjectList.appendChild(projectThumb[i]);
	}
	projectDiv.appendChild(subProjectList);
}

function memberMgr(projectCode) {
	postAjaxJson("GetHoonList", "projectCode=" + projectCode, "callBack5");
}

function callBack5(ajaxData) {
	let hoonList = JSON.parse(ajaxData);
	createProjectMemberList(hoonList);
	let eeeee = document.getElementById("eeeee");
	eeeee.innerHTML = "";
}

function callBack6(ajaxData) {
	let emailList = JSON.parse(ajaxData);
	createEmailList(emailList);
}

function sendMoore() {
	postAjaxJson("GetEmailList","", "callBack6");
}

let variable;

function createEmailList(memberList) {
	let newInvite = document.getElementById("newInvite");
	newInvite.innerHTML = "";
	let newThumb = [];
	let box = [];
	
	
	for(i=0; i < memberList.length; i++) {
		if(!pmbCode.includes(memberList[i].pmbCode)) {
			newThumb[i] = createDiv("newThumb" + i, "newThumb", "", null);
			
			variable = newThumb[i];
			
			for(k=0; box.length !=0; k++) {
				box.pop();
			}
			box.push(createDiv("pmbName", null, memberList[i].pmbName, null)); 
			box.push(createDiv("mlvName", null, memberList[i].mlvName, null)); 
			box.push(createDiv("claName", null, memberList[i].claName, null)); 
			box.push(createDiv("email", null, memberList[i].email, null)); 
			box.push(createDiv(null, null, null, null));
			
			box[4].innerHTML = "<input type='button' name='sendButton' placeholder='null' class='btn button' value='이메일 전송' onClick='sendRight(" + variable + ")'>";
			
			for(j=0; j<box.length; j++) {		
				newThumb[i].appendChild(box[j]);
			}			
			/*box[4].addEventListener("click", function(){
				alert(i);
				sendRight(i);
				});*/
		}
		if(newThumb[i] != null) {
			newInvite.appendChild(newThumb[i]);
		}
	}
	
	for(i=0; pmbCode.length !=0; i++) {
		pmbCode.pop();
	}
}

function sendRight(html) {
	alert(html);
	//let eeeee = document.getElementById("eeeee");
	eeeee.appendChild(html);
	//alert(right);
}
function createProjectMemberList(hoonList) {
	let memberDiv = document.getElementById("inviteDiv");
	memberDiv.innerHTML = "";
	let memberThumb = [];
	let box = [];
	
	for(i=0; i < hoonList.length; i++) {
		
		(hoonList[i].isAccept == "AC") 
		? memberThumb[i] = createDiv("memberThumb" + i, "memberThumbAC", "", null)
		: memberThumb[i] = createDiv("memberThumb" + i, "memberThumb", "", null);
		
		variable = memberThumb[i];
		
		for(k=0; box.length !=0; k++) {
			box.pop();
		}
		pmbCode.push(hoonList[i].pmbCode);
		box.push(createDiv("pmbName", null, hoonList[i].pmbName, null)); 
		box.push(createDiv("position", null, (hoonList[i].position == "MG") ? "매니저" : "멤버", null));
		box.push(createDiv("mlvName", null, hoonList[i].mlvName, null));
		box.push(createDiv("claName", null, hoonList[i].claName, null));
		box.push(createDiv("email", null, hoonList[i].email, null));
		box.push(createDiv("isAccept", null, (hoonList[i].isAccept == "AC") ? "수락완료" : "미수락", null));
		box.push(createDiv(null, null, null, null));

		
		
		if(hoonList[i].isAccept != "AC") {
			box[6].innerHTML = "<input type='button' name='sendButton' placeholder='null' class='btn button' value='이메일 (재)전송' onClick='sendRight(" + variable + ")'>";

			//box.push(createInput("button", "sendButton" , null, "btn button", "이메일 (재)전송"));

		}
		for(j=0; j<box.length; j++) {		
			memberThumb[i].appendChild(box[j]);
		}	
		
		/*if(box[6] != null) {
			box[6].addEventListener("click", function(){
				alert(i);
				sendRight(i);
				});
		}*/

		memberDiv.appendChild(memberThumb[i]);
	}
	
	let newInvite = document.getElementById("newInvite");
	newInvite.innerHTML = "";
	sendMore = createInput("button", "sendMore" , null, "btn button", "추가 이메일 전송");
	newInvite.appendChild(sendMore);
	sendMore.addEventListener("click", function(){
		sendMoore();
		});
	
	let memberThumbAC = document.getElementsByClassName("memberThumbAC");
	
	for(i=0; i < memberThumbAC.length; i++) {
		memberDiv.insertBefore(memberThumbAC[i], memberDiv.children[0]);
	}
}
function showProgress() {
	let progressBar = document.getElementById("progressBar");
	progressBar.innerHTML = "<img src='res/images/radialProgressBar.png'></img>";
}
function moveUp() {
	if(projectThumb.length >= 4) {
		let a = projectThumb[projectThumb.length-1];	
		projectThumb.splice(projectThumb.length-1,1);	
		projectThumb.unshift(a);
		for(i=0; i<projectThumb.length; i++) {	
			projectThumb[i].setAttribute("class", (i >= 3) ? "projectThumbOff": "projectThumbOn");
		}		
	}
}
function moveDown() {
	if(projectThumb.length >= 4) {
		
		let a = projectThumb[0];
		projectThumb.splice(0,1);		
		projectThumb.push(a);
		
		for(i=0; i<projectThumb.length; i++) {	
			projectThumb[i].setAttribute("class", (i >= 3) ? "projectThumbOff": "projectThumbOn");
		}
	}
}
/* 멤버관리 

멤버 insert/update

st도 띄우다

인증만료 - 이메일 재발송 버튼
새로운 사람에게 이메일 발송 기능 (AC 제외)

PROJECT CLASS 에서 작업 */
</script>
</head>
<body onLoad="init()">
<br><br><br>
<section>  
  <article>
  	<div>
  		<div id="projectDiv">
  		</div>
  	</div>
  	<div>
  		<div id="inviteDiv">
  
  		</div>
  	</div>
  	<div>
  		<div id="newInvite">
  
  		</div>
  	</div>
  	<div>
  		<div id="eeeee">			
  		</div>
  	</div>  	
  	<div>
  		<div id="progressBar">			
  		</div>
  	</div>    	
  </article>
</section>
<footer>
  <p>공지사항</p>
</footer>
<nav>
  <ul>
    <br>
    <li><i id="default" class="fa-solid fa-chalkboard-user" onClick="moveMain()" ></i></li>
    <br>
    <li><i id="default" class="fa-solid fa-bell" onClick="moveAlert()" ></i></li>
    <br>
    <li><i id="highlight" class="fa-solid fa-wrench" onClick="moveMgr()" ></i></li>
    <br>
    <li><i id="default" class="fa-solid fa-user" onClick="moveMyPage()" ></i></li>
  </ul>
</nav>
<header>
  <span>
	${accessInfo.pmbName}(${accessInfo.mlvName})님 어서오세요&nbsp&nbsp&nbsp&nbsp소속: [${accessInfo.claName}]&nbsp&nbsp&nbsp&nbsp접속시간: [${accessInfo.date}]
	<input type="button" name="accessOut" value="로그아웃" onClick="logOut()" />
  </span>
</header>
<i id="newProject" class="fa-solid fa-folder-plus" onClick="moveProject()" ></i>
<form name="form" method="post"></form>	
</body>
</html>