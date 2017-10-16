
	$(document).ready(function(){
		
		
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
			

			var password = $("#password").val();
			var password_check = $("#password_check").val();
			var name = $("#name").val();
			var email = $("#email").val();
			var fixed_income = $("#fixed_income").val();

			

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
			alert("회원가입에 성공하였습니다.")
			$("#createFrm").submit();
		});//do_save
	});//document









(function($){
	$(document).ready(function(){
		 font_size();
		 menu_size();
		 $('#mypage').attr('checked', true);
	});
	
	$( window ).resize(function() {
		 font_size();
		 menu_size();
	});
	
})(jQuery);

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
	if(window.screen.width>768 && width>768){
	$('body').css('width','60%');
	$('body').css('margin-left','20%');
	$('body').css('border-left','5px solid graytext');
	$('body').css('border-right','5px solid graytext');
	$('body').css('border-top','5px solid graytext');
	$('.footer').css('width','100%');
	}else{
		$('body').css('width','100%');
		$('body').css('border','0px');
		$('body').css('margin','0px');
		$('.footer').css('width','100%');
	}
}