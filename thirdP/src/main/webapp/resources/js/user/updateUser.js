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