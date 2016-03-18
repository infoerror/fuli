<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>发福利</title>
<%@ include file="/WEB-INF/web/common/resource.jspf"%>

<script type="text/javascript"
	src="<%=basePath%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	//以前ckeditor写的js代码移步js/写过的js
	var ue = UE.getEditor('content',{
		initialFrameWidth:"600px" //初始化选项
	});
</script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
</head>
<body>
	<%@ include file="/WEB-INF/web/common/newnav.jsp"%>
	<div class="wrap">
		<div class="container">
			<div class="row container-fluid">
				<%@ include file="/WEB-INF/web/common/userNotice.jspf"%>
				<div class="col-md-12">
					<div class="row container-fluid">
						<div class="row">
							<%@ include file="/WEB-INF/web/common/usernav.jspf"%>
							<div class="col-md-10" id="publish_welfare">
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
												<div name="content" id="content" style="width:600px"></div>
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




						<div class="clearfix"></div>
						<blockquote class="pull-right row">
							<p>多发福利，造福人类！</p>
							<small>本站宗旨</small>
						</blockquote>

					</div>

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
<%@ include file="/WEB-INF/web/common/footer.jspf"%>
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
								welfare.content = $("#ueditor_textarea_content")
										.val();

								welfare.welfareTagIds = [];
								var i = 0;
								$('#welfareTagIds option:selected')
										.each(
												function() {
													welfare.welfareTagIds[i++] = $(
															this).val();
												})

								$
										.ajax({
											url : "${pageContext.request.contextPath}/welfare/addWelfare",
											data : JSON.stringify(welfare),
											type : 'post',
											cache : false,
											dataType : 'json',
											contentType : 'application/json;charset=utf-8',
											success : function(result) {
												var no = result.error_no;
												if (no == 0) {
													state = 0;
													$('#tips').text(result.msg);
													$('#myModal').modal('show')
												} else {
													state = -1;
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

							$("#submit_welfare").click(function(event) {

								event.preventDefault();
								publishWelfare();

							});

						});
	</script>
</body>
</html>
