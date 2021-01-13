<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Jsp</title>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<%-- <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"> --%>

<%@ include file="/common/common_lib.jsp" %>

<%-- <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script> --%>

<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
</head>

<script>
	// 문서 로딩이 완료되고 나서 실행되는 영역
	$(function() {
		
		$(".user").on("click", function() {
			
			// this : 클릭 이벤트가 발생한 element
			// data-속성명 data-userid, 속성명은 대소문자 무시하고 소문자로 인식
			// data-userId ==> data-userid
			var userid = $(this).data("userid");
			console.log("userid" + userid);
			$("#userid").val(userid);
		})
	})
</script>

<body>
	<form id="frm" action="<%= request.getContextPath()%>/user">
		<input type="hidden" id="userid" name="userid" value="">
	</form>
	
	<%@ include file="/common/header.jsp" %>

	
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JSP/SPRING</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
					
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<%@ include file="/common/left.jsp" %>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
							
		
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">전체사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								<%
								List<UserVo> userList = (List<UserVo>) request.getAttribute("userList");
								for(int i =0; i < userList.size(); i++ ) {
										 UserVo uservo = userList.get(i);
									 %>
								<tr class="user" data-userid="<%= uservo.getUserid()%>">
									<td><%= uservo.getUserid() %></td>
									<td><%= uservo.getUsernm() %></td>
									<td><%= uservo.getAlias() %></td>
									<td><%= uservo.getReg_dt_fmt() %></td>
								</tr>
								 <% } %>
							</table>
						</div>
				
						<a class="btn btn-default pull-right">사용자 등록</a>
				
						<div class="text-center">
							<ul class="pagination">
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
