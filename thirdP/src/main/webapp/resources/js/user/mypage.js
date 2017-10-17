(function($){
	$(document).ready(function(){
		 font_size();
		 menu_size();
		 $(function(){
			$("#password").focus();
		 });
		 $('#mypage').attr('checked', true);
		 $('#identifyBtn').click(function(){
				console.log("클릭은 되는거냐?")
				 $.ajax({
		           type:"POST",
		           url:"checkPW.do",
		           dataType: "JSON",
		           async: false,
		           data:{
		              "id"  : $('#id').val(),
		              "password"  : $('#password').val()
		           },
		           success: function(data){
		        	  if(data == 1){
		        		  go_update();
		        	  }
		           },
		           complete: function(data){
		        	   console.log($('#id').val());
		        	   console.log($('#password').val());
		           }
		        });//ajax
			});	 
	});
	
	$( window ).resize(function() {
		 font_size();
		 menu_size();
	});
	function go_update(){
		$('#identifyFrm').submit();
	}
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