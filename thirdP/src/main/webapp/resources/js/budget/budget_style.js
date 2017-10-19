(function($){
	$(document).ready(function(){
		 font_size();
		 menu_size();
	$('#balance>label').click(function(){
		if($(this).html().toString() == '잔고'){
			$.ajax({
	            type:"POST",
	            url:"getBalance.do",
	            dataType: "JSON",
	            async: false,
	            data:{
	            	
	            },
	            success: function(data){
	            	var balance = data.toString();
	            	if(balance.substring(1,0) == '-'){
	            		$('#balance>label').html(numberFormat(balance).replace('-',''));
		    			$('#balance>label').css('color','#ffaa9b');
		    			$('#balance>label').css('font-weight','bold');
		    			
	            	}else{
	            		$('#balance>label').html(numberFormat(balance));
		    			$('#balance>label').css('color','#b6f4fb');
		    			$('#balance>label').css('font-weight','bold');
	            	}
	            }
	         });
		}else{
			$(this).html('잔고');
			$(this).css('color','#ecdd84');
			$('#balance>label').css('font-weight','normal');
		}
	});	 
	});
	
	$( window ).resize(function() {
		 font_size();
		 menu_size();
	});
	
})(jQuery);
function numberFormat(num) {
    var pattern = /(-?[0-9]+)([0-9]{3})/;
    while(pattern.test(num)) {
        num = num.replace(pattern,"$1,$2");
    }
    return num;
}
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
	
	if(window.screen.width>768 && width>768){
		$('body').css('width',width*0.6);
		$('body').css('height',height);
		$('body').css('margin-left',width*0.2);
		$('body').css('border','5px solid graytext');
		$('.header').css('top','5px');
		$('.header').css('height',height*0.14-5);
		$('.datepicker').css('width',width*0.6*0.4);
		$('.bodyCover').css("width",(width*0.6)-10);
		$('.bodyCover').css('height',height*0.79-5);
		$('.footer').css('top',height*0.93-5);
	}else{
		$('body').css('width',width);
		$('body').css('height',height);
		$('body').css('border','0px');
		$('body').css('margin','0px');
		$('.header').css('top','0px');
		$('.header').css('height',height*0.14);
		$('.datepicker').css('width',width*0.6);
		$('.bodyCover').css('height',height*0.79);
		$('.bodyCover').css("width",width);
		$('.footer').css('top',height*0.93);
	}
	$('.header').css('width',$('.bodyCover').width());
	$('#selectedDate').css('height',$('.header').height()*0.6);
	$('#balance').css('height',$('.header').height()*0.6);
	$('.sub_menus').css('width','100%');
	$('.sub_menus').css('display','inline-block');
	$('.sub_menu').css('height',$('.header').height()*0.4);
	$('body').css('box-sizing','border-box');
	$('.bodyCover').css('top',$('.header').height());
	$('.body').css("width",$('.bodyCover').width()+18);
	$('.footer').css('height',height*0.07);
	$('.footer').css('width',$('.bodyCover').width());
	$('.menu').css('height',$('.footer').height());
	
	
	
}