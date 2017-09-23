<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<tiles:insertDefinition name="head"/>
<script type="text/javascript">
	$(document).ready(function(){
		$("#monthly").attr('checked', true) ;
	});
	
	
</script>
<body>	
		<div style="height: 60px;">
			데이트피커
		</div>
		<div class="block">
		<ul>
			<li class="menu">
				<input class="selector" type="radio" name="menu" id="daily">
				<label class="menu-label" for="daily"><a href="/apps/header.jsp">일별</a></label>
				
			</li>
			<li class="menu">
				<input class="selector" type="radio" name="menu"  id="monthly">
				<label class="menu-label" for="monthly"><a href="?">월별</a></label>
			</li>
			<li class="menu">
				<input class="selector" type="radio" name="menu" id="category">
				<label class="menu-label" for="category"><a href="??">카테고리</a></label>
			</li>
		</ul>
	</div>
</body>
</html>