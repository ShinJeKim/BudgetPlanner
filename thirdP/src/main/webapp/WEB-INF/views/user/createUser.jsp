<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<title>::: Create User :::</title>
<script type="text/javascript">

	$(document).ready(function(){
		
		//ID 중복체크 누름 여부
		var idFlag = 0;
		
		//////////////////////////////////////////////////////
		/// Alphabet And Number Validation Check
		//////////////////////////////////////////////////////
		$(".onlyAlphabetAndNumber").keyup(function(e){
			if(!(e.keyCode >= 37 && e.keyCode <= 40)){
				var inputVal = $(this).val();
				$(this).val($(this).val().replace(/[^_a-z0-9]/gi,''));
			}
		});
		
		///////////////////////////////////////////////////
		/// onlyNumber Validation Check
		//////////////////////////////////////////////////////
		$(".onlyNumber").keyup(function(e){
			if(!(e.keyCode >= 37 && e.keyCode <=40)){
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[^0-9]/gi,''));
			}
		});
		
		//ID 중복 체크 버튼
		$("#checkUserId").on("click", function(){
			
			var checkUserId = $("#id").val();
			
			if(checkUserId == ""){
				alert("ID를 입력해 주세요");
				return;
			}
			
			console.log("checkUserId : "+checkUserId);
			
			$.ajax({
				type:"POST",
				url:"do_check_id.do",
				dataType:"HTML", //option default : html
				data: {
					"id" : checkUserId
				},
				success: function(data){	//통신이 성공적으로 이루어 졌을 때 받을 함수
					console.log("data : "+data);
					var flag = ($.trim(data));
					if(flag == "1"){
						alert("다른 ID를 입력해 주십시오");
					} else {
						alert("사용할 수 있는 ID 입니다"); 
						idFlag = 1;
					}
				},
				complete: function(data){	//실패 성공 상관없이 무조건 수행
					
				}, 
				error: function(xhr, status, error){
					
				}
			}); //ajax
		});//checkUserId
		
		//////////////////////////////////////////////////////
		// Validation Check
		//////////////////////////////////////////////////////
		
		$("#doSave").on("click", function(){
			
/* 			var div_id = $("#div_id");
			var div_password = $("#div_password");
			var div_password_check = $("#div_password_check");
			var div_name = $("#div_name");
			var div_email = $("#div_email");
			var div_fixed_income = $("#div_fixed_income");
			var div_balance = $("#div_balance"); */
			
			var id = $("#id").val();
			var password = $("#password").val();
			var password_check = $("#password_check").val();
			var name = $("#name").val();
			var email = $("#email").val();
			var fixed_income = $("#fixed_income").val();
			var balance = $("#balance").val();
			
			//ID 중복체크 검사
			if(idFlag!=1){
				alert("ID 중복체크를 해주세요");
				return;
			}
			
			//ID 검사
			if($("#id").val==""){
				alert("아이디를 입력해 주시기 바랍니다.");
				
				$("#id").focus();
				return false;
			} 
			//PASSWORD 검사
			if($("#password").val==""){
				alert("패스워드를 입력해 주시기 바랍니다.");
				
				$("#password").focus();
				return false;
			}
			
			//PASSWORD 확인 검사
			if($("#password_check").val==""){
				alert("패스워드를 입력해 주시기 바랍니다.");

				$("#password_check").focus();
				return false;
			}
			
			//패스워드 비교
			if($("#password").val() != $("#password_check").val() || $("#resi_password_check").val==""){
				alert("패스워드가 일치하지 않습니다.");
				
				$("#password_check").focus();
				return false;
			} 
			
			//이름 검사
			if($("#name").val()==""){
				alert("이름을 입력해 주시기 바랍니다."); 
				
				$("#name").focus();
				return false;
			}
			
			//이름 검사
			if($("#fixed_income").val()==""){
				alert("고정수입을 입력해 주시기 바랍니다."); 
				
				$("#fixed_income").focus();
				return false;
			} 
			
			//이름 검사
			if($("#balance").val()==""){
				alert("현재잔고를 입력해 주시기 바랍니다."); 
				
				$("#balance").focus();
				return false;
			}
			
			$("#createFrm").submit();
		});//do_save
	});//document
	
</script>
</head>
<body>
	<form id="createFrm" method="post" action="do_save.do">
		<!-- 아이디 -->
		<div id="div_id">
			<label for="id">아이디</label>
			<div>
				<input type="text" class="onlyAlphabetAndNumber" id="id" name="id" data-rule-required="true"
					placeholder="12자 이내의 알파벳, 숫자만 입력 가능합니다." maxlength="12"/>
				<input type="button" id="checkUserId" name="checkUserId" value="중복확인"/>	
			</div>
		</div>
		<!-- 비밀번호 -->
		<div id="div_password">
			<label for="password">비밀번호</label>
			<div>
				<input type="password" id="password" name="password" data-rule-required="true"
				placeholder="비밀번호" maxlength="12">
			</div>
		</div>
		<!-- 비밀번호확인 -->
		<div id="div_password_check">
			<label for="password_check">비밀번호확인</label>
			<div>
				<input type="password" id="password_check" name="password_check" data-rule-required="true"
				placeholder="비밀번호확인" maxlength="12">
			</div>
		</div>
		<!-- 이름 -->
		<div id="div_name">
			<label for="name">이름</label>
			<div>
				<input type="text" id="name" name="name" data-rule-required="true" 
				placeholder="이름" maxlength="30">
			</div>
		</div>
		<!-- 이메일 -->
		<div id="div_email">
			<label for="email">이메일</label>
			<div>
				<input type="text" id="email" name="email" data-rule-required="true" 
				placeholder="이메일" maxlength="50">
			</div>
		</div>
		<!-- 고정수입 -->
		<div class="onlyNumber" id="div_fixed_income">
			<label for="fixed_income">고정수입</label>
			<div>
				<input type="text" id="fixed_income" name="fixed_income" data-rule-required="true" 
				placeholder="고정수입" maxlength="10">
			</div>
		</div>
		<!-- 현재잔고 -->
		<div class="onlyNumber" id="div_balance">
			<label for="balance">현재잔고</label>
			<div>
				<input type="text" id="balance" name="balance" data-rule-required="true" 
				placeholder="현재잔고" maxlength="12">
			</div>
		</div>
		<!-- 버튼 -->
		<div>
			<input type="button" id="cancle" value="취소" onclick="history.go(-1)"/>
			<input type="button" id="doSave" value="가입" />
		</div>
	</form>
</body>
</html>