<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul class="nav nav-sidebar">
	<li class="active"><a href="#">Main <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="${cp }/user/allUser">전체사용자</a></li>
	<li class="active"><a href="${cp }/user/allUserTiles">전체사용자(타일즈)</a></li>
	
	<li class="active"><a href="${cp }/user/pagingUser">사용자 페이징 리스트</a></li>
	
	<li class="active"><a href="${cp }/user/registUserPage">사용자 신규등록</a></li>
	<li class="active"><a href="${cp }/user/registTiles">사용자 신규등록(타일즈)</a></li>
	
	<li class="active"><a href="${cp }/user/pagingUserTiles">사용자 페이징리스트(타일즈)</a></li>
	<li class="active"><a href="${cp }/user/pagingUserAjaxView">사용자 페이징리스트(ajaxs)</a></li>
	
	<%-- <%@ include file="/user/allUser.jsp" %> --%>
	
</ul>