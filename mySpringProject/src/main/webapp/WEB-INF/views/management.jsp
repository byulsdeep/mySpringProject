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
const hoonList = "${hoonList}";
function init(){
	const message = "${message}";
	if(message != "") alert(message);
	createProjectList();
}

function createProjectList() {
	
	let projectDiv = document.getElementById("projectDiv2");
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
	
	alert(hoonList.length);
	alert(hoonList.size);
	alert(hoonList[0].projectName);
	
	for(i=0; i< hoonList.length; i++) {	
		let box = [];
		
		projectThumb[i] = createDiv("projectThumb" + i, "projectThumbOn", "", null);
		box.push(createDiv("projectName" + i, "bigAss", hoonList[i].projectName, null)); 
		box.push(createDiv("period" + i, "period", "기간: " + hoonList[i].startDate.substr(0,10) + " ~ " + hoonList[i].endDate.substr(0,10), null));
		box.push(createDiv("projectComment" + i, "projectComment", "상세: " + hoonList[i].projectComment, null));
		for(j=0; j<box.length; j++) {		
			projectThumb[i].appendChild(box[j]);
		}	
		projectThumb[i].setAttribute("class", (projectThumb.length >= 4) ? "projectThumbOff": "projectThumbOn");
		subProjectList.appendChild(projectThumb[i]);
	}
	projectDiv.appendChild(subProjectList);
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
  		<div id="projectDiv2">
  		  ${hoonList}
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