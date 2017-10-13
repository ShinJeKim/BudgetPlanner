<%@page import="com.apps.user.domain.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	UserVO userVO = null;
	String id = "";
	
	if(session.getAttribute("id")!=null){
		userVO = (UserVO)session.getAttribute("id");
	}else{
		userVO = new UserVO();
		userVO.setId("id");
	}
	id = userVO.getId();
%>

<script type="text/javascript">
	//JQuery
	$(document).ready(function(){
	
		//update
		$("#update").on("click", function(event){
			event.preventDefalut();//연속수행방지
			
			$.ajax({
				type: "POST",
				url: "update.do",
				dataType: "html",
				async: false,
				data:{
					"id" : $("#id").val(),
					"password" : $("#password").val(),
					"name" : $("#name").val(),
					"email" : $("#email").val(),
					"fixed_income" : $("#fixed_income").val(),
					"balance" : $("#balance").val()
					
				},
				success: function(data){
					$("#update").submit();
	            	location.href="logout.do";//로그아웃시키고 다시 로그인페이지로 이동
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("수정에러");
				}
			});
		});//upedate
		
		//delete
		$("#delete").on("click", function(){
			var delete_flag = $("#delete_flag").val();
			
			$.ajax({
				type:"POST",
				url:"delete.do",
				dataType:"html",
				async:false,
				data:{
					"id" : $("#id").val(),
					"delete_flag" : $("#delete_flag").val()
				},
				success: function(data){
					alert("탈퇴하시겠습니까?");
					$("#delete").submit();
					alert("회원탈퇴 되었습니다");
					location.href = "logout.do";//다시 로그인 페이지로 이동
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("탈퇴에러");
				}
			});
		});//delete
		
});//JQuery

</script>


<form id="updateFrm" method="post" action="do_save.do">
	<div>
		<!-- 아이디  -->
		<div id="div_id">
			<label for="id">아이디</label>
			<div>
				<input type="text" class="onlyAlphabetAndNumber" id="id" name="id"  value="<%=userVO.getId()%> data-rule-required="true"
					placeholder="12자 이내의 알파벳, 숫자만 입력 가능합니다." maxlength="12" disabled="disabled" />
			</div>
		</div>
		<!-- 비밀번호 -->
		<div id="div_password">
			<label for="password">비밀번호</label>
			<div>
				<input type="password" id="password" name="password" value="<%=userVO.getPassword()%> data-rule-required="true"
				placeholder="비밀번호" maxlength="12">
			</div>
		</div>
		<!-- 비밀번호확인 -->
		<div id="div_password_check">
			<label for="password_check">비밀번호확인</label>
			<div>
				<input type="password" id="password_check" name="password_check" value="<%=userVO.getPassword()%> data-rule-required="true"
				placeholder="비밀번호확인" maxlength="12">
			</div>
		</div>
		<!-- 이름 -->
		<div id="div_name">
			<label for="name">이름</label>
			<div>
				<input type="text" id="name" name="name" value="<%=userVO.getName()%> data-rule-required="true" 
				placeholder="이름" maxlength="30">
			</div>
		</div>
		<!-- 이메일 -->
		<div id="div_email">
			<label for="email">이메일</label>
			<div>
				<input type="text" id="email" name="email" value="<%=userVO.getEmail()%> data-rule-required="true" 
				placeholder="이메일" maxlength="50">
			</div>
		</div>
		<!-- 고정수입 -->
		<div class="onlyNumber" id="div_fixed_income">
			<label for="fixed_income">고정수입</label>
			<div>
				<input type="text" id="fixed_income" name="fixed_income" value="<%=userVO.getFixed_income()%> data-rule-required="true" 
				placeholder="고정수입" maxlength="10">
			</div>
		</div>
		<!-- 현재잔고 -->
		<div class="onlyNumber" id="div_balance">
			<label for="balance">현재잔고</label>
			<div>
				<input type="text" id="balance" name="balance" value="<%=userVO.getBalance()%> data-rule-required="true" 
				placeholder="현재잔고" maxlength="12" disabled="disabled"/>
			</div>
		</div>
		<!-- 버튼 -->
		<div>
			<input type="button" id="delete" value="회원탈퇴"/>
			<input type="button" id="cancle" value="취소" onclick="history.go(-1)"/>
			<input type="button" id="update" value="저장" />
		</div>
	</div>
</form>