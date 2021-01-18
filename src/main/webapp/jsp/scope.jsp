<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	응답
	
	<form action="${pageContext.request.contextPath}/scope" method="post">
		<!-- <input type="text" name="request"><br>
		<input type="text" name="session"><br>
		<input type="text" name="application"><br> -->
		<input type="text" name="scope"><br>
		<input type="submit" value="전송">
	</form>
	
	<%= request.getAttribute("request") %><br>
	<%= session.getAttribute("session") %><br>
	<%= application.getAttribute("application")%><br>

</body>
</html>