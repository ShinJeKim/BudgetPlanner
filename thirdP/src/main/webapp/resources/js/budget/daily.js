$(document).ready(function(){
	
	 $('#date').datepicker({
		 type	: 'ymd',
		 lang	: 'ko'
	 });
	$('#daily').attr('checked', true);
	$('#BudgetPlanner').attr('checked', true);
	$(document).find('.item_content>label>p').each(function(){
		var origin_text = $(this).html().toString();
		var first_row = origin_text.split('<br>');
		$(this).html(first_row[0]);
	});
	body_sizing();
	font_sizing();
	$(document).find('.dailyitem').each(function(){
		var paddingTop = ($(this).height()-parseInt($(this).find('.itemList>li').css('font-size').replace('px','')))/2;
		$(this).find('.itemList>li').css('padding-top',paddingTop);
		if($(this).height()<$(this).find('.itemList').height()){
				$(this).css('height',$(this).find('.itemList').height()+20);
			}
	
	});	
	$(window).resize(function(){
		body_sizing();
		font_sizing();
		$(document).find('.dailyitem').each(function(){
			var paddingTop = ($(this).height()-$(this).find('.itemList>li').css('font-size'))/2;
			$(this).find('.itemList>li').css('padding-top',paddingTop);
			if($(this).height()<$(this).find('.itemList').height()){
				$(this).css('height',$(this).find('.itemList').height()+20);
			}
		});	
	});
	
	var dd = $('#reg_dt').val().split("."); 
	$('#nowDate>label').html(dd[2]);
	$(document).find('.item_price').each(function(){
		if($(this).children('label').html().toString().substring(1,0) == "-"){
			$(this).children('label').html($(this).children('label').html().replace('-',''));
			$(this).css('color','red');
		}else{
			$(this).css('color','blue');
		}
	});
	
	$('#reg_dt').change(function(){
		 $.ajax({
            type:"POST",
            url:"dailly.do",
            dataType: "JSON",
            async: false,
            data:{
               "reg_dt"  : $('#reg_dt').val()
            },
            success: function(data){
           var datahtml = "";
	            for(var i=0;i<data.length;i++){
	            	datahtml += "<form class='dailyDatas' action='do_searchOne.do' method='post'>	"
	            	datahtml += "<input type='hidden' id='origin_cont' value='"+data[i].content+"'>"	
	            	datahtml += "<input type='hidden' id='daily_code' name='daily_code' value='"+data[i].daily_code+"'>"
	            	datahtml += "<input <input type='hidden' id='dt' name='reg_dt'>"
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
	            	datahtml += "</form>															"	
	            }
            datahtml += "<div id='blank'></div>"
           	$('#dailyList').html(datahtml);	
            },
            complete: function(data){
            	dd = $('#reg_dt').val().split(".");
            	$('#nowDate>label').html(dd[2]);	
        	 var total_out = 0;
           	 var total_in = 0;
           	 var total_sum = 0;
           	 body_sizing();
           	 font_sizing();
         	$(document).find('.item_content>label>p').each(function(){
        		var origin_text = $(this).html().toString();
        		var first_row = origin_text.split('<br>');
        		$(this).html(first_row[0]);
        	});
           	 $(document).find('.dailyitem').each(function(){
           		  	$(this).parent().find('#dt').val($('#reg_dt').val());
           			var paddingTop = ($(this).height()-parseInt($(this).find('.itemList>li').css('font-size').replace('px','')))/2;
           			$(this).find('.itemList>li').css('padding-top',paddingTop);
           			if($(this).height()<$(this).find('.itemList').height()){
           				$(this).css('height',$(this).find('.itemList').height()+20);
        			}
           	 });
           	 $(document).find('.item_price').each(function(){
   				if($(this).children('label').html().toString().substring(1,0) == "-"){
   					$(this).children('label').html($(this).children('label').html().replace('-',''));
   					$(this).css('color','red');
   					
   					total_out += parseInt($(this).children('label').html());
   				}else{
   					$(this).css('color','blue');
   					
   					total_in += parseInt($(this).children('label').html());
   				}		
   			});
           	 total_sum = (total_in - total_out);
           	 $('#total_in>label').html(total_in+"원");
           	 $('#total_out>label').html(total_out+"원");
           	 $('#total_sum>label').html(total_sum+"원");
           
           	
           }//complete
		 });
	});
	$(document).on('click','#update',function(){
		console.log($(this).closest('form'));
		$('#loadWork').val('reload');
		$(this).closest('form').submit(); //dailyDatas
	});
	$(document).on('click','#delete',function(){
		
		if (confirm("정말 삭제하시겠습니까??") == true){  
			$(this).closest('form').attr('action','delete.do');
			$(this).closest('form').submit(); 
		}else{
		    return;
		}
	});
	$(document).on('click','.itemList',function(event){
		if(event.stopImmediatePropagation){
			event.stopImmediatePropagation();
		}else{
			event.isImmediatePropagationEnabled = false;
		}
		$(document).find('.item_content>label>p').each(function(){
			var o_text = $(this).html().toString();
			var f_row = o_text.split('<br>');
			$(this).html(f_row[0]);
			$(this).css('white-space','nowrap');
		});
		var ocont = $(this).closest('.dailyDatas').find('#origin_cont').val();
		var cont = ocont.replace('<p>','').replace('</p>',''); 
		$(this).find('.item_content>label>p').css('white-space','normal');
		$(this).find('.item_content>label>p').html(cont);
		body_sizing();
		 $(document).find('.dailyitem').each(function(){
			  	$(this).parent().find('#dt').val($('#reg_dt').val());
				var paddingTop = ($(this).height()-parseInt($(this).find('.itemList>li').css('font-size').replace('px','')))/2;
				$(this).find('.itemList>li').css('padding-top',paddingTop);
				if($(this).height()<$(this).find('.itemList').height()){
					$(this).css('height',$(this).find('.itemList').height()+20);
			}
		 });
		 $(document).find('.up_del').hide();
		$(document).find('.dailyitem').css('margin-bottom',$('.bodyCover').height()*0.02);
		$(this).closest('form').find('.up_del').show();
		$(this).parent().css('margin-bottom','0px');
		$(this).closest('form').find('.up_del').css('margin-bottom',$('.bodyCover').height()*0.04);
	});
	$('#icon').click(function(){
		$('#search').attr('method','post');
		$('#search').attr('action','do_save.do');
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
	$('#total>li>label').css('font-size',font_size*2.5);
	$('#total>#nowDate>label').css('font-size',font_size*5);
	$(document).find('.itemList>li').css('font-size',font_size*2.1);
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
	$('#dailyList').css('margin-top',$('#dailyTitle').height());
	$(document).find('.dailyitem').css('width',width_body*0.99);
	$(document).find('.dailyitem').css('margin-left',width_body*0.005);
	$(document).find('.dailyitem').css('margin-bottom',height_body*0.02);
	$(document).find('.dailyitem').css('height',height_body*0.1);
	$(document).find('.up_del').css('height',height_body*0.06);
	$(document).find('.up_del').css('margin-left',width_body*0.005);
    $(document).find('.up_del').css('width',width_body*0.99+1);
    $(document).find('.up_del').css('display','none');
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
			$('.up_del').css('height',height_body*0.06);
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