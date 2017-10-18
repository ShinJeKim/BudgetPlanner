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
	$('#selectedDate').css('height',$('.header').height()*0.6);
	$('#balance').css('height',$('.header').height()*0.6);
}

// 엑셀 다운로드
function do_excelDown(){
	var frm = document.excel_frm;
	frm.action = "do_excelDown.do";
	frm.submit();
}


	//jQuery Start
	$(document).ready(function(){
		
		var clickFlag = 0;
		
		$('#whole_div').css('width',$('.bodyCover').width());
	$(window).resize(function(){
		$('#whole_div').css('width',$('.bodyCover').width());
	})
		// datePicker
		 $('#date').datepicker({
			 type	: 'y',
			 lang	: 'ko'
		 });
		$('#category').attr('checked', true);
		$('#BudgetPlanner').attr('checked', true);
		font_sizing();
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
						
						$('#e_dtl_ct_nm').val('전체');
						console.log("$('#e_dtl_ct_nm').val(): "+$('#e_dtl_ct_nm').val());
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

					
						// 기존페이지네이션 있을시 없애고 재생성
						if($('.pagination').data("twbs-pagination")){
		                    $('.pagination').twbsPagination('destroy');
		                }
							

						if(data.length > 0){
							
							var totalCnt = data[0].totalNo;
							var page_size = 10;
							var max_page = Math.ceil(totalCnt/page_size);	
							
							var datahtml = "";
							
							datahtml += ""
							
							clickFlag = 1;	
								
							for(var i=0; i<data.length; i++){
								var content = data[i].content.replace(/(<([^>]+)>)/gi,'');
								console.log("data[i].id: "+data[i].mst_ct_id);
								
								datahtml += "<tr class='dataList'>"
								datahtml += "<td id='c_no' >"+data[i].No+"</td>"
								datahtml += "<td id='c_mst_ct_id' >"+data[i].mst_ct_nm+"</td>"
								datahtml += "<td id='c_dtl_ct_nm' >"+data[i].dtl_ct_nm+"</td>"
								datahtml += "<td id='c_usage' >"+data[i].usage+"</td>"
								datahtml += "<td id='c_content' >"+content+"</td>"
								datahtml += "<td id='c_reg_dt' >"+data[i].reg_dt+"</td>"
								datahtml += "</tr >"
								
								
							}
							
								$('#tbody').html(datahtml);

								 $(document).find('#c_usage').each(function(){
						   				if($(this).html().toString().substring(1,0) == "-"){
						   					$(this).html($(this).html().replace('-',''));
						   					$(this).css('color','red');
						   				
						   					$(this).children('label').html(numberFormat($(this).children('label').html()));
						   				}else{
						   					$(this).css('color','blue');
						   					
						   					$(this).children('label').html(numberFormat($(this).children('label').html()));
						   				}		
						   			});
									
								$('#pagination').twbsPagination({
										        totalPages: max_page,
										        visiblePages: 5,
										        onPageClick: function(evt, page){
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
															"page_num": page
														},
														success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수
															
															
															console.log("success data: "+data);
															console.log("data.length: "+data.length);

															var totalCnt = data[0].totalNo;
															var page_size = 10;
															var max_page = Math.ceil(totalCnt/page_size);
														

															if(data.length > 0){
																
																var datahtml = "";
																
																datahtml += ""
																
																for(var i=0; i<data.length; i++){
																	
																	console.log("data[i].id: "+data[i].mst_ct_id);
																	var content = data[i].content.replace(/(<([^>]+)>)/gi,'');
																	datahtml += "<tr class='dataList'>"
																	datahtml += "<td id='c_no' >"+data[i].No+"</td>"
																	datahtml += "<td id='c_mst_ct_id' >"+data[i].mst_ct_nm+"</td>"
																	datahtml += "<td id='c_dtl_ct_nm' >"+data[i].dtl_ct_nm+"</td>"
																	datahtml += "<td id='c_usage' >"+data[i].usage+"</td>"
																	datahtml += "<td id='c_content' >"+content+"</td>"
																	datahtml += "<td id='c_reg_dt' >"+data[i].reg_dt+"</td>"
																	datahtml += "</tr >"
																	
																	
																}
																
																	$('#tbody').html(datahtml);

																	 $(document).find('#c_usage').each(function(){
															   				if($(this).html().toString().substring(1,0) == "-"){
															   					$(this).html($(this).html().replace('-',''));
															   					$(this).css('color','red');
															   				
															   					$(this).children('label').html(numberFormat($(this).children('label').html()));
															   				}else{
															   					$(this).css('color','blue');
															   					
															   					$(this).children('label').html(numberFormat($(this).children('label').html()));
															   				}		
															   			});
																
																// do_excelDown Btn event
																	$('#do_excelDown').click(function(){										
																		do_excelDown();
																	});
															}else{
																$('#tbody').html("<label style='font-size: 20px; color: red;'>검색결과가 없습니다.</label>");
															}	
														},
														complete: function(data){// 무조건 수행
														},
														error: function(xhr, status, error){
															console.log("error: "+error);
														}
													}) // --ajax closed
										        }
									 });
								
							// do_excelDown Btn event
								$('#do_excelDown').click(function(){
									do_excelDown();
								});		
						}else{
							$('#tbody').html("<label style='font-size: 20px; color: red;'>검색결과가 없습니다.</label>");
						}
					},
					complete: function(data){// 무조건 수행	
					},
					error: function(xhr, status, error){
						console.log("error: "+error);
					}
				}) // --ajax closed
			});// --do_searchList closed
			
			
			$(document).on('change','#mst_ct_id',function(){
				$('#e_mst_ct_id').val($(this).val());
				
			});
			
			$(document).on('change','#dtlList',function(){
				console.log("this.val: "+$(this).val());
				$('#e_dtl_ct_nm').val($(this).val());
			});
			
			$(document).on('change','#start_month',function(){
				var month = "";
				if($(this).val().length == 1){
					month = '0'+$(this).val();
				}else{
					month = $(this).val();
				}
				var st_dt = $(document).find('.currentDate').html().toString()+"-"+month+"-01";
				$('#e_start_date').val(st_dt.trim());
			});
			
			$(document).on('change','#end_month',function(){
				var month = "";
				if($(this).val().length == 1){
					month = '0'+$(this).val();
				}else{
					month = $(this).val();
				}
				var ed_dt = $(document).find('.currentDate').html().toString()+"-"+month+"-01";
				$('#e_end_date').val(ed_dt.trim());
			});
	});//-- jQuery closed


