<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
.calendar_table {
    border: 3px solid #000000;
    background-color: #D9534F;
    color: #ffffff;
    width: 300px;
    height: 180px;
    text-align: center;
}
.calendar_table tr,
.calendar_table th,
.calendar_table td {
    border: 1px solid #000000;
    text-align: center;
}

</style>
<script src="/apps/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	console.log("야 안뽑아내냐");

	/* 현재 날짜와 현재 달에 1일의 날짜 객체를 생성합니다. */
	var date = new Date(); // 날짜 객체 생성
	var y = date.getFullYear(); // 현재 연도
	var m = date.getMonth(); // 현재 월
	var d = date.getDate(); // 현재 일
	
	    // 현재 년(y)월(m)의 1일(1)의 요일을 구합니다.
	var theDate = new Date(y,m,1);
	    // 그 날의 요일은?
	var theDay = theDate.getDay();
	
	/* 현재 월의 마지막 일을 구합니다. */
	    // 1월부터 12월까지의 마지막 일을 배열로 만듭니다.
	var last = [31,28,31,30,31,30,31,31,30,31,30,31];
	    // 4년마다 있는 윤년을 계산합니다.(100년||400년 주기는 제외)
	    
	if ((y%4==0 && y%100!=0 )|| y%400===0) {
		
	    last[1] = 29;
	}
	    // 현재 월의 마지막 일이 며칠인지 구합니다.
	var lastDate = last[m];
	
	
	/* 현재 월의 달력에 필요한 행의 개수를 구합니다. */
	    // theDay(빈 칸의 수), lastDate(월의 전체 일수)
	var row = Math.ceil((theDay+lastDate)/7);
	
	
	/* 달력 연도/월을 표기하고 달력이 들어갈 테이블을 작성합니다. */
	$("#dd").append("<h1>" + y + "." + (m+1) + "</h1>");
	    //문자 결합 연산자를 사용해 요일이 나오는 행을 생성합니다.
	var calendar = "<table class='calendar_table'>";
	calendar += "<tr>";
	calendar += "<th>일</th>";
	calendar += "<th>월</th>";
	calendar += "<th>화</th>";
	calendar += "<th>수</th>";
	calendar += "<th>목</th>";
	calendar += "<th>금</th>";
	calendar += "<th>토</th>";
	calendar += "</tr>";
	
	    // 달력에 표기되는 일의 초기값!
	var dNum = 1;
	for (var i=1; i<=row; i++) { // 행 만들기
	    calendar += "<tr>";
	    for (var k=1; k<=7; k++) { // 열 만들기
	        // 월1일 이전 + 월마지막일 이후 = 빈 칸으로!
	        if(i===1 && k<=theDay || dNum>lastDate) {
	            calendar += "<td> &nbsp; </td>";
	        } else {
	            calendar += "<td>" + dNum + "</td>";
	            dNum++;
	        }
	    }
	    calendar += "</tr>";
	}
	calendar += "</table>";
	    // 문서 출력!
	$("#dd").append(calendar);


});
</script>
<body>
	<div id="dd">
	</div>
</body>
</html>