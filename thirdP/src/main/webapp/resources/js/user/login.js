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
		$('#loginFrm>div').css('height',$('.body').height()*0.8*0.3*0.25);
		$('#loginFrm>#logo').css('height',$('.body').height()*0.8*0.7);
		$('#loginFrm>#logo').css('padding-top',$('.body').height()*0.1);
		$('#logo>label>img').css('width',$('#loginFrm>#logo').width()*0.6);
		$('#logo>label>img').css('height',$('.body').height()*0.8*0.7);
	}
