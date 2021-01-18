<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TimesTableServlet</title>

<style>
	table {
		text-align: center;
		width : 120%;
	}
</style>

</head>
<body>

	표현식, 스클립틀릿을 EL, JSTL로 변경<br>

	<table border="1">
		<% for(int i = 1; i < 10; i++) { %>
		<tr>
			<% for (int j = 2; j < 10; j++) { %>
				<td><%= j + " * " + i + " = " + i*j %></td>		
			<% } %>
		</tr>
		<% } %>
	</table>
	
	<br><br><br>
	
	<table border="1">
		<c:forEach begin="1" end="9" var="i" varStatus="loop">
			<tr>
				<c:forEach begin="2" end="9" var="j" varStatus="loop">
					<td>${j } * ${i } = ${j*i }</td> 
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

</body>
</html>