<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	$(document).ready(function(){
		//missing_id
		$("#btn_Id").on("click", function(){
			
			$.ajax({
				type: "POST",
				url: "do_findID.do",
				dataType: "html",
				async: false,
				data:{
					"name" : $("#name").val(),
					"email" : $("#email").val()
					
				},
				success: function(data){
					console.log(data);
					 $("#IDresult").val(data);//로그아웃시키고 다시 로그인페이지로 이동
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("수정에러");
				}
			});
		});//missing_id
		
	});//JQuery

</script>


<form id="missingIDFrm" name="missingIDFrm" action="do_findID.do" method="post">
	<div>
		<label>아이디찾기</label>
		<div>
			<input type="text" placeholder="이름" required autofocus
				name="name" id="name"  maxlength="30" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="email" id="email" maxlength="50"/>
		</div>
		<div>
			<input type="submit" id="btn_Id" value="확인" />
		</div>
		<div>
			<c:if test="${message == 'idOK'}">
				<label id="IDresult">아이디는  '${id}' 입니다</label>
			</c:if>
			<c:if test="${message == 'idNo'}">
				<label id="IDresult">이름과 이메일을 확인해 주세요</label>
			</c:if>
		</div>
	</div>
</form>
<form id="missingPWFrm" name="missingPWFrm" action="missingPW.do" method="post">
	<div>
		<label>비밀번호찾기</label>
		<div>
			<input type="text" placeholder="ID" required autofocus
				name="id" id="id" value = "${userVO.id}" maxlength="15" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="email" id=email value = "${userVO.email}" maxlength="15"/>
		</div>
		<div>
			<input type="button" id="btn_Pw" value="확인" />
		</div>
	</div>
	<div>
		<label id="PWresult"></label>
	</div>
</form>