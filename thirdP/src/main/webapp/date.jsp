<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/apps/resources/js/jquery-3.2.1.min.js"></script>

<script>
	(function($){
	$.fn.dpicker = function(){
		$(this).append("<div id='picker'></div>")
		
	/* 현재 날짜와 현재 달에 1일의 날짜 객체를 생성합니다. */
	var date = new Date(); // 날짜 객체 생성
	var y = date.getFullYear(); // 현재 연도
	var m = date.getMonth(); // 현재 월
	var d = date.getDate(); // 현재 일
	
		
		
	var datepicker ={
			
		yearly: function(y){
				
				var sty = parseInt(y)-(parseInt(y)%10+1);
				var edy = sty+10;
				
				cal = "<table id='yearpicker' style='border: 2px solid;'>                                                                                  "  ;
				cal += "		<thead style='text-align: center;'>                                                                        "   ;
				cal += "		<tr>                                                                                                       "   ;
				cal += "			<th colspan='4'>                                                                                       "   ;
				cal += "			<input type='button' value='<'><input type='button' value='"+sty+" ~ "+edy+"'><input type='button' value='>'>"   ;
				cal += "			</th>                                                                                                  "   ;
				cal += "		</tr>                                                                                                      "   ;
				cal += "	</thead>                                                                                                       "   ;
				cal += "	<tbody>                                                                                                        "   ;
				for(var i=0; i<3; i++){
					cal += "<tr>";
					for(var j=0; j<4; j++){
						cal += "<td><input class='cyears' type='button' value='"+sty+"'></td>";
						sty++;
					}
					cal += "</tr>";
				}
				cal += "</tbody>";
				cal += "</table>"; 
				
				$("#picker").html(cal);
				$("#status").val(y);
			},
	monthly: function(y,m){
		var cal = "";
		cal += "<table id='monthpicker' style='border: 2px solid;'>                                                                                                     ";
		cal += "		<thead style='text-align: center;'>                                                                                             ";
		cal += "			<tr>                                                                                                                        ";
		cal += "				<th colspan='4'>                                                                                                        ";
		cal += "				<input id=cprefyear type='button' value='<'><input id='cyearly' type='button' value='"+y+"'><input id=caftyear type='button' value='>'>";
		cal += "				</th>                                                                                                                   ";
		cal += "			</tr>                                                                                                                       ";
		cal += "		</thead>                                                                                                                        ";
		cal += "		<tbody>                                                                                                                         ";
		var k =0;
		var mo = ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"];
		for(var i=0; i<3; i++){
			cal += "<tr>";
			for(var j=0; j<4; j++){
				cal += "<td><input class='cmonth' type='button' value='"+mo[k]+"'></td>";
				k++
			}
			cal += "</tr>";
		}
		cal += "</tbody>";
		cal += "</table>"; 
	
		$("#picker").html(cal);
		$("#status").val(y+"."+m);
	}	
	};

	var cal = "";
				
	
	
	
	$(document).on("click",".cyears",function(){
		datepicker.monthly($(this).val(),1);
	
	});
	$(document).on("click","#cprefyear",function(){
		var prefyear = parseInt($("#cyearly").val());
		$("#cyearly").val(prefyear-1);
	});
	$(document).on("click","#caftyear",function(){
		var prefyear = parseInt($("#cyearly").val());
		$("#cyearly").val(prefyear+1);
	});
	
	$(document).on("click",".cmonth",function(){  
		var month =  $(this).val().replace("월","");
		var year = $("#cyearly").val();
		$("#status").val(year+"."+month);
		$("#monthpicker").remove();
		y=year;
		m=month;
		d=d;
		dati(y,m,d)
	});
	$(document).on("click","#da",function(){  
			if(m<=12 && m>1){
			m = m-1;
			var month = m;
			$(".calendar_table").remove();
			$("#status").val(y+"."+month);
			dati(y,m);
	}
			
	});
	
	$(document).on("click","#ja",function(){  
		if(m<12 && m>=1){
		m = parseInt(m)+1;
		var month = m;
		$(".calendar_table").remove();
		$("#status").val(y+"."+month);
		dati(y,m);
	}
	
	});
	$(document).on("click","#selmonth",function(){  
		datepicker.monthly(y,m);
	});
	$(document).on("click","#cyearly",function(){  
		datepicker.yearly(y);
	});
	
	$(document).on("click","#di",function(){  
		$("#monthpicker").remove();
		$("#status").val(y+"."+m+"."+$(this).val());
	});
	

function dati(y,m,d){
	
	    // 현재 년(y)월(m)의 1일(1)의 요일을 구합니다.
	var theDate = new Date(y,m,1);
	console.log(theDate);
	    // 그 날의 요일은?
	var theDay = theDate.getDay();
	 console.log(theDay);
	/* 현재 월의 마지막 일을 구합니다. */
	    // 1월부터 12월까지의 마지막 일을 배열로 만듭니다.
	var last = [31,28,31,30,31,30,31,31,30,31,30,31];
	    // 4년마다 있는 윤년을 계산합니다.(100년||400년 주기는 제외)
	    
	if ((y%4==0 && y%100!=0 )|| y%400===0) {
		
	    last[1] = 29;
	}
	    // 현재 월의 마지막 일이 며칠인지 구합니다.
	var lastDate = last[m-1];
	
	
	/* 현재 월의 달력에 필요한 행의 개수를 구합니다. */
	    // theDay(빈 칸의 수), lastDate(월의 전체 일수)
	var row = Math.ceil((theDay+lastDate)/7);
	
	
	/* 달력 연도/월을 표기하고 달력이 들어갈 테이블을 작성합니다. */
	
	    //문자 결합 연산자를 사용해 요일이 나오는 행을 생성합니다.
	var cal = "<table class='calendar_table' style='border: 2px solid;'>";
	cal += "		<thead style='text-align: center;'>                                                                        "   ;
	cal += "		<tr>                                                                                                       "   ;
	cal += "			<th colspan='7'>                                                                                       "   ;
	cal += "			<input id='da' type='button' value='<'><input id='selmonth' type='button' value='"+y+"."+m+"'><input id='ja' type='button' value='>'>"   ;
	cal += "			</th>                                                                                                  "   ;
	cal += "		</tr>                                                                                                      "   ;
	cal += "<tr>";
	cal += "<th>일</th>";
	cal += "<th>월</th>";
    cal += "<th>화</th>";
    cal += "<th>수</th>";
	cal += "<th>목</th>";	
	cal += "<th>금</th>";
	cal += "<th>토</th>";
	cal += "</tr>";
	cal += "<tbody>"
	
	
	    // 달력에 표기되는 일의 초기값!
	var dNum = 1;
	for (var i=1; i<=row; i++) { // 행 만들기
		cal += "<tr class='dayss'>";
	    for (var k=1; k<=7; k++) { // 열 만들기
	        // 월1일 이전 + 월마지막일 이후 = 빈 칸으로!
	        if((i==1 && k<=theDay )|| dNum>lastDate) {
	        	cal += "<td> &nbsp; </td>";
	        } else {
	        	cal += "<td><input id='di' type='button' value ='" + dNum + "' ></td>";
	            dNum++;
	        }
	    }
	    cal += "</tr>";
	}
	cal += "</tbody>";
	cal += "</table>";
	    // 문서 출력!
	$("#picker").append(cal);
	}
	
	if($(this).attr('id') == 'ping'){
	  	return dati(y,m);
	}

};
})(jQuery);
	$(document).ready(function(){
	
		var	list = {
				
				data1: function() {
					alert("data1");
				},
				
				data2: function() {
					alert("data2");
				},

			};	
	
		list.data1();
	
		$("#ping").dpicker();
	});
</script>
<style>
#yearpicker>tbody>tr:first-of-type>td:first-of-type>input:first-of-type{
	color: red;
}
#yearpicker>tbody>tr:last-of-type>td:last-of-type input{
	color: red;
}
.dayss>td:first-of-type input{
	color: red;
}
</style>
</head>
<body>
	<div id="dd">
	<input id='status' type='text'>
		<ul>
			<li id="ping"></li>
		</ul>
	</div>
	
	<div id="date1"></div>
</body>
</html>