<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul class="nav nav-sidebar">
	<li class="active"><a href="#">Main <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="${cp }/allUser">전체사용자</a></li>
	<%-- <li class="active"><a href="<%= request.getContextPath()%>/pagingUser?page=1&pageSize=5">사용자 페이징 리스트</a></li> --%>
	<li class="active"><a href="${cp }/pagingUser">사용자 페이징 리스트</a></li>
	<li class="active"><a href="${cp }/allEmpList">전체직원</a></li>
	<li class="active"><a href="${cp }/registUser">사용자 신규등록</a></li>
	
	<%-- <%@ include file="/user/allUser.jsp" %> --%>
	
</ul>