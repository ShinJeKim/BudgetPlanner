<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="date">
</div>
<div id="balance">
	<label>잔고</label>
</div>
<div class="sub_menus">
	<ul>
		<li class="sub_menu">
			<input class="sub_selector" type="radio" name="menu" id="daily">
			<label class="sub_menu-label" for="daily"><a href="daily.do">일별</a></label>
		</li>
		<li class="sub_menu">
			<input class="sub_selector" type="radio" name="menu"  id="monthly">
			<label class="sub_menu-label" for="monthly"><a href="monthly.do">월별</a></label>
		</li>
		<li class="sub_menu">
			<input class="sub_selector" type="radio" name="menu" id="category">
			<label class="sub_menu-label" for="category"><a href="category.do">카테고리</a></label>
		</li>
	</ul>
</div>