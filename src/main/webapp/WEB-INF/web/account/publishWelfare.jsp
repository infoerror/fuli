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
								<li><a href="#">审核列表</a></li>
								<li><a href="#">发福利</a></li>
							</ul>
						</div>
						<div class="col-md-10">

							<ol class="breadcrumb">
								<li class="active">发福利</li>
							</ol>

							<div class="pull-left">
								<form class="form-horizontal" action="" method="post">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">福利标题</label>
										<div class="col-sm-10">
											<input name="title" id="title" type="text"
												class="form-control" id="inputEmail3" placeholder="">
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">福利类型</label>
										<div class="col-sm-10">
											<select name="welfareTagIds" id="welfareTagIds" multiple
												class="form-control">
												<c:forEach items="${welfareTags }" var="type">
													<option value="${type.id}">${type.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">详细描述</label>
										<div class="col-sm-10">
											<div name="content" id="content"></div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button name="addbut" id="submit_welfare"
												class="btn btn-default">发福利</button>
										</div>
									</div>
								</form>
							</div>

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
					<a href="">马云个人博客</a> <a href="">马化腾个人博客</a> <a href="">王欣个人博客</a>
					<a href="">雷军个人博客</a> <a href="">李彦宏个人博客</a> <a href="">周鸿祎个人博客</a>
					<a href="">丁磊个人博客</a> <a href="">曹国伟个人博客</a> <a href="">张朝阳个人博客</a>
					<a href="">陈天桥个人博客</a> <a href="">更多</a>
				</div>
			</div>


			<div class="well">
				<p class="text-center">Copyright © fengblog.cn All Rights
					Reserved. xxx 内容版权所有，同时保留所有权利。个人博客免责声明</p>
			</div>

		<script>
			$(document)
					.ready(
						
							
							function() {
								var state  = -1;
								
								
								$('#myModal').on('hidden.bs.modal', function () {
									if(state==0){
										window.location.href ="<%=basePath%>/welfare/";
									}
									
								})
								

								function publishWelfare() {
									var welfare = {};

									welfare.title = $("#title").val();
									welfare.content= $("#ueditor_textarea_content").val();
									
									welfare.welfareTagIds= [];
									var i=0;
                                   $('#welfareTagIds option:selected').each(function(){
                                	   welfare.welfareTagIds[i++]=$(this).val();
                                   })									
                                      
									$
											.ajax({
												url : "<%=basePath%>/welfare/addWelfare",
												data : JSON.stringify(welfare),
												type : 'post',
												cache : false,
												dataType : 'json',
												contentType : 'application/json;charset=utf-8',
												success : function(result) {
													var no = result.error_no;
													if (no == 0) {
													     state= 0;
													     $('#tips').text(result.msg);
													     $('#myModal').modal('show')
													} else {
														 state= -1;
														 $('#tips').text(result.error);
														 $('#myModal').modal('show')
													}
												},
												error : function() {
													alert("暂时无法链接服务器，请联系管理员")
												}
											});
								}

								$("#submit_welfare").click(function(event) {

									event.preventDefault();
									publishWelfare();

								});

							});
		</script>
</body>
</html>
