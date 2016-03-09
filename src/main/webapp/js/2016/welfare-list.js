	window.pages=0;
							
								
								
	                       function createPagination(currentPage,totalPages){
									
									$('#data_page li').remove();
									
									var startPage=0;
									var endPage = 0;
									
									if(currentPage<=3){
										startPage = 1;
										if(totalPages<5){
											endPage = totalPages;
										}else{
											endPage =5;
										}
									}else{
										if(totalPages<5){
											endPage = totalPages;
										}else{
											startPage = currentPage-2;
											endPage = currentPage+2;	
										}
									}
									for(var i =startPage;i<=endPage;i++){
										$('<li><a class="pageLink">'+i+'</a></li>').appendTo("#data_page");	
									}
									
									
								}
								
								function getDate(tm){
									var tt=new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ")
									return tt;
									} 
						
								
								
									function getData(currentPage,totalPages){
										
										var welfareData={};
										welfareData.currentPage=currentPage;
										welfareData.totalPages=totalPages;
										$
										.ajax({
											url : "<%=basePath%>/unauditedWelfare/myList",
											data : JSON.stringify(welfareData),
											type : 'post',
											cache : false,
											dataType : 'json',
											contentType : 'application/json',
											success : function(result) {
												var no = result.error_no;
												if (no == 0) {
                                                    window.pages= result.totalPages;
													createPagination(currentPage,result.totalPages);

													$('#welfare_data tr').remove();
													$(result.data)
															.each(
																	function() {
																		$dataEle='<tr><td><a href="/">'
																		+ this.title
																		+ '</a></td>';
																		$dataEle+='<td>'+getDate(this.publishTime)+'</td>';
																		$dataEle+='<td><a href="">更新</a> <a href="">删除</a></td></tr>';
																		
																		$($dataEle)
																				.appendTo(
																						"#welfare_data");

																	})

													//
													// if(result.totalPages)

												} else {
													$('#tips').text(
															result.error);
													$('#myModal').modal('show')
												}
											},
											error : function() {
												alert("暂时无法链接服务器，请联系管理员")
											}
										});
							}

						});