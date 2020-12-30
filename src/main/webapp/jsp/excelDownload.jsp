<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// Content-Doisposition header : 파일 다운로드, 업로드시 사용하는 파이로가 관련된 헤더
	response.setHeader("Content-Disposition", "attachement; filename=excel.xls");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>userid</th>
			<th>이름</th>
		</tr>
		<tr>
			<td>brwon</td>
			<td>브라운</td>
		</tr>
		<tr>
			<td>sally</td>
			<td>셀리</td>
		</tr>
		<tr>
			<td>cony</td>
			<td>코니</td>
		</tr>
	</table>

</body>
</html>