<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>未通过的福利</title>
<%@ include file="/WEB-INF/web/common/resource.jspf"%>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
<script charset="utf-8" type="text/javascript"
	src="http://changyan.sohu.com/upload/changyan.js"></script>

</head>
<body>
<%@ include file="../common/newNav.jspf"%>
	<div class="wrap">
		<div class="container">
			

			<div class="row container-fluid projects">
              <%@ include file="/WEB-INF/web/common/userNotice.jspf"%>

				<div class="col-md-12">
					<div class="row container-fluid">
						<div class="row">
							<%@ include file="/WEB-INF/web/common/usernav.jspf"%>
							<div class="col-md-10">

								<ol class="breadcrumb">
									<li class="active">未审核福利</li>
								</ol>

								<table class="table">
									<thead>
										<tr>
											<th>标题</th>
											<th>发表时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="welfare_data">
										<c:forEach items="${welfares}" var="welfare">
											<tr>
												<td><a href="/">${welfare.title }</a></td>
												<td>${welfare.publishTime }</td>
												<td><a href="">更新</a> <a href="">删除</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<ul class="pagination" id="data_page">
									<c:forEach begin="${startPage}" end="${endPage }" var="page">
										<li><a data-key="${page}">${page}</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>

					</div>



					<div class="clearfix"></div>
					<blockquote class="pull-right">
						<p>多发福利，造福人类！</p>
						<small>本站宗旨</small>
					</blockquote>

				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">提交状态</h4>
				</div>
				<div class="modal-body" id="tips"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<%@ include file="../common/footer.jspf"%>
	<script>
	
	var pageCount=${totalPages};
	function getDate(tm){
		var tt=new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ")
		return tt;
		} 
	function getData(currentPage,totalPages){
		
		var welfareData={};
		welfareData.currentPage=currentPage;
		$
		.ajax({
			url : "${pageContext.request.contextPath}/api/unauditedWelfare/myList",
						data : JSON.stringify(welfareData),
						type : 'post',
						cache : false,
						dataType : 'json',
						contentType : 'application/json',
						success : function(result) {
							var no = result.code;
							if (no == 0) {
								createPagination(currentPage, pageCount);

								$('#welfare_data tr').remove();
								$(result.data)
										.each(
												function() {
													$dataEle = '<tr><td><a href="/">'
															+ this.title
															+ '</a></td>';
													$dataEle += '<td>'
															+ getDate(this.publishTime)
															+ '</td>';
													$dataEle += '<td><a href="">更新</a> <a href="">删除</a></td></tr>';

													$($dataEle).appendTo(
															"#welfare_data");

												})

								//
								//if(result.totalPages)

							} else {
								$('#tips').text(result.msg);
								$('#myModal').modal('show')
							}
						},
						error : function() {
							alert("暂时无法链接服务器，请联系管理员")
						}
					});

		}
		function createPagination(currentPage, totalPages) {

			$('#data_page li').remove();

			var startPage = 0;
			var endPage = 0;

			if (currentPage <= 3) {
				startPage = 1;
				if (totalPages < 5) {
					endPage = totalPages;
				} else {
					endPage = 5;
				}
			} else {
				if (totalPages < 5) {
					endPage = totalPages;
				} else {
					startPage = currentPage - 2;
					endPage = currentPage + 2;
				}
			}
			for (var i = startPage; i <= endPage; i++) {
				var temp = '<li';
				if (i == currentPage) {
					temp += ' class="active"';
				}
				temp += '><a data-key="'+i+'"';
			temp+='>' + i + '</a></li>';
				$(temp).appendTo("#data_page");
			}

			setPageListener();
		}

		function setPageListener() {

			$('#data_page li').click(function(e) {

				var page = $(this).find("a").attr("data-key");
				$(this).addClass("active");
				getData(page, pageCount);
			})

		}

		$(document).ready(

		function() {

			$('#data_page li').first().addClass("active");

			setPageListener();

		});
	</script>
</body>
</html>
