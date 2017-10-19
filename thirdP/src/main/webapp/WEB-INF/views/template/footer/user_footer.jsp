<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="main_menus">
	<ul>
		<li class="menu">
			<input class="main_selector" type="radio" name="main_menu" id="BudgetPlanner">
			<label class="menu-label" for="BudgetPlanner"><a href="/apps/budget/daily.do">가계부</a></label>
		</li>
		<li class="menu">
			<input class="main_selector" type="radio" name="main_menu" id="mypage">
			<label class="menu-label" for="mypage"><a href="/apps/mypage.do">내정보</a></label>
		</li>
		<li class="menu">
			<input class="main_selector" type="radio" name="main_menu" id="logout">
			<label class="menu-label" for="logout"><a href="/apps/logout.do">logout</a></label>
		</li>
	</ul>
</div>