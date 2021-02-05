<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	
	$(function() {
		
		// 주소검색 버튼이 클릭 되었을 때 다음주소 api 팝업을 연다
		$("#addrBtn").on("click", function() {
			
		    new daum.Postcode({
		        oncomplete: function(data) {
		            
		            $("#addr1").val(data.roadAddress);		//도로주소
		            $("#zipcode").val(data.zonecode);		//우편번호
		        }
		    }).open();
			
		    
		    // 사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
		    $("#addr2").focus();
			
		});
		
		
	});
</script>



<form class="form-horizontal" role="form" action="${cp}/user/registUser"
	method="post" enctype="multipart/form-data">

	<input type="hidden" name="s_userid" value="${S_USER.userid }" />

	<div class="form-group">
		<label class="col-sm-2 control-label"><spring:message code ="USERID" />(타일즈)</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="userid" name="userid"
				placeholder="사용자 아이디" value="${param.userid}" /> <br> <span
				style="color: red"><form:errors path="userVo.userid" /></span> <br>
			<br> <input type="file" class="form-control" name="profile" />
		</div>
	</div>

	<div class="form-group">

		<%-- 						spring message : <spring:message code="LANG" /> --%>
		spring message :
		<spring:message code="GREETING" arguments="${S_USER.userid }" />
		<br> <label for="usernm" class="col-sm-2 control-label"><spring:message code ="USERNM" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="usernm" name="usernm"
				placeholder="사용자 이름" value="${param.usernm}" />
		</div>
	</div>




	<div class="form-group">
		<label for="alias" class="col-sm-2 control-label"><spring:message code ="ALIAS" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="alias" name="alias"
				placeholder="별명" value="${param.alias}" />
		</div>
	</div>


	<div class="form-group">
		<label for="pass" class="col-sm-2 control-label"><spring:message code ="PASS" /></label>
		<div class="col-sm-10">
			<input type="password" class="form-control" id="pass" name="pass"
				placeholder="비밀번호" value="${param.pass}" />
		</div>
	</div>

	<div class="form-group">
		<label for="reg_dt" class="col-sm-2 control-label"><spring:message code ="REG_DT" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="reg_dt" name="reg_dt"
				placeholder="" value="${param.reg_dt}" />
		</div>
	</div>

	<div class="form-group">
		<label for="addr1" class="col-sm-2 control-label"><spring:message code ="ADDR1" /></label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="addr1" name="addr1"
				placeholder="도로주소" value="${param.addr1}" readonly />
		</div>
		<div class="col-sm-2">
			<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
		</div>
	</div>

	<div class="form-group">
		<label for="addr2" class="col-sm-2 control-label"><spring:message code ="ADDR2" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="addr2" name="addr2"
				placeholder="상세주소" value="${param.addr2}" />
		</div>
	</div>

	<div class="form-group">
		<label for="zipcode" class="col-sm-2 control-label"><spring:message code ="ZIPCODE" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="zipcode" name="zipcode"
				placeholder="우편번호" value="${param.zipcode}" readonly />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">사용자 등록 완료</button>
			
			
			
		</div>
	</div>
</form>

<select name="lang">
	<option value="">언어설정</option>
	<option value="ko">한국어</option>
	<option value="en">영어</option>
</select>

<script>

	$(function() {
		
		$("select[name=lang]").val("${param.lang}");
		$("select[name=lang]").on("change", function() {
			
			console.log("on change : "+$(this).val());
			document.location="${cp}/user/registTiles?lang="+$(this).val();
			
		})
		
		
	})
	
</script>
