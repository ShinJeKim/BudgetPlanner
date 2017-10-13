	$(document).ready(function(){
		
		//ID 중복체크 누름 여부
		var idFlag = 0;
		//
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
			if($("#id").val()==""){
				alert("아이디를 입력해 주시기 바랍니다.");
				
				$("#id").focus();
				return false;
			} 
			
			//PASSWORD 검사
			if($("#password").val()==""){
				alert("패스워드를 입력해 주시기 바랍니다.");
				
				$("#password").focus();
				return false;
			} 
			
			//password validation check
			if($("#password").val()!="") {
				
				if(password.length < 8 || password.length > 12){ //자리수가 안맞을 경우
					alert("8자리 ~ 12자리 이내로 입력해주세요");
					$("#password").focus();
					return false;
				} else { // 자리수가 맞을 때 영어+숫자+특수문자 조합 확인
					var num = $("#password").val().search(/[0-9]/g); //number
					var eng = $("#password").val().search(/[a-z]/ig); //eng character
					var spe = $("#password").val().search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi); //special character
					
					if(num < 0 || eng < 0 || spe < 0 ){
						  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요");
						  $("#password").focus();
						  return false;
					}	
				}
			}

			
			//PASSWORD 확인 검사
			if($("#password_check").val()==""){
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
			
			//고정수입 검사
			if($("#fixed_income").val()==""){
				alert("고정수입을 입력해 주시기 바랍니다."); 
				
				$("#fixed_income").focus();
				return false;
			} 
			
			//잔고 검사
			if($("#balance").val()==""){
				alert("현재잔고를 입력해 주시기 바랍니다."); 
				
				$("#balance").focus();
				return false;
			}
			alert("회원가입에 성공하였습니다.")
			$("#createFrm").submit();
		});//do_save
	});//document