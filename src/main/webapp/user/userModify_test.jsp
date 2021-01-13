<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file="/common/common_lib.jsp" %>

<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
</head>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>

	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과
			
			$("#addr1").val(data.roadAddress);	// 도로주소
			$("#zipcode").val(data.zonecode);	//  우편번호
		}
	}).open();

</script>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
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
			
				<form class="form-horizontal" role="form" action="<%= request.getContextPath()%>/userModify">
					
					<!-- <input type="hidden"> -->
					<% UserVo uservo = (UserVo) request.getAttribute("user"); %>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userId" name="userId"
								placeholder="사용자 아이디" value="<%= uservo.getUserid() %>">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<label class="control-label"><%= uservo.getUserid() %></label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userNm" name="userNm"
								placeholder="사용자 이름" value="<%= uservo.getUsernm() %>">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userAlias"
								name="userAlias" placeholder="별명" value="<%= uservo.getAlias() %>">
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass"
								placeholder="Password" value="<%= uservo.getPass() %>">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<input type="reg" class="form-control" id="reg" name="reg"
								placeholder="등록일시" value="<%= uservo.getReg_dt_fmt() %>">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-10">
							<input type="addr1" class="form-control" id="addr1" name="addr1"
								placeholder="도로주소" value="<%= uservo.getAddr1() %>">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="addr2" class="form-control" id="addr2" name="addr2"
								placeholder="상세주소" value="<%= uservo.getAddr2() %>">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input type="zipcode" class="form-control" id="zipcode" name="zipcode"
								placeholder="우편번호" value="<%= uservo.getZipcode() %>">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">사용자 수정</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
