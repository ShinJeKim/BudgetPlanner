<%@page import="com.apps.common.StringUtil"%>
<%
	// 상위 카테고리 param 받아오기
	String mst_ct_id = StringUtil.nvl(request.getParameter("mst_ct_id"),""); 
%>
<%
	//contextPath
	String contextPath = request.getContextPath();
	contextPath = "http://localhost:8080/" + contextPath;
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Pagination js 추가 -->
<script type="text/javascript" src="<%=contextPath%>/resources/js/common/jquery.twbsPagination.js"></script>

<title>카테고리별 조회</title>

<script>

//paging 이동
function do_search_page(url, PAGE_NUM) {
	console.log(url + "\t" + PAGE_NUM);
	var frm = document.frm;
	frm.PAGE_NUM.value = PAGE_NUM;
	frm.action = url;
	frm.submit();
}

//do_searchCategory
function do_searchCategory(){
	console.log("3. mst_ct_id: "+mst_ct_id.value);
	if(mst_ct_id != null){
		var frm = document.frm;
	}else{
		return;
	}
} 

//font sizing
function font_sizing(){
	var font_size = "";
	if(window.innerWidth > window.innerHeight){
		font_size = window.innerHeight*0.01;
	}else{
		font_size = window.innerWidth*0.01
	}
	$('#thead>tr>th').css('font-size',font_size*3);
	$('#tbody>tr>td').css('font-size',font_size*2.5);
}


	//jQuery Start
	$(document).ready(function(){
		
		// datePicker
		 $('#date').datepicker({
			 type	: 'y',
			 lang	: 'ko'
		 });
		$('#category').attr('checked', true);
		$('#BudgetPlanner').attr('checked', true);
		$('#currentDate').change(function(){
			var thisDate = $('#currentDate').val().toString().split(".");
			var selectedDate = thisDate[0];
			console.log(selectedDate);
		});
		
		console.log("ready: ");
		
			// change_category
			$('#mst_ct_id').change(function(){
				var mst_ct_id = $('#mst_ct_id').val();
				console.log("1. mst_ct_id: "+mst_ct_id);
				$.ajax({
					type: "GET",
					url: "do_searchCategory.do",
					dataType: "JSON",
					data:{
						"mst_ct_id" : $('#mst_ct_id').val()
					},
					success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수
						console.log("2. mst_ct_id: "+mst_ct_id);
						console.log("data: "+data);
						do_searchCategory(mst_ct_id);
	
						var dtlList = "${dtlList}";
						console.log("dtlList: "+"${dtlList}");
						var htmlval ="";
						for(var i=0;i<data.length;i++){
							console.log(data[i]);
							htmlval += "<option value="+data[i]+">"+data[i]+"</option>"
						}
						$('#dtlList').html(htmlval);
					},
					complete: function(data){// 무조건 수행
						console.log("mst_ct_id: "+$('#mst_ct_id').val());
					},
					error: function(xhr, status, error){
						console.log("error: "+error);
					}
				});// --ajax closed
			});// -- change category closed
			
			
			// do_searchList
			$("#do_searchList").on("click", function(){

				var st_date;
				var ed_date;
				
				// month 쿼리를 위한 0 붙이기 조건
				if((parseInt($('#start_month').val())<10)){
					st_date = $('.currentDate').html().toString()+"-0"+$('#start_month').val()+"-01";
					console.log("start_month"+$('.currentDate').html().toString()+"-0"+$('#start_month').val()+"-01");
				}else{
					st_date = $('.currentDate').html().toString()+"-"+$('#start_month').val()+"-01";
					console.log("start_month"+$('.currentDate').html().toString()+"-"+$('#start_month').val()+"-01");
				}

				if((parseInt($('#end_month').val())<10)){
					ed_date = $('.currentDate').html().toString()+"-0"+$('#end_month').val()+"-01";
					console.log("end_month"+$('.currentDate').html().toString()+"-0"+$('#end_month').val()+"-01");
				}else{
					ed_date = $('.currentDate').html().toString()+"-"+$('#end_month').val()+"-01";
					console.log("end_month"+$('.currentDate').html().toString()+"-"+$('#end_month').val()+"-01");
				}
					
				$.ajax({
					type:"POST",
					url:"do_searchList.do",
					dataType:"JSON",
					data:{			
						"start_date": st_date.trim(),
						"end_date": ed_date.trim(),
						"mst_ct_id" : $('#mst_ct_id').val(),
						"dtl_ct_nm": $('#dtlList').val(),
						"page_size": "10",
						"page_num": "1"
					},
					success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수
						
						console.log("success data: "+data);
						console.log("data.length: "+data.length);

						var totalCnt = data.length;
						var page_size = 10;
						var max_page = Math.ceil(totalCnt/page_size);
					
						/*var pageData = $('#pagination').data();
						
						if(typeof(pageData.twbsPagination) != 'undefined'){
							if(pageData.twbsPagination.options.totalPages != max_page){
								$('#pagination').twbsPagination('destroy');
							}
						} */
						
						// pagination 생성
						$('#pagination').twbsPagination({
							        totalPages: max_page,
							        visiblePages: 5
						 });
							

						if(data.length > 0){
	
						var datahtml = "";
						for(var i=0; i<data.length; i++){
							
							console.log("data[i].id: "+data[i].mst_ct_id);
							
							datahtml += "<tr class='dataList'>"
							datahtml += "<td id='c_mst_ct_id' >"+data[i].mst_ct_nm+"</td>"
							datahtml += "<td id='c_dtl_ct_nm' >"+data[i].dtl_ct_nm+"</td>"
							datahtml += "<td id='c_usage' >"+data[i].usage+"</td>"
							datahtml += "<td id='c_content' >"+data[i].content+"</td>"
							datahtml += "<td id='c_reg_dt' >"+data[i].reg_dt+"</td>"
							datahtml += "</tr >"
							
						}
						$('#tbody').html(datahtml);
						//console.log("datahtml: "+datahtml);
						}else{
							$('#tbody').html("<label style='font-size: 20px; color: red;'>검색결과가 없습니다.</label>");
						}
						
					
					},
					complete: function(data){// 무조건 수행
						font_sizing();
			
						
					},
					error: function(xhr, status, error){
						console.log("error: "+error);
					}
				}) // --ajax closed
			});// --do_searchList closed
			
			
	});//-- jQuery closed
</script>

</head>
<body>
<div style="background-color: white; height: 100%; width: 100%;">
	<!-- div 1: 검색조건, 조회버튼, 엑셀다운로드 || div 2: 검색결과 list || div 3: Paging -->
		<!-- div 1 for search condition -->
		<div style="background-color: aliceblue; height: 20%;">
				<div id="layout">
					<div id="header" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 50%;">상위분류
							<select name="mst_ct_id" id="mst_ct_id">
								<option value="">전체</option>
								<option value="10" <%if(mst_ct_id.equals("10")) out.print("selected='selected'"); %>>지출</option>
								<option value="20" <%if(mst_ct_id.equals("20")) out.print("selected='selected'"); %>>수입</option>
							</select>하위분류
							<select name="dtlList" id="dtlList">
							</select>
						</div>
						<div style="float: right; width: 40%">
							<select name="start_month" id="start_month" >
							<% 
							int start_month=1;
								for(start_month=1; start_month<=12; start_month++){
							%>
								<option id="s_month" value="<%=start_month%>"><%=start_month%>월</option>
							<%
								}
							%>
							</select>
							<select name="end_month" id="end_month">
							<% 
							int end_month=1;
								for(end_month=1; end_month<=12; end_month++){
							%>
								<option id="e_month" value="<%=end_month%>"><%=end_month%>월</option>
							<%
								}
							%>
							</select>
						</div>
					</div>
					<div id="content" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 60%">param 받아서 text에 넣기</div>
						<div style="float: right; width: 30%">
							<input type="button" id=do_searchList  value="조회">
						</div>
					</div>
					<div id="footer" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 60%">엑셀 다운로드</div>
					</div>
				</div>

		</div>
		<!--// div 1 closed-->
		
		<!-- div 2 for searchList-->
		<div style="background-color: Linen; height: 75%;">
		
		<!-- List table -->
		<table id="listTable" class="table table-bordered table-hover table-striped"  border="1" cellpadding="1" cellspacing="0">
		<thead id="thead">
			<tr>
				<th>수입/지출&nbsp;</th>
				<th>카테고리&nbsp;</th>
				<th>금액&nbsp;</th>
				<th>상세설명&nbsp;</th>
				<th>날짜&nbsp;</th>
			</tr>
		</thead>
		<tbody id="tbody">
		<c:choose>
			<c:when test="${list.size()>0 }">
				<c:forEach var="CategoryVO" items="${list }">
					<form action="category.do" method="POST" name="${CategoryVO.id }">
						<tr>
							<td id="c_mst_ct_id" class="text-left"><c:out value="${CategoryVO.mst_ct_id }"></c:out></td>
							<td id="c_dtl_ct_nm" class="text-left"><c:out value="${CategoryVO.dtl_ct_nm }"></c:out></td>
							<td id="c_usage" class="text-left"><c:out value="${CategoryVO.usage }"></c:out></td>
							<td id="c_content" class="text-left"><c:out value="${CategoryVO.content }"></c:out></td>
							<td id="c_reg_dt" class="text-center"><c:out value="${CategoryVO.reg_dt }"></c:out></td>
						</tr>
					</form>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="99">등록된 게시물이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	<!-- // List table closed-->
		
		</div>
		<!--// div 2 closed-->
		
		<!-- div 3 -->
		<div style="background-color: LightSteelBlue; height: 5%;">
		<!-- Paging -->
			<ul id="pagination" class="pagination"></ul>
		<!-- // Paging closed  --> 
		</div>
		<!--// div 3 closed-->
</div>
</body>
</html>


