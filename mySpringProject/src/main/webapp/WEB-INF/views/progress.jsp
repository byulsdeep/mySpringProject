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
function init(){
	const message = "${message}";
	if(message != "") alert(message);

}



</script>
</head>
<body onLoad="init()">
<br><br><br>
<section>  
  <article>
   	<div>
		<div id="projectDiv">
  	  <!--  <input type="button" name="up" placeholder="null" class="btn button" value="▲">
  			<input type="button" name="down" placeholder="null" class="btn button" value="▼">
  			<div id="subProjectList" class="null" value="null">
  				<div id="projectThumb0" class="projectThumbOn" value="null">
  					<div id="projectName0" class="bigAss" value="null">taehoon test</div>
  					<div id="projectCode0" class="projectCode" value="null">22072914170220220708</div>
  					<div id="memberCount0" class="memberCount" value="null">멤버수 :2</div>
  					<div id="managerName0" class="managerName" value="null">매니저 :태훈</div>
  					<div id="period0" class="period" value="null">기간: 2022-07-29 ~ 2022-07-29</div>
  					<div id="projectComment0" class="projectComment" value="null">상세: test</div>
  					<input type="button" name="member0" placeholder="null" class="stn button" value="멤버관리">
  					<input type="button" name="job0" placeholder="null" class="stn button" value="업무관리">
  					<input type="button" name="progress0" placeholder="null" class="stn button" value="결과관리">
  					<input type="button" name="progress0" placeholder="null" class="stn button" value="진행도관리">
  				</div>
			</div> -->
			${projectDetail}
		</div>
  	</div>
  	<div>
  		<div id="inviteDiv">	
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  			<div class="stn button"> ACTION NAME </div>
  		</div>
  	</div>
  	<div>
  		<div id="newInvite">
  			<div class="btn button"> CONTROLLER </div>
  			<div class="btn button"> SERVICES </div>
  			<div class="btn button"> DAO </div>
  			<div class="btn button"> VIEW </div>
  		</div>
  	</div>
  	<div>
  		<div id="eeeee">
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>
  			<div class="stn button"> method </div>			
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