$(document).ready(function(){
	 font_size();
	 menu_size();
	 $('#mypage').attr('checked', true);
	
	$( window ).resize(function() {
		 font_size();
		 menu_size();
	});
	
	//email 중복체크 누름 여부
	var emailFlag = 0;

	//email 중복 체크 버튼
	$("#checkUserEmail").on("click", function(){
		
		
		var checkUserEmail = $("#email").val().trim();
		var sessionEmail = $("#sessionEmailData").val().trim();
		
		if(checkUserEmail == ""){
			alert("email을 입력해 주세요");
			return;
		}
		
		if(checkUserEmail != "" && (checkUserEmail == sessionEmail)){
			
			emailFlag = -1;
			
			if(confirm("기존 이메일을 사용하시겠습니까?")==false){
				return
			}
		}
		
		if(checkUserEmail != sessionEmail){
			
			console.log("ddd");
			
			var emailReg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
			
			if(!$("#email").val().match(emailReg)){
				alert("이메일 형식을 맞춰서 작성해 주시기 바랍니다");
				div_email.removeClass("has-success");
				div_email.addClass("has-error");
				$("#email").val('');
				$("#email").focus();
				return false;
			}  else {
				
				console.log("sessionEmail(diff) : "+sessionEmail);
				console.log("checkUserEmail(diff) : "+checkUserEmail);
				
				$.ajax({
					type:"POST",
					url:"do_check_email.do",
					dataType:"HTML", //option default : html
					data: {
						"email" : checkUserEmail
					},
					success: function(data){	//통신이 성공적으로 이루어 졌을 때 받을 함수
						console.log("data : "+data);
						var flag = ($.trim(data));
						if(flag != "0"){
							alert("다른 email를 입력해 주십시오");
						} else {
							emailFlag = 1;
							alert("사용할 수 있는 email 입니다"); 
						}
					},
					complete: function(data){	//실패 성공 상관없이 무조건 수행
						
					}, 
					error: function(xhr, status, error){
						
					}
				}); //ajax
			}
		} 
		
		console.log("emailFlag : "+emailFlag);
		console.log("checkUserEmail : "+checkUserEmail);
		
		
	});//checkUserEmail
		

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
	
	
	//////////////////////////////////////////////////////
	// Data input Check
	//////////////////////////////////////////////////////
	
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
	
	//update
	$("#update").on("click", function(event){
		event.preventDefault();//연속수행방지
		
		console.log("1");
		

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
	
		
		//PW 중복체크 검사
		if(emailFlag == 0){
			console.log("emailFlag(2) : "+emailFlag);
			alert("이메일 중복체크를 해주세요");
			return;
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
		

		
		$.ajax({
			type: "POST",
			url: "do_update.do",
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
				alert("성공적으로 수정되었습니다");
				$("#updateFrm").submit();
            	$(location).attr('href', "logout.do"); //로그아웃시키고 다시 로그인페이지로 이동
			},
			complete: function(data){
				
			},
			error:function(xhr, status, error){
				alert("수정에러");
			}
		});
	});//update
	
	//delete
	$("#delete").on("click", function(){
		var delete_flag = $("#delete_flag").val();
		
		if(confirm("탈퇴하시겠습니까?")==true){
			$.ajax({
				type:"POST",
				url:"do_delete.do",
				dataType:"html",
				async:false,
				data:{
					"id" : $("#id").val()
				},
				success: function(data){
					
					$("#updateFrm").submit();
					alert("회원탈퇴 되었습니다");
					
					$(location).attr('href', "logout.do"); //다시 로그인 페이지로 이동
					
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("탈퇴에러");
				}
			});
		} else {
			return;
		}
		
		
	});//delete
	
});//document

function font_size(){
	var width = window.innerWidth;
	var height = window.innerHeight;
	var font_size = "";
	if(width > height){
		font_size = height*0.01;
	}else{
		font_size = width*0.01
	}
	$('.menu-label').css("font-size",font_size*4);
}
function menu_size(){
	var width = window.innerWidth;
	var height = window.innerHeight;
	$('.body').css('height',height*0.93-5);
	$('.footer').css('height',height*0.07);
	$('.footer').css('top',height*0.93);
	$('#updateFrm').css('height',$('.body').height()*0.9);
	$('#updateFrm').css('margin-top',$('.body').height()*0.05);
	if(window.screen.width>768 && width>768){
	$('body').css('width','60%');
	$('body').css('height',height);
	$('body').css('margin-left','20%');
	$('body').css('border-left','5px solid graytext');
	$('body').css('border-right','5px solid graytext');
	$('body').css('border-top','5px solid graytext');
	$('.footer').css('border-bottom','5px solid graytext');
	$('.footer').css('box-sizing','border-box');
	$('.footer').css('width','inherit');
	}else{
		$('body').css('width','100%');
		$('body').css('border','0px');
		$('body').css('margin','0px');
		$('.footer').css('border','0px');
		$('.footer').css('width','100%');
	}
}