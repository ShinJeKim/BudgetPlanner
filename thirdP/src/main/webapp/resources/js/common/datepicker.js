(function($){
		var currentdate	= new Date();
		var calVal = "";
		$.fn.status = {
				year 	: currentdate.getFullYear().toString(),
				month 	: (parseInt(currentdate.getMonth())+1).toString(),
				day 	: (currentdate.getDate()).toString(),
				type	: 'ymd', 
				lang	: 'ko',
				pattern : 'single',
				monthName : [
					{'ko': "1월" ,'en': "Jan",  'ch': "1月",},
					{'ko': "2월", 'en': "Feb",  'ch': "2月",},
					{'ko': "3월", 'en': "Mar",  'ch': "3月",},
					{'ko': "4월", 'en': "Apr",  'ch': "4月",},
					{'ko': "5월", 'en': "May",  'ch': "5月",},
					{'ko': "6월", 'en': "Jun",  'ch': "6月",},
					{'ko': "7월",  'en': "Jul",  'ch': "7月",},
					{'ko': "8월",  'en': "Aug",  'ch': "8月",},
					{'ko': "9월",  'en': "Sep",  'ch': "9月",},
					{'ko': "10월", 'en': "Oct",  'ch': "10月"},
					{'ko': "11월", 'en': "Nov",  'ch': "11月"},
					{'ko': "12월", 'en': "Dec",  'ch': "12月"}
				],
				weeksName: [
					{'ko': "일",	'en': "Sun",  'ch': "日",},
					{'ko': "월",	'en': "Mon",  'ch': "月",},
					{'ko': "화",	'en': "Tue",  'ch': "火",},
					{'ko': "수",	'en': "Wed",  'ch': "水",},
					{'ko': "목",	'en': "Thu",  'ch': "木",},
					{'ko': "금",	'en': "Fri",  'ch': "金",},
					{'ko': "토",	'en': "Sat",  'ch': "土",},
				],
				
		};
		
		var createpicker ={
				
		
			//연달력	
			yearpicker: function(element,options){
		        	
					var startYear = parseInt(options.year)-(parseInt(options.year)%10);
					var endYear = startYear+9;                                 		
					calVal =  "	<div id='yearpicker'>              												";
					calVal += "		<ul class='upperselect'>       												";
					calVal += "			<li><input type='button' class='btncal' id='prev' value='◀'></li>    								";
					calVal += "			<li><input type='button' class='btncal' id='upper' value='"+startYear+" ~ "+endYear+"'></li>		";
					calVal += "			<li><input type='button' class='btncal' id='next' value='▶'></li>   								";
					calVal += "		</ul>                          												";
					calVal += "		<ul class='picker_contents'>        										";
					var sty = startYear
					for(var i=0; i<12; i++){
						calVal += "<li><input type='button' class= btncal id='years' value='"+(sty-1)+"' ></li>";
						sty++
						
					}      				
					calVal +="</ul>";
					calVal += "</div>";	
					
					element.find('.datepicker').html(calVal);
					var width = window.innerWidth;
					var height = window.innerHeight;
					$('.btncal').css('height',height*0.04);
					
					$.fn.currentDate(element,options);
					$('.picker_contents>li').contents().each(function(){
						if($(this).val()==options.year){
							$(this).css('box-shadow','inset 0 0 10px blue');
							$(this).css('font-weight','bold');
						}else{
							$(this).css('box-shadow','');
							$(this).css('font-weight','normal');
						}
					});			
				
					var ypicker = element.find('.datepicker').children('div').attr('class','yearpicker');
					ypicker.find('#prev').click(function(){
						options.year = parseInt(options.year)-10;
						var prevst = parseInt(options.year)-(parseInt(options.year)%10);
						var preved = prevst+9;
						$('#upper').val(prevst+" ~ "+preved);
						$('.picker_contents>li').each(function(){
							$(this).children().attr('id','years').val(parseInt($(this).children().attr('id','years').val())-10);
						});
						options.year = prevst;
						$.fn.currentDate(element,options);
						$('.picker_contents>li').contents().each(function(){
							if($(this).val()==options.year){
								$(this).css('box-shadow','inset 0 0 10px blue');
								$(this).css('font-weight','bold');
							}else{
								$(this).css('box-shadow','');
								$(this).css('font-weight','normal');
							}
						});	
							
					});
					ypicker.find('#next').click(function(){
						options.year = parseInt(options.year)+10;
						var nextst = parseInt(options.year)-(parseInt(options.year)%10);
						var nexted = nextst+9;
						$('#upper').val(nextst+" ~ "+nexted);
						$('.picker_contents>li').each(function(){
							$(this).children().attr('id','years').val(parseInt($(this).children().attr('id','years').val())+10);
						});
						options.year = nextst;
						$.fn.currentDate(element,options);
						$('.picker_contents>li').contents().each(function(){
							if($(this).val()==options.year){
								$(this).css('box-shadow','inset 0 0 10px blue');
								$(this).css('font-weight','bold');
							}else{
								$(this).css('box-shadow','');
								$(this).css('font-weight','normal');
							}
						});	
						
					});
					$(document).click(function(e){
						if($('.datepicker').has(e.target).length === 0){
						element.find('.yearpicker').remove();
						}
					});
				},//yearpicker	
			monthpicker: function(element,options){
				
					var year = options.year
						
					if(options.lang == "ch"){
							year = year+"年";
					}
					
					
					
					calVal =  "	<div id='monthpicker'>              												";
					calVal += "		<ul class='upperselect'>       												";
					calVal += "			<li><input type='button' class= btncal id='prev' value='◀'></li>    								";
					calVal += "			<li><input type='button' class= btncal id='upper' value='"+year+"'></li>		";
					calVal += "			<li><input type='button' class= btncal id='next' value='▶'></li>   								";
					calVal += "		</ul>                          												";
					calVal += "		<ul class='picker_contents'>        										";                                                                                  
					for(var i=0;i<options.monthName.length;i++){
							var mo = "";
							if(options.lang == "ko"){
								mo = options.monthName[i].ko
							}else if(options.lang == "en"){
								mo = options.monthName[i].en
							}else if(options.lang == "ch"){
								mo = options.monthName[i].ch	
							}else{
								mo = options.monthName[i].ko
							}
							calVal += "<li><input type='button' class= btncal id='months' value='"+mo+"'></li>";
					}		
					calVal +="</ul>";
					calVal += "</div>";	
					element.find('.datepicker').html(calVal);
					var height = window.innerHeight;
					$('.btncal').css('height',height*0.04);
					
					$('.picker_contents>li').contents().each(function(){
						if(parseInt($('.picker_contents>li').contents().index($(this)))+1 == options.month){
							$(this).css('box-shadow','inset 0 0 10px blue');
							$(this).css('font-weight','bold');
						}else{
							$(this).css('box-shadow','');
							$(this).css('font-weight','normal');
						}
					});	
					
					var mpicker = element.find('.datepicker').children('div').attr('class','monthpicker');
					mpicker.find('#prev').click(function(){
						year = parseInt(year)-1;
						options.year = year;
						options.month = 1;
						$.fn.currentDate(element,options);	
						$('.picker_contents>li').contents().each(function(){
							if(parseInt($('.picker_contents>li').contents().index($(this)))+1 == options.month){
								$(this).css('box-shadow','inset 0 0 10px blue');
								$(this).css('font-weight','bold');
							}else{
								$(this).css('box-shadow','');
								$(this).css('font-weight','normal');
							}
						});	
						if(options.lang == "ch"){
							year = year+"年";
						}
						$('#upper').val(year);
					});
					mpicker.find('#next').click(function(){
						year = parseInt(year)+1;
						options.year = year;
						options.month = 1;
						$.fn.currentDate(element,options);
						$('.picker_contents>li').contents().each(function(){
							if(parseInt($('.picker_contents>li').contents().index($(this)))+1 == options.month){
								$(this).css('box-shadow','inset 0 0 10px blue');
								$(this).css('font-weight','bold');
							}else{
								$(this).css('box-shadow','');
								$(this).css('font-weight','normal');
							}
						});	
						if(options.lang == "ch"){
							year = year+"年";
					}
						$('#upper').val(year);
					});
					$(document).click(function(e){
						if($('.datepicker').has(e.target).length === 0){
						element.find('.monthpicker').remove();
						}
					});
				},	
				daypicker: function(element,options){
						
						 
						var theDate = new Date(options.year,parseInt(options.month-1),1);
						var theDay = theDate.getDay();
						var last = [31,28,31,30,31,30,31,31,30,31,30,31];
						if ((options.year%4==0 && options.year%100!=0)|| options.year%400===0) {
						    last[1] = 29;
						}
						var lastDate = last[options.month-1];
						var year = options.year;
						var month = options.month; 	
						if(options.lang == "ch"){
							year = year+"年";
							month = options.monthName[options.month-1].ch;
						}else if(options.lang == "en"){
							month = options.monthName[options.month-1].en;
						}
											
						calVal =  "<div id='daypicker'>              																					 ";
						calVal += "	<ul class='upperselect'>       						    															 ";
						calVal += "		<li><input type='button' class= btncal id='prev' value='◀'></li>    		    					 ";
						calVal += "		<li><input type='button' class= btncal id='upper' value='"+year+"."+month+"'></li> ";
						calVal += "		<li><input type='button' class= btncal id='next' value='▶'></li>   		    					 ";
						calVal += "	</ul>                          						    															 ";
						calVal += "	<ul class='picker_contents'>        				    															 ";
						calVal += "   	<li class='contents_header'>                        															 ";
						calVal += "       	<ul class='dayOfWeeks'>                         															 ";
						for(var i=0; i<options.weeksName.length; i++){
								var weeks = "";
								if(options.lang == "ko"){
									weeks = options.weeksName[i].ko;
								}else if(options.lang == "en"){
									weeks = options.weeksName[i].en;
								}else if(options.lang == "ch"){
									weeks = options.weeksName[i].ch;
								}else{
									weeks = options.weeksName[i].ko;
								}	
							calVal +=  "<li><label>"+weeks+"</label></li>";
							}
						calVal +="</ul>";
						calVal +="</li>";//contents_header
						calVal +="<li id='contents_body'>";
						calVal +="<ul id='thedays'>";
							for(var k=0; k<theDay; k++){
								calVal +="<li id='days'></li>";
							}
							for(var j=1; j<=lastDate; j++){
								
								calVal +="<li><input type='button' class= btncal id='days' value='"+j+"'></li>";
								
							}
						calVal +="</ul>";
						calVal +="</li>";
						calVal +="</ul>";
						calVal += "</div>";	
					
						element.find('.datepicker').html(calVal);
						var height = window.innerHeight;
						$('.dayOfWeeks>li').css('height',height*0.04);
						$('.btncal').css('height',height*0.04);
						
						var d = "";
						if(options.day.toString().substring(1,0) == 0){
							d = options.day.replace('0','');
						}else{
							d = options.day
						}
						$('#thedays>li').contents().each(function(){
							if($(this).val() == d){
								$(this).css('box-shadow','inset 0 0 10px blue');
								$(this).css('font-weight','bold');
							}else{
								$(this).css('box-shadow','');
								$(this).css('font-weight','normal');
							}
						
						});	
						
						
						var dpicker = element.find('.datepicker').children('div').attr('class','daypicker');	
						dpicker.find('#prev').click(function(){
								options.month = parseInt(options.month)-1;
								
								if(options.month<1){
								options.month = 12;
								  options.year = parseInt(options.year)-1;
								  
								}
								
								
								var year = options.year;
								var month = options.month; 	
								if(options.lang == "ch"){
									year = year+"年";
									month = options.monthName[options.month-1].ch;
									$('#upper').val(year+"."+month);
								}else if(options.lang == "en"){
									month = options.monthName[options.month-1].en;
									$('#upper').val(year+"."+month);
								}else{
									if(month.toString().length == 1){
										month = '0'+month;
									}	
									$('#upper').val(year+"."+month);
								}
								
								
								
								var theDate = new Date(options.year,parseInt(options.month-1),1);
								var theDay = theDate.getDay();
								var last = [31,28,31,30,31,30,31,31,30,31,30,31];
								if ((options.year%4==0 && options.year%100!=0)|| options.year%400===0) {
								    last[1] = 29;
								}
								var lastDate = last[options.month-1];
								var dayVal = "";
								dayVal += "   	<li class='contents_header'>                        															 ";
								dayVal += "       	<ul class='dayOfWeeks'>                         															 ";
								for(var i=0; i<options.weeksName.length; i++){
										var weeks = "";
										if(options.lang == "ko"){
											weeks = options.weeksName[i].ko;
										}else if(options.lang == "en"){
											weeks = options.weeksName[i].en;
										}else if(options.lang == "ch"){
											weeks = options.weeksName[i].ch;
										}else{
											weeks = options.weeksName[i].ko;
										}	
										dayVal += "<li><label>"+weeks+"</label></li>";
									}
								dayVal +="</ul>";
								dayVal +="</li>";//contents_header
								dayVal +="<li id='contents_body'>";
								dayVal +="<ul id='thedays'>";
									for(var k=0; k<theDay; k++){
										dayVal +="<li id='days'></li>";
									}
									for(var j=1; j<=lastDate; j++){
										
										dayVal +="<li><input type='button' class= btncal id='days' value='"+j+"'></li>";
										
									}
									dayVal +="</ul>";
									dayVal +="</li>";
									
							element.find('.picker_contents').html(dayVal);
							
							options.day = 1;
							$('#thedays>li').contents().each(function(){
								if($(this).val() == options.day){
									$(this).css('box-shadow','inset 0 0 10px blue');
									$(this).css('font-weight','bold');
								}else{
									$(this).css('box-shadow','');
									$(this).css('font-weight','normal');
								}
							});	
							$.fn.currentDate(element,options);	
								
							});//click prev
						dpicker.find('#next').click(function(){
								options.month = parseInt(options.month)+1;
								if(options.month>12){
									options.month = 1;
								  options.year = parseInt(options.year)+1;
								}
								
								
								var year = options.year;
								var month = options.month; 	
								if(options.lang == "ch"){
									year = year+"年";
									month = options.monthName[options.month-1].ch;
									$('#upper').val(year+"."+month);
								}else if(options.lang == "en"){
									month = options.monthName[options.month-1].en;
									$('#upper').val(year+"."+month);
								}else{
									if(month.toString().length == 1){
										month = '0'+month;
									}	
									$('#upper').val(year+"."+month);
								}
								
								
								
								
								var theDate = new Date(options.year,parseInt(options.month-1),1);
								var theDay = theDate.getDay();
								var last = [31,28,31,30,31,30,31,31,30,31,30,31];
								if ((options.year%4==0 && options.year%100!=0)|| options.year%400===0) {
								    last[1] = 29;
								}
								var lastDate = last[options.month-1];
								var dayVal = "";
								dayVal += "   	<li class='contents_header'>                        															 ";
								dayVal += "       	<ul class='dayOfWeeks'>                         															 ";
								for(var i=0; i<options.weeksName.length; i++){
										var weeks = "";
										if(options.lang == "ko"){
											weeks = options.weeksName[i].ko;
										}else if(options.lang == "en"){
											weeks = options.weeksName[i].en;
										}else if(options.lang == "ch"){
											weeks = options.weeksName[i].ch;
										}else{
											weeks = options.weeksName[i].ko;
										}	
										dayVal +=  "<li><label>"+weeks+"</label></li>";
									}
								dayVal +="</ul>";
								dayVal +="</li>";//contents_header
								dayVal +="<li id='contents_body'>";
								dayVal +="<ul id='thedays'>";
									for(var k=0; k<theDay; k++){
										dayVal +="<li id='days'></li>";
									}
									for(var j=1; j<=lastDate; j++){
										
										dayVal +="<li><input type='button' class= btncal id='days' value='"+j+"'></li>";
										
									}
									dayVal +="</ul>";
									dayVal +="</li>";
									
							element.find('.picker_contents').html(dayVal);
							options.day = 1;
							$('#thedays>li').contents().each(function(){
								if($(this).val() == options.day){
									$(this).css('box-shadow','inset 0 0 10px blue');
									$(this).css('font-weight','bold');
								}else{
									$(this).css('box-shadow','');
									$(this).css('font-weight','normal');
								}
							});	
							$.fn.currentDate(element,options);
								
							});//click next
							$(document).click(function(e){
								if($('.datepicker').has(e.target).length === 0){
								element.find('.daypicker').remove();
								}
							});
						}//daypicker
						
		};
		
		
	
		
		$.fn.ypicker = function(element,options){
			
			createpicker.yearpicker(element,options);
			element.on('click','#years',function(evnet){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				options.year = $(this).val().replace(/[^0-9]/g,'');
				$.fn.currentDate(element,options);
				
				element.find('.yearpicker').remove();
			});
			
		}
		
		$.fn.mpicker = function(element,options){
			
			createpicker.monthpicker(element,options);
			element.on('click','#months',function(event){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				if(options.lang == "en"){
					options.month = ($('.picker_contents>li').index($(this).parent())+1);
					$.fn.currentDate(element,options);
				}else{
					options.month = $(this).val().replace(/[^0-9]/g,'');
					$.fn.currentDate(element,options);
				}
				element.find('.monthpicker').remove();
			});
		}
		
		$.fn.dpicker = function(element,options){
			
			createpicker.daypicker(element,options);
			element.on('click','#days',function(){
				options.day = $(this).val().replace(/[^0-9]/g,'');
				$.fn.currentDate(element,options);
				element.find('.daypicker').remove();	
			});
			
		};
		
		
		
		$.fn.ympicker = function(element,options){
			
			
			createpicker.monthpicker(element,options);
			element.on('click','#months',function(event){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				if(options.lang == "en"){
					options.month = parseInt($('.picker_contents>li').contents().index($(this)))+1;
					$.fn.currentDate(element,options);
				}else{
					options.month = $(this).val().replace(/[^0-9]/g,'');
					$.fn.currentDate(element,options);
				}
				element.find('.monthpicker').remove();
				
			});
			element.on('click','#years',function(event){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				options.year = $(this).val().replace(/[^0-9]/g,'');
				$.fn.currentDate(element,options);
				createpicker.monthpicker(element,options);
			});
			element.on('click','#upper',function(evnet){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				createpicker.yearpicker(element,options);
			});
		};
		$.fn.ymdpicker = function(element,options){
			createpicker.daypicker(element,options);
			element.on('click','#months',function(event){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				if(options.lang == "en"){
					options.month = parseInt($('.picker_contents>li').contents().index($(this)))+1;
					$.fn.currentDate(element,options);
				}else{
					options.month = $(this).val().replace(/[^0-9]/g,'');
					$.fn.currentDate(element,options);
				}
				options.day = 1;
				createpicker.daypicker(element,options);
			});
			element.on('click','#years',function(event){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				options.year = $(this).val().replace(/[^0-9]/g,'');
				options.month = 1;
				$.fn.currentDate(element,options);
				createpicker.monthpicker(element,options);
			});
			element.on('click','#days',function(event){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				options.day = $(this).val().replace(/[^0-9]/g,'');
				$.fn.currentDate(element,options);
				element.find('.daypicker').remove();
			});
			element.on('click','#upper',function(event){
				if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
				if($(this).closest('div').attr('id') == 'monthpicker'){
					createpicker.yearpicker(element,options);
				}else if($(this).closest('div').attr('id') == 'daypicker'){
					createpicker.monthpicker(element,options);
				}
			});
		};
		$.fn.currentDate =  function(element,options){
			if(options.month.toString().length == 1){
				options.month = '0'+options.month;
			}
			if(options.day.toString().length == 1){
				options.day = '0'+options.day;
			}
			
			$('#currentDate').val(options.year+"."+options.month+"."+options.day).trigger('change');
			if($('#reg_dt').val() != undefined){
				if($('#reg_dt').val() != ""){
					if($('#loadWork').val() == "reload"){
						$('#currentDate').val($('#reg_dt').val()).trigger('change');
						$('#loadWork').val("normal");
					}else{
						$('#reg_dt').val($('#currentDate').val()).trigger('change');
					}		 
				}else{
					$('#reg_dt').val($('#currentDate').val()).trigger('change');
				}
			}
			if($('#month').val() != undefined && $('#month').val() != ""){
				$('#currentDate').val($('#month').val().substring(4,0)+"."+$('#month').val().substring(4,6)+".01").trigger('change');
				}
			
			
			var seperate = element.find('#currentDate').val().split('.');
				var y = seperate[0];
				options.year = y;
				var m = seperate[1];
				options.month = m;
				var d = seperate[2];
				options.day = d;
				if(options.type == 'y'){
					element.find('.currentDate').html('&emsp;'+y).trigger('change');
				}else if(options.type == 'm'){
					element.find('.currentDate').html('&emsp;'+y+'.'+m).trigger('change');
				}else if(options.type == 'd'){
					element.find('.currentDate').html('&emsp;'+y+'.'+m+'.'+d).trigger('change');
				}else if(options.type == 'ym'){
					element.find('.currentDate').html('&emsp;'+y+'.'+m).trigger('change');
				}else if(options.type == 'ymd'){
					element.find('.currentDate').html('&emsp;'+y+'.'+m).trigger('change');
				}
			
		
		};
		
		$.fn.datepicker = function(options){
			 var element = $(this);
			 options = $.extend({}, $.fn.status, options);
			 if(options.pattern == 'single'){
				 element.html("<div id='selectedDate'>" +
				 					"<label class='currentDate'></label>" +
				 					"<input type='hidden' id='currentDate'>" +
				 			 "</div>");
			 }else if(options.pattern == 'double'){
				 element.html("<label class='startDate'><img class='calSelect' id='stDate' src='/apps/resources/files/images/dateSelect.jpg'>~<img class='calSelect' id='edDate' src='/apps/resources/files/images/dateSelect.jpg'></label>");
			 }
			 
		    element.append("<div class='datepicker'></div>")
			$.fn.currentDate(element,options); 
		    
			
		    element.on('click','.currentDate',function(event){
		    	
		    	if(event.stopImmediatePropagation){
					event.stopImmediatePropagation();
				}else{
					event.isImmediatePropagationEnabled = false;
				}
		    	if(options.type == 'y'){
					return $.fn.ypicker(element,options);
				}else if(options.type == 'm'){
					return $.fn.mpicker(element,options); 
				}else if(options.type == 'd'){
					return $.fn.dpicker(element,options);
				}else if(options.type == 'ym'){
					return $.fn.ympicker(element,options);
				}else if(options.type == 'ymd'){
					return $.fn.ymdpicker(element,options);
				}
		    	
		    });
		    	
		    
		  			
		};
})(jQuery);		