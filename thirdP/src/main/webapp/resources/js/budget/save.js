$(document).ready(function(){
	$("#datepick").datepicker({
		 type	: 'ymd',
		 lang	: 'ko'
	});
	
	 font_size();
	 menu_size();
	 $('#income').attr('checked', true);
	 $('#BudgetPlanner').attr('checked', true);
	 $('.currentDate').hide();
	 
	var usage = $('#usage').val().replace("-","");
	$('#usage').val(usage);
	
	$('#up_save').click(function(){
		$('#save').submit();	
	});
	$('#cancle').click(function(){
		history.go(-1);
	});	
	
	$(window).resize(function() {
		 font_size();
		 menu_size();
	});	 
	
});

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
	
	if(window.screen.width>768 && width>768){
	$('body').css('width','60%');
	$('body').css('margin-left','20%');
	$('body').css('border-left','5px solid graytext');
	$('body').css('border-right','5px solid graytext');
	$('.header').css('width','inherit');
	$('.footer').css('width','inherit');
	$('.bodyCover').css('width','inherit');
	$('.body').css("width",$('.bodyCover').width()+18);
	}else{
		$('body').css('width','100%');
		$('body').css('border','0px');
		$('body').css('margin','0px');
		$('.header').css('width','100%');
		$('.footer').css('width','100%');
	}
}