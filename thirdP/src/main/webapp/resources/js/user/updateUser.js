$(document).ready(function(){
	 font_size();
	 menu_size();
	 $('#mypage').attr('checked', true);
	
	$( window ).resize(function() {
		 font_size();
		 menu_size();
	});

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
	if(window.screen.width>768 && width>768){
	$('body').css('width','60%');
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