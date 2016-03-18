<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="/WEB-INF/web/common/resource.jspf"%>
<script src="${pageContext.request.contextPath }/assets/vendors/layer/layer.js" type="text/javascript"></script>
<title>修改用户信息</title>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->

</head>
<body>
	<jsp:include page="/WEB-INF/web/common/newnav.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
		<%@ include file="../common/userNotice.jspf"%>
			<div class="row container-fluid projects">
				<div class="col-md-12">
					<div class="row container-fluid">
						<div class="row">
							<%@ include file="../common/usernav.jspf"%>
							<div class="col-md-10">

								<div class="panel panel-default stacked">
									<div class="panel-heading">
										<ul class="nav nav-pills account-tab">
											<li class="active"><a href="#">基本信息</a></li>
											<li><a href="modifyAvatar">修改头像</a></li>
											<li><a href="password">修改密码</a></li>
										</ul>
									</div>
									<div class="panel-body">
										<div id="message"></div>
										<div class="tab-pane active" id="basic_info">
											<form id="pf" action="profile" method="post"
												class="form-horizontal">
												<div class="form-group">
													<label class="control-label col-lg-3" for="nickname">昵称</label>
													<div class="col-lg-4">
														<input class="form-control" name="name" value="${basicInfo.nickname }"
															maxlength="7" data-required="" type="text">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-lg-3" for="nickname">个性签名</label>
													<div class="col-lg-6">
														<textarea name="signature" class="form-control" rows="3"
															maxlength="128">${basicInfo.signature }</textarea>
													</div>
												</div>
												<div class="form-group">
													<div class="text-center">
														<button id="confirmModify" class="btn btn-primary">提交</button>
													</div>
												</div>
												<!-- /form-actions -->
											</form>
										</div>
									</div>
									<!-- /panel-content -->
								</div>
								<!-- /panel -->

								<script type="text/javascript">
									$(function() {
										$("#confirmModify").click(function(event){
											 event.preventDefault();
											 var info={
													nickname:$('#basic_info input').val(),
													signature:$('#basic_info textarea').val()
													 
											 };
											 $.ajax({
													url : "${pageContext.request.contextPath}/api/user/modifyBasicInfo", // 跳转到 action
													data : JSON.stringify(info) ,
													type : 'post',
													cache : false,
													dataType : 'json',
													contentType : 'application/json',
													success : function(result) {
														layer.msg(result.msg);
														
													},
													error : function() {
														alert("暂时无法链接服务器，请联系管理员")
													}
												});
											 
										})
									});
								</script>


							</div>
						</div>

					</div>



				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jspf"%>
</body>
</html>
