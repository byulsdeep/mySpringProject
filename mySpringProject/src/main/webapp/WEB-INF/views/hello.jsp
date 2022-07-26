<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증코드 입력</title>
<script>
	function moveMain() {
		let form = document.getElementsByName("serverCall")[0];
		
		form.action="Accept";
		form.method="post";
		
	}
</script>
</head>
<body>
	<form name="serverCall">
		<input type="text" name="code" placeholder="인증키입력"/>
		<input type="button" value="확인" onclick="moveMain()"/>
	</form>
</body>
</html>