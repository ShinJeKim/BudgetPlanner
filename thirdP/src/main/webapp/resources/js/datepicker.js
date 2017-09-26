(function($){
		var currentdate	= new Date();
		var calVal = "";
		$.fn.status = {
				year 	: currentdate.getFullYear().toString(),
				month 	: (parseInt(currentdate.getMonth())+1).toString(),
				day 	: (currentdate.getDate()).toString(),
				type	: 'ymd', 
				lang	: 'ko',
				pattern : 'single'
				
		};
		
		var createpicker ={
				
		
			//연달력	
			yearpicker: function(element,options){
			        options = $.extend({}, $.fn.status, options);
		        	
					var startYear = parseInt(options.year)-(parseInt(options.year)%10);
					var endYear = startYear+9;
	                                                            		
					calVal =  "	<div id='yearpicker'>              												";
					calVal += "		<ul class='upperselect'>       												";
					calVal += "			<li><input type='button' class='btncal' id='prev' value='◀'></li>    								";
					calVal += "			<li><input type='button' class='btncal' id='upper' value='"+startYear+" ~ "+endYear+"'></li>		";
					calVal += "			<li><input type='button' class='btncal' id='next' value='▶'></li>   								";
					calVal += "		</ul>                          												";
					calVal += "		<ul class='picker_contents'>        										";
					for(var i=0; i<12; i++){
						calVal += "<li><input type='button' class= btncal id='years' value='"+(startYear-1)+"' ></li>";
						startYear++
					}      				
					calVal +="</ul>";
					calVal += "</div>";	
					
					element.find('.datepicker').html(calVal);
				},//yearpicker	
			monthpicker: function(element,options){
				
					options = $.extend({}, $.fn.status, options);
					var year = options.year
					
					if(options.lang == "ch" && year ==  null){
							year = year+"年";
					}
					
					var monthName = [
									{'ko': "1월",	'en': "Jan",  'ch': "1月",},
									{'ko': "2월",	'en': "Feb",  'ch': "2月",},
									{'ko': "3월",	'en': "Mar",  'ch': "3月",},
									{'ko': "4월",	'en': "Apr",  'ch': "4月",},
									{'ko': "5월",	'en': "May",  'ch': "5月",},
									{'ko': "6월",	'en': "Jun",  'ch': "6月",},
									{'ko': "7월",	'en': "Jul",  'ch': "7月",},
									{'ko': "8월",	'en': "Aug",  'ch': "8月",},
									{'ko': "9월",	'en': "Sep",  'ch': "9月",},
									{'ko': "10월",	'en': "Oct",  'ch': "10月"},
									{'ko': "11월",	'en': "Nov",  'ch': "11月"},
									{'ko': "12월",	'en': "Dec",  'ch': "12月"}
							];
					
					calVal =  "	<div id='monthpicker'>              												";
					calVal += "		<ul class='upperselect'>       												";
					calVal += "			<li class='prev'><input type='button' class= btncal id='years' value='◀'></li>    								";
					calVal += "			<li class='upper'><input type='button' class= btncal id='years' value='"+year+"'></li>		";
					calVal += "			<li class='next'><input type='button' class= btncal id='years' value='▶'></li>   								";
					calVal += "		</ul>                          												";
					calVal += "		<ul class='picker_contents'>        										";                                                                                  
					for(var i=0;i<monthName.length;i++){
							var mo = "";
							if(options.lang == "ko"){
								mo = monthName[i].ko
							}else if(options.lang == "en"){
								mo = monthName[i].en
							}else if(options.lang == "ch"){
								mo = monthName[i].ch	
							}else{
								mo = monthName[i].ko
							}
							calVal += "<li class='months'><input type='button' class= btncal id='months' value='"+mo+"'></li>";
					}		
					calVal +="</ul>";
					calVal += "</div>";	
					element.find('.datepicker').html(calVal);
					},//monthpicker
					
				daypicker: function(element,options){
						
						options = $.extend({}, $.fn.status, options);
						
						var weeksName = [
							{'ko': "일",	'en': "Sun",  'ch': "日",},
							{'ko': "월",	'en': "Mon",  'ch': "月",},
							{'ko': "화",	'en': "Tue",  'ch': "火",},
							{'ko': "수",	'en': "Wed",  'ch': "水",},
							{'ko': "목",	'en': "Thu",  'ch': "木",},
							{'ko': "금",	'en': "Fri",  'ch': "金",},
							{'ko': "토",	'en': "Sat",  'ch': "土",},
						];
						var theDate = new Date(Date.parse(options.month+' 1,'+options.year));
						var theDay = theDate.getDay();
						var last = [31,28,31,30,31,30,31,31,30,31,30,31];
						if ((options.year%4==0 && options.year%100!=0)|| options.year%400===0) {
						    last[1] = 29;
						}
						var lastDate = last[options.month-1];
				
						
						calVal =  "<div id='daypicker'>              						";
						calVal += "<input type='hidden' id='thisdate'>";
						calVal += "	<ul class='upperselect'>       						    ";
						calVal += "		<li class='prev'><input type='button' class= btncal id='years' value='◀'></li>    		    ";
						calVal += "		<li class='upper'><input type='button' class= btncal id='years' value='"+options.year+"."+options.month+"'></li> ";
						calVal += "		<li class='next'><input type='button' class= btncal id='years' value='▶'></li>   		    ";
						calVal += "	</ul>                          						    ";
						calVal += "	<ul class='picker_contents'>        				    ";
						calVal += "   	<li class='contents_header'>                        ";
						calVal += "       	<ul class='dayOfWeeks'>                         ";
						for(var i=0; i<weeksName.length; i++){
								var weeks = "";
								if(options.lang == "ko"){
									weeks = weeksName[i].ko;
								}else if(options.lang == "en"){
									weeks = weeksName[i].en;
								}else if(options.lang == "ch"){
									weeks = weeksName[i].ch;
								}else{
									weeks = weeksName[i].ko;
								}	
							calVal += "<li>"+weeks+"</li>";
							}
						calVal +="</ul>";
						calVal +="</li>";//contents_header
						calVal +="<li id='conti'>";
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
						}//daypicker
						
		};
		
		
		
		$.fn.datepicker = function(options){
			 var element = $(this);
			 options = $.extend({}, $.fn.status, options);
			
			if(options.month.toString().length == 1){
				options.month = '0'+options.month;
			}
			if(options.day.toString().length == 1){
				options.day = '0'+options.day;
			}
			
			element.html("<input type='hidden' id='currentDate'><div class='datepicker'></div>")

			$('#currentDate').val(options.year+"."+options.month+"."+options.day);
			 
			if(options.type == 'y'){
				return createpicker.yearpicker(element);
			}else if(options.type == 'm'){
				return createpicker.monthpicker(element);
			}else if(options.type == 'd'){
				return createpicker.daypicker(element);
			}
		};
})(jQuery);		

