<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/apps/resources/css/reset.css">
<script src="/apps/resources/js/jquery-3.2.1.min.js"></script>
<script>
	
	(function($){
		
		
		var currentdate = new Date();
		var calendar = "";  
		$.fn.status = {
				year 	: currentdate.getFullYear(),
				month 	: parseInt(currentdate.getMonth())+1,
				day 	: currentdate.getDate(),
				picker	: 'dpicker',
				lang	: 'ko'
				
		};
		
		
		var createpicker ={
			
			yearpicker: function(element,year,options){
				
					if(year ==  null){
						year = $.fn.status.year;
					}
					var startYear = parseInt(year)-(parseInt(year)%10);
					var endYear = startYear+9;
	                                                            		
					calendar =  "	<div id='yearpicker'>              												";
					calendar += "<input type='hidden' id='thisdate'>";
					calendar += "		<ul class='upperselect'>       												";
					calendar += "			<li class='prev'><input type='button' class= btncal id='years' value='◀'></li>    								";
					calendar += "			<li class='upper'><input type='button' class= btncal id='years' value='"+startYear+" ~ "+endYear+"'></li>		";
					calendar += "			<li class='next'><input type='button' class= btncal id='years' value='▶'></li>   								";
					calendar += "		</ul>                          												";
					calendar += "		<ul class='picker_contents'>        										";
					for(var i=0; i<12; i++){
						calendar += "<li class='years'><input type='button' class= btncal id='years' value='"+(startYear-1)+"'></li>";
						startYear++
					}      				
					calendar +="</ul>";
					calendar += "</div>";	
					
					element.find('.datepicker').html(calendar);
				},//yearpicker
			monthpicker: function(element,year,month,lang){
				//널값 초기화
				if(element == null){
					element = $(document);
				}
				if(lang == "ch" && year ==  null){
						year = $.fn.status.year+"年";
				}else if(year ==  null){
					year = $.fn.status.year;
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
				
				calendar =  "	<div id='monthpicker'>              												";
				calendar += "<input type='hidden' id='thisdate'>";
				calendar += "		<ul class='upperselect'>       												";
				calendar += "			<li class='prev'><input type='button' class= btncal id='years' value='◀'></li>    								";
				calendar += "			<li class='upper'><input type='button' class= btncal id='years' value='"+year+"'></li>		";
				calendar += "			<li class='next'><input type='button' class= btncal id='years' value='▶'></li>   								";
				calendar += "		</ul>                          												";
				calendar += "		<ul class='picker_contents'>        										";                                                                                  
				for(var i=0;i<monthName.length;i++){
						var mo = "";
						if(lang == "ko"){
							mo = monthName[i].ko
						}else if(lang == "en"){
							mo = monthName[i].en
						}else if(lang == "ch"){
							mo = monthName[i].ch	
						}else{
							mo = monthName[i].ko
						}
				calendar += "<li class='months'><input type='button' class= btncal id='years' value='"+mo+"'></li>";
				}		
				calendar +="</ul>";
				calendar += "</div>";	
				element.find('.datepicker').html(calendar);
				},//monthpicker	
			daypicker: function(element,year,month,day,lang){
				if(year ==  null){
					year = $.fn.status.year;
					month = $.fn.status.month;
					day = $.fn.status.day;
				}
				var weeksName = [
					{'ko': "일",	'en': "Sun",  'ch': "日",},
					{'ko': "월",	'en': "Mon",  'ch': "月",},
					{'ko': "화",	'en': "Tue",  'ch': "火",},
					{'ko': "수",	'en': "Wed",  'ch': "水",},
					{'ko': "목",	'en': "Thu",  'ch': "木",},
					{'ko': "금",	'en': "Fri",  'ch': "金",},
					{'ko': "토",	'en': "Sat",  'ch': "土",},
				];
				
			
			    // 현재 년(y)월(m)의 1일(1)의 요일을 구합니다.
				var theDate = new Date(year,(parseInt(month)-1),1);
				// 그 날의 요일은?
				var theDay = theDate.getDay();
				/* 현재 월의 마지막 일을 구합니다. */
				// 1월부터 12월까지의 마지막 일을 배열로 만듭니다.
				var last = [31,28,31,30,31,30,31,31,30,31,30,31];
				// 4년마다 있는 윤년을 계산합니다.(100년||400년 주기는 제외)   
				if ((year%4==0 && year%100!=0)|| year%400===0) {
				    last[1] = 29;
				}
			    // 현재 월의 마지막 일이 며칠인지 구합니다.
				var lastDate = last[month-1];
				/* 달력 연도/월을 표기하고 달력이 들어갈 테이블을 작성합니다. */
				
				//문자 결합 연산자를 사용해 요일이 나오는 행을 생성합니다.
				calendar =  "<div id='daypicker'>              						";
				calendar += "<input type='hidden' id='thisdate'>";
				calendar += "	<ul class='upperselect'>       						    ";
				calendar += "		<li class='prev'><input type='button' class= btncal id='years' value='◀'></li>    		    ";
				calendar += "		<li class='upper'><input type='button' class= btncal id='years' value='"+year+"."+month+"'></li> ";
				calendar += "		<li class='next'><input type='button' class= btncal id='years' value='▶'></li>   		    ";
				calendar += "	</ul>                          						    ";
				calendar += "	<ul class='picker_contents'>        				    ";
				calendar += "   	<li class='contents_header'>                        ";
				calendar += "       	<ul class='dayOfWeeks'>                         ";
				for(var i=0; i<weeksName.length; i++){
						var weeks = "";
						if(lang == "ko"){
							weeks = weeksName[i].ko;
						}else if(lang == "en"){
							weeks = weeksName[i].en;
						}else if(lang == "ch"){
							weeks = weeksName[i].ch;
						}else{
							weeks = weeksName[i].ko;
						}	
					calendar += "<li>"+weeks+"</li>";
				}
					calendar +="</ul>";
				calendar +="</li>";//contents_header
				calendar +="<li id='conti'>";
					calendar +="<ul id='thedays'>";
					for(var k=0; k<theDay; k++){
						calendar +="<li class='days'></li>";
					}
					for(var j=1; j<=lastDate; j++){
						
						calendar +="<li class='days'><input type='button' class= btncal id='years' value='"+j+"'></li>";
						
					}
					calendar +="</ul>";
				calendar +="</li>";
				calendar +="</ul>";
				calendar += "</div>";	
				    // 문서 출력!
				
				element.find('.datepicker').html(calendar);
				$('#thisdate').val(year+"."+month+"."+day);
				}//daypicker
			
			};//createpicker
			
		$.fn.datepicker = function(options){
			options = $.extend({}, $.fn.status, options);	
			if(options.picker == 'dpicker'){
				return $(this).mdpicker();
			}else{
				if($(this).attr('class') == 'mdpicker'){
					$(this).html("<div class='datepicker'></div>");
					createpicker.daypicker($(this));
				}else if($(this).attr('class') == 'ypicker'){
					$(this).html("<div class='datepicker'></div>");
					createpicker.yearpicker($(this));
				}else if($(this).attr('class') == 'ympicker'){
					$(this).html("<div class='datepicker'></div>");
					createpicker.monthpicker($(this));
				}
			}
			
			

				
				
					
		};
		$.fn.mdpicker = function(){
		     var htmlval = "";
		    	 htmlval +="<input id='startselect' type='button' value='시작일'>";
		    	 htmlval +="<input id='endselect' type='button' value='종료일'>";
		    	 htmlval +=	"<div class='datepicker'></div>";
			 $(this).html(htmlval);
			 createpicker.daypicker($(this));
			
			 };
	
	})(jQuery); 
	
	$(document).ready(function(){
		$(".ydpicker").datepicker({
			
		});
		$(".mdpicker").datepicker({
			
		});
		$(".ypicker").datepicker({
			picker: 'ym'
		});
		$(".ympicker").datepicker({
			picker: 'ym'
		});
		
		$(document).on("click",".years",function(){
			$("#text").val($(this).find("label").html());
		});
	
		
		
	});//document.ready
	

</script>
<style type="text/css">
.datepicker ul{
	font-size: 0px;
	text-align: left;
}
.datepicker ul>li{
	display: inline-table;
	vertical-align: middle;
	height:  30px;
	font-size: 1rem;
	border: 1px solid transparent;
	box-sizing: border-box;
	text-align: center;
	
}
.datepicker li>label{
	display: table-cell;
	vertical-align: middle;
}
.datepicker li>label:hover{
	cursor: pointer;
}
.upper:hover,.days:hover,.months:hover,.years:hover,.prev:hover,.next:hover{
	cursor: pointer;
	box-shadow: inset 0 0 10px aqua; 
	color: blue;
}
.years{
	width: 25%;
}
.months{
	width: 25%;
}
div{
	width: 300px;
}

.prev{
	width: 20%;
}
.next{
	width: 20%;
}
.upper{
	width: 60%;
}
.contents_header{
	width: 100%;
}
.dayOfWeeks>li{
	width: 14.28%;
}
.days{
	width: 14.28%;
}
.days:NTH-CHILD(7n+1) {
	color: red;
}
</style>
</head>
<body>
	<input type="text" id="text">
	<div class="ydpicker"></div>
	<div class="mdpicker"></div>
	<div class="ypicker"></div>
	<div class="ympicker"></div>
	
</body>
</html>