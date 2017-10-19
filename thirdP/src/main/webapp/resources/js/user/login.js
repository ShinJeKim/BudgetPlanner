	$(document).ready(function(){
		font_sizing();
		body_sizing();
		$(window).resize(function(){
			font_sizing();
			body_sizing();
		});
		
		///////////////////////////////////////////
		//ID focusing
		///////////////////////////////////////////		
		$("#id").focus();
		
		$('#passinput').keyup(function(e){
			if(e.which == 13){
				$("#loginBtn").click();
			}
		});
		
		///////////////////////////////////////////
		//LoginBtn
		///////////////////////////////////////////
		$("#loginBtn").on("click", function(){
			
			//get input id
			var userID = $("#id").val().trim();
			if(userID.length==0){
				alert("ID를 입력해 주세요");
				$("#id").val("");
				$("#id").focus();
				return false;
			} //end if
			
			//get input password
			var userPassword = $("#password").val().trim();
			if(userPassword.length==0){
				alert("Password를 입력해 주세요");
				$("#password").val("");
				$("#password").focus();
				return false;
			} //end if
			
			$("#loginFrm").submit();
		});
		
	}); //End Document
	
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
	$('.body').css("width",'100%');
	
	if(window.screen.width>768 && width>768){
	$('body').css('width','60%');
	$('body').css('margin-left','20%');
	$('body').css('border','5px solid graytext');
	$('.body').css("width",'100%');
	}else{
		$('body').css('width','100%');
		$('body').css('border','0px');
		$('body').css('margin','0px');
	}
}
