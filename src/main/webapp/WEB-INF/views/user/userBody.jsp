<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common_lib.jsp" %>


<script>
	$(function() {
		
		
		/* 사용자 수정 : method : get, action = /userModify
		사용자 삭제 : method : post, action = /deleteUser
		파라미터는 둘다 userid 하나만 있으면 가능 */
		
		$("#modifyBtn").on("click", function() {
			$("#frm").attr("method", "get");
			$("#frm").attr("action", "${cp}/user/modifyUserPage");
			$("#frm").submit();
		})
		
		$("#deleteBtn").on("click", function() {
			$("#frm").attr("method", "post");
			$("#frm").attr("action", "${cp}/user/deleteUser");
			$("#frm").submit();
		})
		
	})
</script>

		<div class="row">
			
			<div class="col-sm-8 blog-main">
			

				<form class="form-horizontal" id="frm" role="form" action="${cp}/userModify">
					
					<input type="hidden" id="s_userid" name="s_userid" value="${S_USER.userid}">
				
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진(타일즈)</label>
						<div class="col-sm-10">
						<a href="${cp }/user/profileDownload?userid=${user.userid}">
							<img src="${cp }/user/profile?userid=${user.userid}"><br>
						</a>
							<label class="control-label">${user.userid}</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디(타일즈부분)</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userid" name="userid"
								placeholder="사용자 아이디" value="${user.userid}" readonly/>
						</div>
					</div>


					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm"
								placeholder="사용자 이름" value="${user.usernm}" readonly/>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias"
								name="alias" placeholder="별명" value="${user.alias}" readonly/>
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass"
								placeholder="Password" value="${user.pass}" readonly/>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							
							
							<button type="button" id="modifyBtn" class="btn btn-default">사용자 수정</button>
							<button type="button" id="deleteBtn" class="btn btn-default">사용자 삭제</button>
						</div>
					</div>
				</form>
			</div>
		</div>
