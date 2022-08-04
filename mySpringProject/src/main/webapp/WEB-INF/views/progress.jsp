<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: PMS Progress ::</title>
<style>
	@import url("/res/css/common.css");
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet" />
<script src="/res/js/common.js"></script>
<script>
let projectCode;

function init(){
	const message = "${message}";
	if(message != "") alert(message);
	projectCode = document.getElementById("projectCode0").innerText;
}
function changeProject() {
	let form = document.getElementsByName("form")[0];
	const abc = document.getElementsByName("projectCode")[0];
	form.appendChild(abc);
	form.action = "ProgressMgr";
	form.submit();
}
function getMethodsOnMJMC(mc) {
	let arr = [];
	let ang = document.getElementsByName("ang")[0].value;    
	for(i = 0; i < ang; i++) {
		arr.push(document.getElementById("method" + i));
	}
	clientData = "";
	clientData += "projectCode=" + projectCode;
	for(j = 0; j < arr.length; j++) {
		clientData += "&moduleCode=" + arr[j].value.split(":")[0] + "&jobCode=" + arr[j].value.split(":")[1] + "&mcCode=" + mc;
	}
	postAjaxJson("GetMethodsOnMJMC", clientData, "gotMethods");
}
function getMethodsOnMJ(obj) {
	let hoe = obj.children[0].value;
	let arr = hoe.split(":");
	let gimotti = document.getElementsByName("gimotti")[0].value;       
	
	clientData = "";
	clientData += "projectCode=" + projectCode;
	clientData += "&moduleCode=" + arr[0] + "&jobCode=" + arr[1];

	postAjaxJson("GetMethodsOnMJ", clientData, "gotMethods");
}
function gotMethods(ajaxData) {
	let methods = JSON.parse(ajaxData);
	createMethods(methods);
}
function createMethods(methods) {
	let mainDiv = document.getElementById("eeeee");
	mainDiv.innerHTML = "";
	let subDiv = [];
	let input = [];
	let button = [];
	if(methods != null && methods != "") {
		for(i = 0; i < methods.length; i++) {
			subDiv[i] = createDiv("", "stn button", methods[i].methodName, "");
			button[i] = createDiv(null, null, "<input class=\"button stn\" type=\"button\" value=\"상태변경\" onClick=\"changeState(" + i + ")\">", null);
	
			input[i] = createInput("hidden", "", "", "", methods[i].moduleCode + ":" + methods[i].jobCode + ":" + methods[i].methodCode + ":" + methods[i].mcCode, null);
			input[i].setAttribute("id", "method" +i);
			subDiv[i].appendChild(input[i]);
			subDiv[i].appendChild(button[i]);
			mainDiv.appendChild(subDiv[i]);
		}
		let newAng = createInput("hidden", "ang", "", "", methods.length, null);
		mainDiv.appendChild(newAng);
	} else mainDiv.innerHTML = "NULL";
}
function changeState(num) {
	lightBoxCtl('상태변경', true);
	let cbody = document.getElementById("cbody");
	cbody.innerHTML = "";
	let box = [];
	box.push(createInput("button", "button", null, "btn button", "개발전", null));	
	box[0].addEventListener("click", function(){
		changeBF(num);
		});
	box.push(createInput("button", "button", null, "btn button", "개발진행중", null));	
	box[1].addEventListener("click", function(){
		changeIN(num);
		});
	box.push(createInput("button", "button", null, "btn button", "개발완료", null));	
	box[2].addEventListener("click", function(){
		changeCP(num);
		});
	box.push(createInput("button", "button", null, "btn button", "취소", null));	
	box[3].addEventListener("click", function(){
		closeCanvas();
		});
	for(i=0; i<box.length; i++) {
		cbody.appendChild(box[i]);
	}
}	
function changeBF(num) {
	let method = document.getElementById("method" + num);
	let hoe = method.value;
	let arr = hoe.split(":");
	clientData = "";
	clientData += "projectCode=" + projectCode;
	clientData += "&moduleCode=" + arr[0] + "&jobCode=" + arr[1];
	clientData += "&methodCode=" + arr[2] + "&mcCode=" + arr[3];
	clientData += "&methodState=" + "BF";
	alert(clientData);
	postAjaxJson("BF", clientData, "gotMethods");
	closeCanvas();
}
function changeIN(num) {
	let method = document.getElementById("method" + num);
	let hoe = method.value;
	let arr = hoe.split(":");
	clientData = "";
	clientData += "projectCode=" + projectCode;
	clientData += "&moduleCode=" + arr[0] + "&jobCode=" + arr[1];
	clientData += "&methodCode=" + arr[2] + "&mcCode=" + arr[3];
	clientData += "&methodState=" + "IN";
	postAjaxJson("IN", clientData, "gotMethods");
	closeCanvas();
}
function changeCP(num) {
	let method = document.getElementById("method" + num);
	let hoe = method.value;
	let arr = hoe.split(":");
	clientData = "";
	clientData += "projectCode=" + projectCode;
	clientData += "&moduleCode=" + arr[0] + "&jobCode=" + arr[1];
	clientData += "&methodCode=" + arr[2] + "&mcCode=" + arr[3];
	clientData += "&methodState=" + "CP";	
	postAjaxJson("CP", clientData, "gotMethods");
	closeCanvas();
}
</script>
</head>
<body onLoad="init()">
<br><br><br>
<section>  
  <article>
   	<div>
		<div id="projectDiv">
			<div class="projectThumbOn">
				<select name="projectCode">
					${projectOptions}
				</select>
				<button onClick="changeProject()" class="btn button">선택</button>
			</div>
			${projectDetail}
		</div>
  	</div>
  	<div>
  		<div id="inviteDiv">	
  		${actionName}
  		</div>
  	</div>
  	<div>
  		<div id="newInvite">
			<div class="btn button" onClick="getMethodsOnMJMC('CT')"> CONTROLLER </div>
			<div class="btn button" onClick="getMethodsOnMJMC('MO')"> SERVICES </div>
			<div class="btn button" onClick="getMethodsOnMJMC('RD')"> DAO </div>
			<div class="btn button" onClick="getMethodsOnMJMC('VI')"> VIEW </div>
  		</div>
  	</div>
  	<div>
  		<div id="eeeee">
  		${methodName}		
  		</div>
  	</div>  	
  	<div>
  		<div id="fffff">	
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
    <li><i id="default" class="fa-solid fa-wrench" onClick="moveMgr()" ></i></li>
    <br>
    <li><i id="highlight" class="fa-solid fa-user" onClick="moveMyPage()" ></i></li>
  </ul>
</nav>
<header>
  <span>
	${accessInfo.pmbName}(${accessInfo.mlvName})님 어서오세요&nbsp&nbsp&nbsp&nbsp소속: [${accessInfo.claName}]&nbsp&nbsp&nbsp&nbsp접속시간: [${accessInfo.date}]
	<input type="button" name="accessOut" value="로그아웃" onClick="logOut()" />
  </span>
</header>
<i id="newProject" class="fa-solid fa-folder-plus" onClick="moveProject()" ></i>
<!-- Light Box Start -->
	<div id="canvas" class="canvas">
		<div id="light-box" class="light-box">
			<div id="image-zone" class="lightbox image"></div>
			<div id="content-zone" class="lightbox content">
				<div id="cheader"></div>
				<div id="cbody"></div>			
				<div id="cfoot"></div>
			</div>
		</div>	
	</div>
<!-- Light Box End -->
<form name="form" method="post"></form>	
</body>
</html>