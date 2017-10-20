	$(document).ready(function(){
		font_sizing();
		body_sizing();
		$(window).resize(function(){
			font_sizing();
			body_sizing();
		});
		
		//ID, email 중복체크 누름 여부
		var idFlag = 0;
		var emailFlag = 0;
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
		
		//////////////////////////////////////////////////////
		/// Alphabet Validation Check
		//////////////////////////////////////////////////////
		$(".onlyAlphabetAndHangul").keyup(function(e){
			if(!(e.keyCode >= 37 && e.keyCode <= 40)){
				var inputVal = $(this).val();
				$(this).val($(this).val().replace(/[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]/gi,''));
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
			
			var checkUserId = $("#id").val().trim();
			
			if(checkUserId == ""){
				alert("ID를 입력해 주세요");
				return;
			}
			
			if(checkUserId != ""){
				
				var hangulReg = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

				if(hangulReg.test(checkUserId)){
					alert("영문과 숫자 조합으로 작성해 주시기 바랍니다.");
					$("#id").val('');
					$("#div_id").removeClass("has-success");
					$("#div_id").addClass("has-error");
					$("#id").focus();
					return false;
				}
			} 

			$.ajax({
				type:"POST",
				url:"do_check_id.do",
				dataType:"HTML", //option default : html
				data: {
					"id" : checkUserId
				},
				success: function(data){	//통신이 성공적으로 이루어 졌을 때 받을 함수

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
		
		//email 중복 체크 버튼
		$("#checkUserEmail").on("click", function(){
			
			var checkUserEmail = $("#email").val();
			
			if(checkUserEmail == ""){
				alert("email을 입력해 주세요");
				return;
			}

			if(checkUserEmail != ""){
				
				var emailReg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
				
				if(!$("#email").val().match(emailReg)){
					alert("이메일 형식을 맞춰서 작성해 주시기 바랍니다");
					div_email.removeClass("has-success");
					div_email.addClass("has-error");
					$("#email").val('');
					$("#email").focus();
					return false;
				} 
			} 
			
			$.ajax({
				type:"POST",
				url:"do_check_email.do",
				dataType:"HTML", //option default : html
				data: {
					"email" : checkUserEmail
				},
				success: function(data){	//통신이 성공적으로 이루어 졌을 때 받을 함수

					var flag = ($.trim(data));
					if(flag != "0"){
						alert("다른 email를 입력해 주십시오");
					} else {
						alert("사용할 수 있는 email 입니다"); 
						emailFlag = 1;
					}
				},
				complete: function(data){	//실패 성공 상관없이 무조건 수행
					
				}, 
				error: function(xhr, status, error){
					
				}
			}); //ajax
		});//checkUserEmail
		
		//////////////////////////////////////////////////////
		// Data input Check
		//////////////////////////////////////////////////////
		$("#id").keyup(function(e){
			var div_id = $("#div_id");
			
			if($("#id").val()==""){
				div_id.removeClass("has-success");
				div_id.addClass("has-error");
			} else {
				div_id.removeClass("has-error");
				div_id.addClass("has-success");
			}
			
		}); //id check
		
		$("#password").keyup(function(e){
			
			var div_password = $("#div_password");
			
			if($("#password").val()==""){
				div_password.removeClass("has-success");
				div_password.addClass("has-error");
			} else {
				div_password.removeClass("has-error");
				div_password.addClass("has-success");
			}
		}); //password check
		
		$("#password_check").keyup(function(e){
			
			var div_password_check = $("#div_password_check");
			
			if($("#password_check").val()==""){
				div_password_check.removeClass("has-success");
				div_password_check.addClass("has-error");
			} else {
				div_password_check.removeClass("has-error");
				div_password_check.addClass("has-success");
			}
			
		}); //password_check check
		
		$("#name").keyup(function(e){
			
			var div_name = $("#div_name");
			
			if($("#name").val() == ""){
				div_name.removeClass("has-success");
				div_name.addClass("has-error");
			} else {
				div_name.removeClass("has-error");
				div_name.addClass("has-success");
			}
			
		}); //name check
		
		$("#email").keyup(function(e){
			
			var div_email = $("#div_email");
			
			if($("#email").val() == ""){
				div_email.removeClass("has-success");
				div_email.addClass("has-error");
			} else {
				div_email.removeClass("has-error");
				div_email.addClass("has-success");
			}
			
		}); //email check
		
		$("#fixed_income").keyup(function(e){
			
			var div_fixed_income = $("#div_fixed_income");
			
			if($("#fixed_income").val() == ""){
				div_fixed_income.removeClass("has-success");
				div_fixed_income.addClass("has-error");
			} else {
				div_fixed_income.removeClass("has-error");
				div_fixed_income.addClass("has-success");
			}
			
		}); //fixed_income check
		
		$("#balance").keyup(function(e){
			
			var div_balance = $("#div_balance");
			
			if($("#balance").val() == ""){
				div_balance.removeClass("has-success");
				div_balance.addClass("has-error");
			} else {
				div_balance.removeClass("has-error");
				div_balance.addClass("has-success");
			}
			
		}); //balance check
		
		//////////////////////////////////////////////////////
		// Validation Check
		//////////////////////////////////////////////////////
		
		$("#doSave").on("click", function(){
			
 			var div_id = $("#div_id");
			var div_password = $("#div_password");
			var div_password_check = $("#div_password_check");
			var div_name = $("#div_name");
			var div_email = $("#div_email");
			var div_fixed_income = $("#div_fixed_income");
			var div_balance = $("#div_balance"); 
			
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
				
				div_id.removeClass("has-success");
				div_id.addClass("has-error");
				$("#id").focus();
				return false;
			} else if($("#id").val() != ""){
				var hangulReg = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

				if(hangulReg.test(checkUserId)){
					alert("영문과 숫자 조합으로 작성해 주시기 바랍니다.");
					$("#id").val('');
					div_id.removeClass("has-success");
					div_id.addClass("has-error");
					$("#id").focus();
					return false;
				}
			} else {
				div_id.removeClass("has-error");
				div_id.addClass("has-success");
			}
			
			//PASSWORD 검사
			if($("#password").val()==""){
				alert("패스워드를 입력해 주시기 바랍니다.");
				
				div_password.removeClass("has-success");
				div_password.addClass("has-error");
				$("#password").focus();
				return false;
			} else if($("#password").val() != ""){
				if(password.length < 8 || password.length > 12){ //자리수가 안맞을 경우
					alert("8자리 ~ 12자리 이내로 입력해주세요");
					div_password.removeClass("has-success");
					div_password.addClass("has-error");
					$("#password").focus();
					return false;
				} else { // 자리수가 맞을 때 영어+숫자+특수문자 조합 확인
					var num = $("#password").val().search(/[0-9]/g); //number
					var eng = $("#password").val().search(/[a-z]/ig); //eng character
					var spe = $("#password").val().search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi); //special character
					
					if(num < 0 || eng < 0 || spe < 0 ){
						  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요");
						  div_password.removeClass("has-success");
						  div_password.addClass("has-error");
						  $("#password").focus();
						  return false;
					} else if (num > 0 && eng > 0 && spe > 0){
						div_password.removeClass("has-error");
						div_password.addClass("has-success");
					}
				}
			}
			
			//PASSWORD 확인 검사
			if($("#password_check").val()==""){
				alert("패스워드를 입력해 주시기 바랍니다.");
				div_password_check.removeClass("has-success");
				div_password_check.addClass("has-error");
				$("#password_check").focus();
				return false;
			} else {
				div_password_check.removeClass("has-error");
				div_password_check.addClass("has-success");
			}
			
			//패스워드 비교
			if($("#password").val() != $("#password_check").val() || $("#resi_password_check").val==""){
				alert("패스워드가 일치하지 않습니다.");
				div_password_check.removeClass("has-success");
				div_password_check.addClass("has-error");
				$("#password_check").focus();
				return false;
			} else {
				div_password_check.removeClass("has-error");
				div_password_check.addClass("has-success");
			}
			
			//이름 검사
			if($("#name").val()==""){
				alert("이름을 입력해 주시기 바랍니다."); 
				div_name.removeClass("has-success");
				div_name.addClass("has-error");
				$("#name").focus();
				return false;
			} else {
				div_name.removeClass("has-error");
				div_name.addClass("has-success");
			}
			
			//ID 중복체크 검사
			if(emailFlag!=1){
				alert("EMAIL 중복체크를 해주세요");
				return;
			}
			
			//이메일 검사
			if($("#email").val()==""){
				alert("이메일을 입력해 주시기 바랍니다."); 
				div_email.removeClass("has-success");
				div_email.addClass("has-error");
				$("#email").focus();
				return false;
			} else {
				
				var emailReg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

				if(!$("#email").val().match(emailReg)){
					alert("이메일 형식을 맞춰서 작성해 주시기 바랍니다");
					div_email.removeClass("has-success");
					div_email.addClass("has-error");
					$("#email").val('');
					$("#email").focus();
					return false;
				} else {
					div_email.removeClass("has-error");
					div_email.addClass("has-success");
				}
			}
			
			//고정수입 검사
			if($("#fixed_income").val()==""){
				alert("고정수입을 입력해 주시기 바랍니다."); 
				div_fixed_income.removeClass("has-success");
				div_fixed_income.addClass("has-error");
				$("#fixed_income").focus();
				return false;
			} else {
				div_fixed_income.removeClass("has-error");
				div_fixed_income.addClass("has-success");
			}
			
			//잔고 검사
			if($("#balance").val()==""){
				alert("현재잔고를 입력해 주시기 바랍니다."); 
				div_balance.removeClass("has-success");
				div_balance.addClass("has-error");
				$("#balance").focus();
				return false;
			} else {
				div_balance.removeClass("has-error");
				div_balance.addClass("has-success");
			}
			alert("회원가입에 성공하였습니다.")
			$("#createFrm").submit();
		});//do_save
	});//document
	function font_sizing(){
		var font_size = "";
		if(window.innerWidth > window.innerHeight){
			font_size = window.innerHeight*0.01;
		}else{
			font_size = window.innerWidth*0.01
		}
		$('html').css("font-size",font_size);
	}

	function body_sizing(){
		var width = window.innerWidth;
		var height = window.innerHeight;
		$('body').css('box-sizing','border-box');
		if(window.screen.width>768 && width>768){
			$('body').css('width',width*0.6);
			$('body').css('margin-left',width*0.2);
			$('body').css('border','5px solid graytext');
			$('body').css('height',height);
			$('.body').css("width",(width*0.6)-10);
			$('.body').css("height",height-10);
		}else{
			$('body').css('width',width);
			$('body').css('height',height);
			$('body').css('border','0px');
			$('body').css('margin','0px');
			$('.body').css("width",width);
			$('.body').css('height',height);
		}
		$('#createFrm>div').css('height',$('.body').height()*0.9);
		$('#createFrm>div').css('margin-top',$('.body').height()*0.05);
		$('.form-group').css('height',$('#createFrm>div').height()*0.1);
		$('#div_id').css('height',$('#createFrm>div').height()*0.15);
		$('#div_email').css('height',$('#createFrm>div').height()*0.15);
		$('.form-group>label').css('height',$('.form-group').height()*0.3);
		$('.form-group>div').css('height',$('.form-group').height()*0.6);
		$('.form-group').css('margin-bottom',$('.form-group').height()*0.1);
		$('.form-group>div>input').css('height',$('.form-group>div').height()*0.7);
		$('#div_id>label').css('height',$('#div_id').height()*0.2);
		$('#div_id>div').css('height',$('#div_id').height()*0.73);
		$('#div_id>div>#id').css('height',$('#div_id>div').height()*0.5);
		$('#div_id>div>#checkUserId').css('height',$('#div_id>div').height()*0.4);
		$('#div_email>label').css('height',$('#div_email').height()*0.2);
		$('#div_email>div').css('height',$('#div_email').height()*0.73);
		$('#div_email>div>input').css('height',$('#div_email>div').height()*0.5);
		$('#div_id>div>#checkUserEmail').css('height',$('#div_email>div').height()*0.4);
		$('#div_button').css('padding-top',$('.form-group').height()*0.1);
		$('#div_button>input').css('height',$('#div_button').height()*0.8);
	}
	