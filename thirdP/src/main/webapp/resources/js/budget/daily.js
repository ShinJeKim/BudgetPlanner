$(document).ready(function(){
	
	 $('#date').datepicker({
		 type	: 'ymd',
		 lang	: 'ko'
	 });
	$('#daily').attr('checked', true);
	$('#BudgetPlanner').attr('checked', true);

	body_sizing();
	font_sizing();
	$('.dailyitem').each(function(){
		var paddingTop = ($(this).height()-parseInt($(this).find('.itemList>li').css('font-size').replace('px','')))/2;
		$(this).find('.itemList>li').css('padding-top',paddingTop);
		if($(this).height()<$(this).find('.itemList').height()){
			$(this).css('height',$(this).find('.itemList').height());
		}
	
	});	
	$(window).resize(function(){
		body_sizing();
		font_sizing();
		$('.dailyitem').each(function(){
			var paddingTop = ($(this).height()-$(this).find('.itemList>li').css('font-size'))/2;
			$(this).find('.itemList>li').css('padding-top',paddingTop);
			if($(this).height()<$(this).find('.itemList').height()){
				$(this).css('height',$(this).find('.itemList').height());
			}
		});	
	});
	$('#currentDate').change(function(){
		$('#reg_dt').val($('#currentDate').val()).trigger('change');
		$('#search').attr("action","daily.do");
		$('#search').attr("method","post");
		$('#search').submit();
	});

});


function font_sizing(){
	var font_size = "";
	if(window.innerWidth > window.innerHeight){
		font_size = window.innerHeight*0.01;
	}else{
		font_size = window.innerWidth*0.01
	}
	$('#total>li').css('font-size',font_size*3);
	$('#total>#nowDate').css('font-size',font_size*5);
	$('.itemList>li').css('font-size',font_size*2.1);
	$('.currentDate').css("font-size",font_size*6);
	$('#selectedDate').css('height',$('.header').height()*0.6);
	$('#balance').css('height',$('.header').height()*0.6);
}

function body_sizing(){
	var height_body = $('.bodyCover').height();
	var width_body = $('.bodyCover').width();
	$('#dailyData').css('width',width_body);
	$('#dailyTitle').css('width',width_body);
	$('#dailyTitle').css('position','fixed');
	$('#dailyTitle').css('top',window.innerHeight*0.14);
	$('#sum').css('width',width_body*0.99);
	$('#sum').css('margin-left',width_body*0.005);
	$('#sum').css('margin-top',height_body*0.01);
	$('#sum').css('margin-bottom',height_body*0.03);
	$('#sum').css('height',height_body*0.12);
	$('#sum').css('border','1px solid');
	$('#sum').css('box-sizing','border-box');
	$('#dailyList').css('margin-top',$('#dailyTitle').height());
	$('.dailyitem').css('width',width_body*0.99);
	$('.dailyitem').css('margin-left',width_body*0.005);
	$('.dailyitem').css('margin-bottom',height_body*0.02);
	$('.dailyitem').css('height',height_body*0.1);
	$('.dailyitem').css('border','1px solid');
	$('.dailyitem').css('box-sizing','border-box');
	$('#blank').css('width',width_body);
	if(window.screen.width<768){
		$('#plus').css('height',height_body*0.1);
		$('#plus').css('width',$('#plus').height());
		$('#plus').css('font-size',window.innerHeight*0.07);
		$('#plus').css('bottom',window.innerHeight*0.09);
		$('#plus').css('right',window.innerWidth*0.02);
	}
	if(window.innerWidth<768 && window.screen.width>768){
		$('#plus').css('bottom',window.innerHeight*0.09);
		$('#plus').css('right',window.innerWidth*0.02);
		if(window.innerHeight>window.innerWidth){
			$('#plus').css('height',height_body*0.1);
			$('#plus').css('width',$('#plus').height());
			$('#plus').css('font-size',window.innerHeight*0.07);
		}else{
			$('#plus').css('width',width_body*0.1);
			$('#plus').css('height',$('#plus').width());
			$('#plus').css('font-size',window.innerWidth*0.08);
			$('#sum').css('height',height_body*0.17);
			$('#dailyList').css('margin-top',$('#dailyTitle').height());
			$('.dailyitem').css('height',height_body*0.14);
		}
	}else if(window.innerWidth>768 && window.screen.width>768){
		$('#plus').css('height',height_body*0.15);
		$('#plus').css('width',$('#plus').height());
		$('#plus').css('font-size',window.innerHeight*0.09);
		$('#plus').css('bottom',window.innerHeight*0.09);
		$('#plus').css('right',window.innerWidth*0.21);
	}
	$('#blank').css('height',$('#plus').height()*1.15);
}