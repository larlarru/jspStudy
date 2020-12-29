<%@page import="kr.or.ddit.servlet.basic.Factorial"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jsp 메소드, 변수 선언부 -->

<%!
	// 메소드 선언
	private int cal(int n) {
		
		
		if(n <= 1)
			return 1;
		else
			return n * cal(--n);
		
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	1! : <%= cal(1) %><br>
	2! : <%= cal(2) %><br>
	3! : <%= cal(3) %><br>
	4! : <%= cal(4) %><br>
	5! : <%= cal(5) %><br>


</body>
</html>