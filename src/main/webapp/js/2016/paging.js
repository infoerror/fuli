                         


function Pagination(currentPage,totalPages){
									
				this.setting={page:10};		
									
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