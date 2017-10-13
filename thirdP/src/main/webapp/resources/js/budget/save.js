$(document).ready(function(){
	$("#datepick").datepicker({
		 type	: 'ymd',
		 lang	: 'ko'
	});
	
	 font_size();
	 menu_size();
	 $('#income').attr('checked', true);
	 $('#BudgetPlanner').attr('checked', true);
	
	var cate = $(':input[name=main_cates]:radio:checked').val();
	
	
	if($('#main_cate').val() == ""){
		$('#main_cate').val("수입")
	} 
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
	
	
	
	function setcate(){$.ajax({
        type:"POST",
        url:"cate.do",
        dataType: "JSON",
        async: false,
        data:{
           "mst_ct_nm"  : $('#main_cate').val()
        },
        success: function(data){
       var datahtml = "";
            for(var i=1;i<=data.length;i++){
            	console.log(data[i]);
            	
            
            	/*datahtml += "<form class='dailyDatas' action='do_searchOne.do' method='post'>	"
            	datahtml += "<input type='hidden' id='daily_code' name='daily_code' value='"+data[i].daily_code+"'>"	
            	datahtml += "<div class='dailyitem'>                                            "
            	datahtml += " <ul class='itemList'>                                             "
            	datahtml +=	"  <li class='item_cate'><label>"+data[i].dtl_ct_nm+"</label></li>  "
            	datahtml +=	"  <li class='item_content'><label>"+data[i].content+"</label></li> "
                datahtml +=	"  <li class='item_price'><label>"+data[i].usage+"</label>원</li>    "
            	datahtml += " </ul>																"		
            	datahtml += "</div>																"
        		datahtml += "<div class='up_del'>                                             	"
            	datahtml += "  <input type='button' id='update' value='수정'>					"
            	datahtml += "  <input type='button' id='delete' value='삭제'>			        "	
            	datahtml += "</div>														    "
            	datahtml += "</form>															"*/	
            }/*
        datahtml += "<div id='blank'></div>"
       	$('#dailyList').html(datahtml);	*/
        },
        complete: function(data){
        
        }
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