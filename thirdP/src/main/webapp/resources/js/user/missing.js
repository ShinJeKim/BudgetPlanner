$(document).ready(function(){

		font_sizing();
		body_sizing();
		$(window).resize(function(){
			font_sizing();
			body_sizing();
		});
		
		//missing_id
		$("#btn_ID").on("click", function(){
			
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
					console.log(data);
					 $("#IDresult").val(data);
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("id찾기 에러");
				}
			});
		});//missing_id
		
		
		//missing_pw
		$("#btn_PW").on("click", function(){
			
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
					console.log(data);
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
			$('body').css('height',height);
			$('#missingIDFrm').css("height",$('body').height()*0.38);
			$('#missingIDFrm').css("margin-top",$('body').height()*0.08);
			$('#missingPWFrm').css("height",$('body').height()*0.38);
			$('#missingPWFrm').css("margin-top",$('body').height()*0.08);
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
	
}); //End Document