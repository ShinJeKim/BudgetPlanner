<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

	$(document).ready(function(){
		font_sizing();
		body_sizing();
		$(window).resize(function(){
			font_sizing();
			body_sizing();
		});
		
		//missing_id
		$("#btn_ID").on("click", function(){
			
			$.ajax({
				type: "POST",
				url: "do_findID.do",
				dataType: "html",
				async: false,
				data:{
					"name" : $("#name").val(),
					"email" : $("#email").val()
					
				},
				success: function(data){
					console.log(data);
					 $("#IDresult").val(data);
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("id찾기 에러");
				}
			});
		});//missing_id
		
		
		//missing_pw
		$("#btn_PW").on("click", function(){
			
			$.ajax({
				type: "POST",
				url: "do_findPW.do",
				dataType: "html",
				async: false,
				data:{
					"id" : $("#id").val(),
					"email" : $("#email").val()
					
				},
				success: function(data){
					console.log(data);
					 $("#PWresult").val(data);
				},
				complete: function(data){
					
				},
				error:function(xhr, status, error){
					alert("비밀번호찾기 에러");
				}
			});
		});//missing_pw
		
		function font_sizing(){
			var font_size = "";
			if(window.innerWidth > window.innerHeight){
				font_size = window.innerHeight*0.01;
			}else{
				font_size = window.innerWidth*0.01
			}
			$('html').css("font-size",font_size);
		}

		function body_sizing(){
			var width = window.innerWidth;
			var height = window.innerHeight;
			$('.body').css("width",'100%');
			
			if(window.screen.width>768 && width>768){
			$('body').css('width','60%');
			$('body').css('margin-left','20%');
			$('body').css('border','5px solid graytext');
			$('.body').css("width",'100%');
			}else{
				$('body').css('width','100%');
				$('body').css('border','0px');
				$('body').css('margin','0px');
			}
		}	
		
	});//JQuery

</script>


<form id="missingIDFrm" name="missingIDFrm" action="do_findID.do" method="post">
	<div>
		<div class="title">
			<label>아이디찾기</label>
		</div>
		<div>
			<input type="text" placeholder="이름" required autofocus
				name="name" id="name"  maxlength="30" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="email" id="email" maxlength="50"/>
		</div>
		<div>
			<input type="submit" id="btn_ID" value="확인" />
		</div>
		<div id="IDlabel">
			<c:if test="${message == 'idOK'}">
				<label id="IDresult" style="color: #77f799">아이디는  '${id}' 입니다</label>
			</c:if>
			<c:if test="${message == 'idNo'}">
				<label id="IDresult"  style="color: #f56360">이름과 이메일을 확인해 주세요</label>
			</c:if>
		</div>
	</div>
</form>
<form id="missingPWFrm" name="missingPWFrm" action="missingPW.do" method="post">
	<div>
		<div class="title">
			<label>비밀번호찾기</label>
		</div>
		<div>
			<input type="text" placeholder="ID" required autofocus
				name="id" id="id" maxlength="15" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="email" id=email maxlength="15"/>
		</div>
		<div>
			<input type="submit" id="btn_PW" value="확인" />
		</div>
	</div>
	<div id="PWlabel">
		<c:if test="${message == 'pwOK'}">
			<label id="PWresult" style="color: #77f799">비밀번호는  ${email} 으로 전송되었습니다.</label>
		</c:if>
		<c:if test="${message == 'pwNo'}">
			<label id="PWresult" style="color: #f56360">ID와 이메일을 확인해 주세요</label>
		</c:if>
	</div>
</form>