<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
<ul>
	<li class="active"><a href="#">Main <sapn class="sr-only">(current)</sapn></a></li>
	<li class="active"><a href="<%= request.getContextPath()%>/allUser">전체 사용자</a></li>
	
	<!--
		/allUser 요청을 처리할 servlet(controller)
		kr.or.ddit.user.controller.AllUser
			doGet() {
				1. service 객체를 통해 전체 사용자 정보를 조회
				2. request객체에 userList라는 속성명으로 1번에서 조회한 데이터를 설정
				3. webapp/user/allUser.jsp로 응답을 생성하도록 forward
					allUser.jsp는 user.html 참고 하여 생성
					header.jsp, left.jsp를 재활용 하여 생성
					
					user.html 사용자 정보를 표현하는 테이블 태그의 tr 부분을
					request에 저장된 userList 속성 값으로 동적 생성하여
					화면에 출력
			} 
	 -->
	 
	 
</ul>

 --%>
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> <span class="icon-bar"></span> 
						<span class="icon-bar"></span>
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
