(function($){
	$(document).ready(function(){
		 font_size();
		 menu_size();
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
	$('html').css("font-size",font_size);
	$('.currentDate').css("font-size",font_size*6);
	$('#balance>label').css("font-size",font_size*3);
	$('.sub_menu-label').css("font-size",font_size*4);
	$('.menu-label').css("font-size",font_size*4);
}
function menu_size(){
	var width = window.innerWidth;
	var height = window.innerHeight;
	var body_width = (width+18);
	$('.header').css('height',height*0.14);
	$('.bodyCover').css('height',height*0.79);
	$('.bodyCover').css('top',$('.header').height());
	$('.body').css("width",body_width);
	$('.footer').css('height',height*0.07);
	$('.footer').css('top',height*0.93);
	$('#selectedDate').css('height',$('.header').height()*0.6);
	$('#balance').css('height',$('.header').height()*0.6);
	$('.sub_menus').css('width','100%');
	$('.sub_menus').css('display','inline-block');
	$('.sub_menu').css('height',$('.header').height()*0.4);
	
	if(window.screen.width>768 && width>768){
	$('body').css('width','60%');
	$('body').css('margin-left','20%');
	$('body').css('border-left','5px solid graytext');
	$('body').css('border-right','5px solid graytext');
	$('.header').css('border-top','5px solid graytext');
	$('.header').css('box-sizing','border-box');
	$('.bodyCover').css('top',$('.header').height()+5);
	$('.footer').css('border-bottom','5px solid graytext');
	$('.footer').css('box-sizing','border-box');
	$('.header').css('width','inherit');
	$('.footer').css('width','inherit');
	$('.bodyCover').css('width','inherit');
	$('.body').css("width",$('.bodyCover').width()+18);
	$('.datepicker').css('width',width*0.6*0.4);
	}else{
		$('body').css('width','100%');
		$('body').css('border','0px');
		$('.header').css('border','0px');
		$('.bodyCover').css('top',$('.header').height());
		$('.footer').css('border','0px');
		$('body').css('margin','0px');
		$('.header').css('width','100%');
		$('.footer').css('width','100%');
		$('.bodyCover').css('width','100%');
		$('.datepicker').css('width',width*0.6);
	}
	
	
}