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
		
		console.log("checkUserEmail : "+checkUserEmail);
		
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
	
	//update
	$("#update").on("click", function(event){
		event.preventDefault();//연속수행방지
		
		console.log("1");
		
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
				$("#updateFrm").submit();
            	$(location).attr('href', "logout.do"); //로그아웃시키고 다시 로그인페이지로 이동
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
			url:"do_delete.do",
			dataType:"html",
			async:false,
			data:{
				"id" : $("#id").val()
			},
			success: function(data){
				alert("탈퇴하시겠습니까?");
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