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
			
			if($("#pw_email").val() == ''){
				alert("이메일을 입력해 주세요");
				return false;
			}
			
			$("#inputID").val($("#id").val());
			$("#inputEmail").val($("#pw_email").val());
			
			$("#missingPWFrm").submit();
			
		});//missing_pw
		
		function font_sizing(){
			var font_size = "";
			if(window.innerWidth > window.innerHeight){
				font_size = window.innerHeight*0.01;
			}else{
				font_size = window.innerWidth*0.01
			}
			$('html').css("font-size",font_size);
			$('#goBack').css('width',font_size*8);
			$('#goBack').css('height',font_size*6);
		}
		
		function body_sizing(){
			var width = window.innerWidth;
			var height = window.innerHeight;
			
			if(window.screen.width>768 && width>768){
				$('body').css('width',width*0.6);
				$('body').css('margin-left',width*0.2);
				$('body').css('border','5px solid graytext');
				$('body').css('height',height);
				$('.body').css("width",(width*0.6)-10);
				$('.body').css("height",height-10);
				$('#goBack').css('top','5px');
			}else{
				$('body').css('width',width);
				$('body').css('height',height);
				$('body').css('border','0px');
				$('body').css('margin','0px');
				$('.body').css("width",width);
				$('.body').css('height',height);
				$('#goBack').css('top','0px');
			}
			$('body').css('box-sizing','border-box');
			$('.body').css('height',height-10);
			$('#missingIDFrm').css("height",$('.body').height()*0.5);
			$('#missingPWFrm').css("height",$('.body').height()*0.5);
			$('#missingIDFrm').css("width",$('.body').width());
			$('#missingPWFrm').css("width",$('.body').width());
			$('#missingIDFrm>div').css('width',$('#missingIDFrm').width()*0.7);
			$('#missingPWFrm>div').css('width',$('#missingPWFrm').width()*0.7);
			$('#missingIDFrm>div').css('margin-left',$('#missingIDFrm').width()*0.15);
			$('#missingPWFrm>div').css('margin-left',$('#missingPWFrm').width()*0.15);
			$('#missingIDFrm>div').css('height',$('#missingIDFrm').height()*0.8);
			$('#missingPWFrm>div').css('height',$('#missingPWFrm').height()*0.8);
			$('#missingIDFrm>div').css('margin-top',$('#missingIDFrm').height()*0.1);
			$('#missingPWFrm>div').css('margin-top',$('#missingPWFrm').height()*0.1);
		}		
	$('#goBack').click(function(){
		location.replace('main.do');
	});
}); //End Document