<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>福利</title>
<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/site.min.css" rel="stylesheet">
<script src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>/js/bootstrap.js"></script>

<script type="text/javascript"
	src="<%=basePath%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	//以前ckeditor写的js代码移步js/写过的js
	var ue = UE.getEditor('content');
</script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
<script charset="utf-8" type="text/javascript"
	src="http://changyan.sohu.com/upload/changyan.js"></script>
<script type="text/javascript">
	window.changyan.api.config({
		appid : 'cyryzf53o',
		conf : 'prod_a3df4ac1dd6bda8ad1e2bb464a44438f'
	});
</script>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/web/common/newnav.jsp"></jsp:include>


		<div class="row container-fluid projects">

			<div class="projects-header page-header">

				<h3>禁止黄赌毒，从我做起！</h3>
			</div>

			<div class="col-md-12">
				<div class="row container-fluid">
					<div class="row">
						<div class="col-md-2">

							<ul class="nav nav-list">
								<li class="nav-header">操作列表</li>
								<li><a href="#">我的福利</a></li>
								<li><a href="#">我的余额</a></li>
								<li class="active"><a href="#">审核列表</a></li>
								<li class="info"><a href="#">发福利</a></li>
							</ul>
						</div>
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
									  <td><a href="">更新</a> <a href="">删除</a></td></tr>
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

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			友情链接 <a class="pull-right" href="">申请入口</a>
		</div>
		<div class="panel-body">
			<a href="">马云个人博客</a> <a href="">马化腾个人博客</a> <a href="">王欣个人博客</a> <a
				href="">雷军个人博客</a> <a href="">李彦宏个人博客</a> <a href="">周鸿祎个人博客</a> <a
				href="">丁磊个人博客</a> <a href="">曹国伟个人博客</a> <a href="">张朝阳个人博客</a> <a
				href="">陈天桥个人博客</a> <a href="">更多</a>
		</div>
	</div>


	<div class="well">
		<p class="text-center">Copyright © fengblog.cn All Rights
			Reserved. xxx 内容版权所有，同时保留所有权利。个人博客免责声明</p>
	</div>
</div>
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
			url : "<%=basePath%>/unauditedWelfare/myList",
			data : JSON.stringify(welfareData),
			type : 'post',
			cache : false,
			dataType : 'json',
			contentType : 'application/json',
			success : function(result) {
				var no = result.error_no;
				if (no == 0) {
					createPagination(currentPage,pageCount);

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
					//if(result.totalPages)

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
			var temp= '<li';
			if(i==currentPage){
				temp+=' class="active"';
			}
		    temp+='><a data-key="'+i+'"';
			temp+='>'+i+'</a></li>';
			$(temp).appendTo("#data_page");	
		}
		
		setPageListener();
	}
	
	function setPageListener(){
		
		$('#data_page li').click(function(e){
		
			var page=$(this).find("a").attr("data-key");
			$(this).addClass("active");
			getData(page,pageCount);
		})
		
	}
	
			$(document)
					.ready(
					
							
							function() {

							
								$('#data_page li:first').addClass("active");
								
							
								setPageListener();
								
								
							

						});
	</script>
</body>
</html>
