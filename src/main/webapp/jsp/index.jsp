<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	request.getContentType() : <%= request.getContentType() %><br>
	request.getMethod() : <%= request.getMethod() %><br>
	request.getContextPath() : <%= request.getContextPath() %><br>
	request.getServerPort() : <%= request.getServerPort() %><br>
		
	
	webapp/index.jsp
	
	webapp/image/brown.png 
	
	index.jsp
	
	<!-- <img src="/jsp/image/brown.png"> -->
	<img src="<%= request.getContextPath() %>/image/brown.png">

</body>
</html>