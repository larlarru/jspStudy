<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<script>
	// 문서 로딩이 완료되고 나서 실행되는 영역
	$(function() {
		
		pagingUserAjax(1,5);
		
		$("#userTbody").on("click", ".user", function() {
			
			// this : 클릭 이벤트가 발생한 element
			// data-속성명 data-userid, 속성명은 대소문자 무시하고 소문자로 인식
			// data-userId ==> data-userid
			var userid = $(this).data("userid");
			console.log("userid : " + userid);
			/* alert($(this).data("userid")); */
			$("#userid").val(userid);
			$("#frm").submit();
			
		})
		
		$("#userRegist").on("click", function() {
			location.href="${cp }/user/registUserPage";
		})
	})
	
	function pagingUserAjax(page, pageSize) {
		// ajax를 통해 사용자 리스트를 가져온다. 1page, 5pageSize
		$.ajax({
			
// 			url:"${cp }/user/pagingUserAjax",
			url:"${cp }/user/pagingUserAjaxHtml",
			data : "page="+page+"&pageSize="+pageSize,
// 			data : "page=1&pageSize=5",
			success : function(data) {
				
				console.log(data);
// 				var html = "";
// 				$.each(data.userList, function(i, user){
						
// 						html += "<tr class='user' data-userid='"+user.userid+"'>";
// 						html += "<td>"+user.userid+"</td>";
// 						html += "<td>"+user.usernm+"</td>";
// 						html += "<td>"+user.alias+"</td>";
// 						html += "<td>"+user.reg_dt_fmt+"</td>";
// 						html += "</td>";
					
// 				})
				
				var html = data.split("###################");
				
// 				$("#userTbody").html(data);
				$("#userTbody").html(html[0]);
				$("#pagination").html(html[1]);
				
			}
			
		})
	}
	
</script>


	<form id="frm" action="${cp}/user/userTiles">
		<input type="hidden" id="userid" name="userid" value="">
	</form>


	<div class="row">
		<div class="col-sm-8 blog-main">
			<h2 class="sub-header">페이징된 사용자(tiles)</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<tr>
						<th>사용자 아이디</th>
						<th>사용자 이름</th>
						<th>사용자 별명</th>
						<th>등록일시</th>
					</tr>
					<tbody id="userTbody">
					</tbody>
					
				</table>
			</div>

			<a id="userRegist" class="btn btn-default pull-right">사용자 등록</a> <a
				class="btn btn-default pull-right" href="${cp }/user/excelDownload">사용자
				엑셀 다운로드</a>


			<div class="text-center">
			
				<ul id="pagination" class="pagination">
				</ul>
				
			</div>
		</div>
	</div>


