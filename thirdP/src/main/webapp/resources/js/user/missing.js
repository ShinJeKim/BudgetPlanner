$(document).ready(function(){

		font_sizing();
		body_sizing();
		$(window).resize(function(){
			font_sizing();
			body_sizing();
		});
		
		$('#email').keyup(function(e){
			if(e.which == 13){
				$("#btn_ID").click();
			}
		});

		//missing_id
		$("#btn_ID").on("click", function(){
			
			if($("#name").val() == ''){
				alert("이름을 입력해 주세요");
				return false;
			}
			
			if($("#email").val() == ''){
				alert("이메일을 입력해 주세요");
				return false;
			}
			
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

					 $("#IDresult").val(data);
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("id찾기 에러");
				}
			});
		});//missing_id
		

		$('#pw_email').keyup(function(e){
			if(e.which == 13){
				$("#btn_PW").click();
			}
		});

		
		//missing_pw
		$("#btn_PW").on("click", function(){
			
			if($("#id").val() == ''){
				alert("아이디를 입력해 주세요");
				return false;
			}
			
			if($("email").val() == ''){
				alert("이메일을 입력해 주세요");
				return false;
			}
			
			$.ajax({
				type: "POST",
				url: "do_findPW.do",
				dataType: "html",
				async: false,
				data:{
					"id" : $("#id").val(),
					"email" : $("#pw_email").val()
					
				},
				success: function(data){

					 $("#PWresult").val(data);
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("비밀번호찾기 에러");
				}
			});
		});//missing_pw
		
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
			$('body').css('height',height-10);
			$('#missingIDFrm').css("height",$('body').height()*0.38);
			$('#missingIDFrm').css("margin-top",$('body').height()*0.08);
			$('#missingPWFrm').css("height",$('body').height()*0.38);
			$('#missingPWFrm').css("margin-top",$('body').height()*0.08);
			$('#goBack').css('top','0px');
			$('#goBack').css('width',width*0.12);
			$('#goBack').css('height',height*0.06);
			if(window.screen.width>768 && width>768){
			$('body').css('width','60%');
			$('body').css('margin-left','20%');
			$('body').css('border','5px solid graytext');
			$('.body').css("width",'100%');
			$('#goBack').css('top','5px');
			$('#goBack').css('width',$('body').width()*0.12);
			$('#goBack').css('height',$('body').height()*0.06);
			}else{
				$('body').css('width','100%');
				$('body').css('border','0px');
				$('body').css('margin','0px');
			}
		}		
	$('#goBack').click(function(){
		location.replace('main.do');
	});
}); //End Document