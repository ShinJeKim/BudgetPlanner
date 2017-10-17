$(document).ready(function(){
	$("#datepick").datepicker({
		 type	: 'ymd',
		 lang	: 'ko'
	});
	
	 font_size();
	 menu_size();
	 if($('#main_cate').val() == '수입'){
		 $('#income').attr('checked', true);
	 }else if($('#main_cate').val() == '지출'){
		 $('#spending').attr('checked', true);
	 }
	 $('#BudgetPlanner').attr('checked', true);
	 
	 var without_tag_content =  $('#content').val().toString().replace("<p>","").replace("</p>","");
	 console.log(without_tag_content);
	 var fromDB = without_tag_content.replace(/<br>/g,"\n");
	 console.log(fromDB);
 	 $('#detail').val(fromDB);
	
 	 
	if($('#main_cate').val() == ""){
		$('#main_cate').val("수입")
	} 
	setcate();
	 
	 $('.sub_selector').click(function(){
		 var cate = $(':input[name=main_cates]:radio:checked').val();
		 $('#main_cate').val(cate); 
		 setcate();
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
		$('#save').submit();	
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
       	$('#ccate div:first-child').find('.cateSelect').trigger('click');
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
	$('.header').css('border-top','5px solid graytext');
	$('.header').css('box-sizing','border-box');
	$('.bodyCover').css('top',$('.header').height()+5);
	$('.footer').css('border-bottom','5px solid graytext');
	$('.footer').css('box-sizing','border-box');
	$('.header').css('width','inherit');
	$('.footer').css('width','inherit');
	$('.bodyCover').css('width','inherit');
	$('.body').css("width",$('.bodyCover').width()+18);
	}else{
		$('body').css('width','100%');
		$('body').css('border','0px');
		$('body').css('margin','0px');
		$('.header').css('border','0px');
		$('.bodyCover').css('top',$('.header').height());
		$('.footer').css('border','0px');
		$('.header').css('width','100%');
		$('.footer').css('width','100%');
	}
}