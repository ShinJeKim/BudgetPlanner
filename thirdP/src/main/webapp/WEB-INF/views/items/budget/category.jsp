<%@page import="com.apps.common.StringUtil"%>
<%
/*
<받아와야될 인자 List>
1. page_num
2. page_size
3. mst_ct_id
4. dtl_ct_id
5. start_date
6. end_date
7. id
*/
 	//상수 paging bottom count
	int bottomCount = 10;

	//for default element
	int page_num = 1;
	int page_size = 10;
	//int totalCnt;
	String start_date = "2017-07-01";
	String end_date = "2017-09-28";
	String id = "id1";
	String mst_ct_id = "";
	String dtl_ct_id = "";

	//initializing default element
	page_num = Integer.parseInt(StringUtil.nvl(request.getParameter("page_num"), "1"));
	page_size = Integer.parseInt(StringUtil.nvl(request.getParameter("page_size"), "10"));
	//totalCnt = Integer.parseInt(StringUtil.nvl(request.getAttribute("totalCnt").toString(), ""));
	
	// 카테고리 param 받아오기
	mst_ct_id = StringUtil.nvl(request.getParameter("mst_ct_id"),""); 
	//String dtlList = StringUtil.nvl(request.getParameter("dtlList"), "");
	
	
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


	$(document).ready(function(){
		
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
							htmlval += "<option value="+i+">"+data[i]+"</option>"
						}
						$('#dtlList').html(htmlval);
					},
					complete: function(data){// 무조건 수행
						console.log($('#mst_ct_id').val());
					},
					error: function(xhr, status, error){
						console.log("error: "+error);
					}
				});// --ajax closed
			});// -- change category closed
			
			
			// do_searchList
			$("#do_searchList").on("click", function(){
				console.log($('.currentDate').html().toString()+"-0"+$('#end_month').val()+"-01");
				var st_date = $('.currentDate').html().toString()+"-0"+$('#start_month').val()+"-01";
				var ed_date = $('.currentDate').html().toString()+"-0"+$('#end_month').val()+"-01";
				
				$.ajax({
					type:"POST",
					url:"do_searchListt.do",
					dataType:"JSON",
					data:{
						
						
						"start_date": st_date.trim(),
						"end_date": ed_date.trim(),
						"mst_ct_id" : $('#mst_ct_id').val(),
						"dtlList"		: $('#dtlList').val()
					},
					success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수
						console.log("success data: "+data);
						console.log("data.length: "+data.length);
						//console.log("data.content: "+data.content);
						if(data.length > 0){
						var datahtml = "";
						for(var i=0; i<data.length; i++){
							
							console.log("data[i].id: "+data[i].mst_ct_id);
							
							datahtml += "<tr class='dataList'>"
							datahtml += "<td id='c_id' >"+data[i].id+"</td>"
							datahtml += "<td id='c_daily_code' >"+data[i].daily_code+"</td>"
							datahtml += "<td id='c_usage' >"+data[i].usage+"</td>"
							datahtml += "<td id='c_content' >"+data[i].content+"</td>"
							datahtml += "<td id='c_mst_ct_id' >"+data[i].mst_ct_nm+"</td>"
							datahtml += "<td id='c_dtl_ct_id' >"+data[i].dtl_ct_nm+"</td>"
							datahtml += "<td id='c_reg_dt' >"+data[i].reg_dt+"</td>"
							datahtml += "<td id='c_mod_dt' >"+data[i].mod_dt+"</td>"
							datahtml += "</tr >"
							
						}
						$('#tbody').html(datahtml);
						//console.log("datahtml: "+datahtml);
						}
					},
					complete: function(data){// 무조건 수행
						
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
		<thead>
			<!-- <th class="text-center">No.</th> -->
			<th class="text-center">아이디</th>
			<th class="text-center">식별코드</th>
			<th class="text-center">금액</th>
			<th class="text-center">수입/지출</th>
			<th class="text-center">카테고리</th>
			<th class="text-center">상세설명</th>
			<th class="text-center">등록일</th>
			<th class="text-center">수정일</th>
		</thead>
		<tbody id="tbody">
		<c:choose>
			<c:when test="${list.size()>0 }">
				<c:forEach var="CategoryVO" items="${list }">
					<form action="category.do" method="POST" name="${CategoryVO.id }">
						<tr>
							<td id="c_id" class="text-center"><c:out value="${CategoryVO.id }"></c:out></td>
							<td id="c_daily_code" class="text-center"><c:out value="${CategoryVO.daily_code }"></c:out></td>
							<td id="c_usage" class="text-left"><c:out value="${CategoryVO.usage }"></c:out></td>
							<td id="c_content" class="text-left"><c:out value="${CategoryVO.content }"></c:out></td>
							<td id="c_mst_ct_id" class="text-left"><c:out value="${CategoryVO.mst_ct_id }"></c:out></td>
							<td id="c_dtl_ct_id" class="text-left"><c:out value="${CategoryVO.dtl_ct_id }"></c:out></td>
							<td id="c_reg_dt" class="text-center"><c:out value="${CategoryVO.reg_dt }"></c:out></td>
							<td id="c_mod_dt" class="text-right"><c:out value="${CategoryVO.mod_dt }"></c:out></td>
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
<%-- 			<!-- Paging -->
			<div align="center">
				<%=StringUtil.renderPaging(totalCnt, page_num, page_size, bottomCount, "do_searchList.do", "do_search_page")%>
			</div>
		<!-- // Paging closed  --> --%>
		</div>
		<!--// div 3 closed-->
</div>
</body>
</html>


