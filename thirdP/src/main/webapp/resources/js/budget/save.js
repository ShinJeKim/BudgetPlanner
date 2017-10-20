$(document).ready(function(){
	$("#datepick").datepicker({
		 type	: 'ymd',
		 lang	: 'ko'
	});
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	 font_size();
	 menu_size();
	 if($('#main_cate').val() == '수입'){
		 $('#income').attr('checked', true);
	 }else if($('#main_cate').val() == '지출'){
		 $('#spending').attr('checked', true);
	 }else{
		 $('#income').attr('checked', true);
	 }
	 $('#BudgetPlanner').attr('checked', true);
	 
	 var without_tag_content =  $('#content').val().toString().replace("<p>","").replace("</p>","");
	 var fromDB = without_tag_content.replace(/<br>/g,"\n");
 	 $('#detail').val(fromDB);
	
 	 
	if($('#main_cate').val() == ""){
		$('#main_cate').val("수입")
	} 
	setcate();
	 
	 $('.sub_selector').click(function(){
		 var cate = $(':input[name=main_cates]:radio:checked').val();
		 $('#main_cate').val(cate); 
		 setcate();
		 $('#ccate div:first-child').find('.cateSelect').trigger('click');
		 var sub_cate = $(document).find(':input[name=cate]:radio:checked').val();
		 $('#sub_cate').val(sub_cate); 
	 });
	 
	 $('.cateSelect').click(function(){
		 var sub_cate = $(':input[name=cate]:radio:checked').val();
		 $('#sub_cate').val(sub_cate); 
	 });
	 
	var usage = $('#usage').val().replace("-","");
	$('#usage').val(usage);
	
	$('#up_save').click(function(){
		var tag_content =  "<p>"+$('#detail').val()+"</p>";
		var forDB = tag_content.replace(/\n/g,"<br>");
		$('#content').val(forDB);
		if($('#usage').val() == '' | $('#detail').val() == ''){
			if($('#usage').val() == ''){
				alert("금액을 입력해주세요");
				$('#usage').focus();
				$('#usage').css('border','inset 3px red');
			}else{
				$('#usage').css('border','inset 3px blue');
			}
			if($('#detail').val() == ''){
				alert("내용을 입력해주세요");
				$('#detail').focus();
				$('#detail').css('border','inset 3px red');
			}else{
				$('#detail').css('border','inset 3px blue');
			}
			return;
		}else{
			$('#save').submit();
		}
			
	});
	$('#cancle').click(function(){
		$('#save').attr('action','daily.do');
		$('#save').submit();
	});	
	
	$(window).resize(function() {
		 font_size();
		 menu_size();
	});	
	
	
	
	function setcate(){
		$.ajax({
        type:"POST",
        url:"cate.do",
        dataType: "JSON",
        async: false,
        data:{
           "mst_ct_nm"  : $('#main_cate').val()
        },
        success: function(data){
       var datahtml = "";
            for(var i=1;i<data.length;i++){
             datahtml += "<div class='cates'><input type='radio' class='cateSelect' value='"+data[i].dtl_ct_nm+"' id='cate"+i+"' name='cate'><label for='cate"+i+"'>"+data[i].dtl_ct_nm+"</label></div>"

            }
       	$('#ccate').html(datahtml);
        font_size();
   	 	menu_size();
       	if($('#workDiv').val() == 'save'){
			$('#ccate div:first-child').find('.cateSelect').trigger('click');
		}else if($('#workDiv').val() == 'update' && $('#ccate div:first-child').find('.cateSelect').val() != $('#sub_cate').val()){
			$('#ccate div').find('.cateSelect').each(function(){
				if($(this).val() == $('#sub_cate').val()){
					$(this).trigger('click');
				}else{
					return;
				}
			});
		}else if($('#workDiv').val() == 'update' && $('#ccate div:first-child').find('.cateSelect').val() == $('#sub_cate').val()){
			$('#ccate div:first-child').find('.cateSelect').trigger('click');
		}
        var sub_cate = $(document).find(':input[name=cate]:radio:checked').val();
		 $('#sub_cate').val(sub_cate);  
		 
        },
        complete: function(data){

        }
   	 });
	$(document).on('click',':input[name=cate]:radio:checked',function(){
		$('#sub_cate').val($(this).val()); 
	});	
  }
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
		$('.bodyCover').css('top',$('.header').height()+5);
		$('.footer').css('top',height*0.93-5);
	}else{
		if(window.screen.width<768){
			$('.cates>label').css('font-size',width*0.035);
		}
		$('body').css('width',width);
		$('body').css('height',height);
		$('body').css('border','0px');
		$('body').css('margin','0px');
		$('.header').css('top','0px');
		$('.header').css('height',height*0.14);
		$('.datepicker').css('width',width*0.4);
		$('.bodyCover').css('height',height*0.79);
		$('.bodyCover').css('top',$('.header').height());
		$('.bodyCover').css("width",width);
		$('.footer').css('top',height*0.93);
	}
	$('.header').css('width',$('.bodyCover').width());
	$('.sub_menus').css('width','100%');
	$('.sub_menus').css('display','inline-block');
	$('.sub_menu').css('height',$('.header').height());
	$('body').css('box-sizing','border-box');
	$('.body').css("width",$('.bodyCover').width()+18);
	$(document).find('.input_content>.cates').css('height',$('.input_content').height()*0.5);
	$(document).find('.input_content>.cates>label').css('height',$('#category_division').height()*0.9*0.5);
	$('#datepick').css('width',$('#date_division').width()*0.07);
	$('#datepick').css('height',$('#date_division').height()*0.4);
	$('.footer').css('height',height*0.07);
	$('.footer').css('width',$('.bodyCover').width());
	$('.menu').css('height',$('.footer').height());
	$(document).find('.cates').each(function(){
		   $(this).css('height',$('#ccate').height()*0.48);
		});
	
	
}