(function($){
	$(document).ready(function(){
		 font_size();
		 menu_size();
		 $(function(){
			$("#password").focus();
		 });
		 $('#mypage').attr('checked', true);
		 
		 $('#password').keyup(function(e){
			if(e.which == 13){
				$("#identifyBtn").click();
			}
		 });
		 
		 $('#identifyBtn').click(function(){

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
	if(window.screen.width>768 && width>768){
		$('body').css('width',width*0.6);
		$('body').css('margin-left',width*0.2);
		$('body').css('border','5px solid graytext');
		$('body').css('height',height);
		$('.body').css("width",(width*0.6)-10);
		$('.body').css("height",height*0.93-10);
		$('.footer').css('top',height*0.93-5);
	}else{
		$('body').css('width',width);
		$('body').css('height',height);
		$('body').css('border','0px');
		$('body').css('margin','0px');
		$('.body').css("width",width);
		$('.body').css('height',height*0.93);
		$('.footer').css('top',height*0.93);
	}
	$('body').css('box-sizing','border-box');
	$('.footer').css('height',height*0.07);
	$('.footer').css('width',$('.body').width());
	$('.menu').css('height',$('.footer').height());
	$('#identifyFrm').css('width',$('.body').width());
	$('#identifyFrm').css('height',$('.body').height());
	$('#identifyFrm>div').css('width',$('#identifyFrm').width()*0.5);
	$('#identifyFrm>div').css('margin-left',$('#identifyFrm').width()*0.25);
	$('#identifyFrm>div').css('width',$('#identifyFrm').width()*0.5);
	$('#identifyFrm>div').css('height',$('#identifyFrm').height()*0.5);
	$('#identifyFrm>div').css('margin-top',$('#identifyFrm').height()*0.25);
}