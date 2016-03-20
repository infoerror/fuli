<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>修改用户信息</title>
<%@ include file="/WEB-INF/web/common/resource.jspf"%>
<script
	src="${pageContext.request.contextPath }/assets/vendors/jcrop/jquery.jcrop.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/assets/js/avatar.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/assets/vendors/layer/layer.js"
	type="text/javascript"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->

</head>
<body>
	<%@ include file="../common/newNav.jspf"%>
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
											<li><a href="modifyBasicInfo">基本信息</a></li>
											<li><a href="modifyAvatar">修改头像</a></li>
											<li class="active"><a href="modifyPassword">修改密码</a></li>
										</ul>
									</div>
									<div class="panel-body">
										<div id="message"></div>
										<div id="passwd" class="tab-pane active">
											<form class="form-horizontal" method="post" action="password"
												id="pw">
												<div class="form-group">
													<label for="password" class="control-label col-lg-3">当前密码</label>
													<div class="col-lg-4 has-success">
														<input type="password" data-required=""
															placeholder="请输入当前密码" maxlength="18" name="oldPassword"
															class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="password" class="control-label col-lg-3">新密码</label>
													<div class="col-lg-4">
														<input type="password" data-required="" maxlength="18"
															placeholder="请输入新密码" name="password" id="password"
															class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="password2" class="control-label col-lg-3">确认密码</label>
													<div class="col-lg-4">
														<input type="password" data-description="passwd"
															data-describedby="message" data-conditional="confirm"
															maxlength="18" placeholder="请再输入一遍新密码" data-required=""
															name="password2" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<div class="text-center">
														<button class="btn btn-primary" id="confirmModify">提交</button>
													</div>
												</div>
												<!-- /form-actions -->
											</form>
										</div>
									</div>
									<!-- /panel-content -->
								</div>
								<!-- /panel-content -->

								<script type="text/javascript">
									$(function() {
										$("#confirmModify")
												.click(
														function(event) {
															event
																	.preventDefault();
															oldPass = $('#pw input:eq(0)')
																	.val();
															newPass = $(
																	'#pw input:eq(1)')
																	.val();
															newPass2 = $(
																	'#pw  input:eq(2)')
																	.val();

															if (oldPass.length<6 || oldPass.length>16) {
																layer
																		.msg("密码只能6到16位之间!");
																return;
															}

															if (newPass<6 || newPass>16) {
																layer
																		.msg("密码只能6到16位之间!");
																return;
															}

															if (newPass != newPass2) {
																layer
																		.msg("两次密码必须一致 !");
																return;
															}

															var info = {
																	oldPassword:oldPass,
																	newPassword:newPass,
																	confirmNewPassword:newPass2
															};
															$
																	.ajax({
																		url : '${pageContext.request.contextPath}/api/user/modifyPassword', // 跳转到 action
																		data : JSON
																				.stringify(info),
																		type : 'post',
																		cache : false,
																		dataType : 'json',
																		contentType : 'application/json',
																		success : function(
																				result) {
																		    var code= result.code;
																		    var msg=result.msg;
																			if(code==0){
																			    layer.msg('修改成功', function(){
																				//关闭后的操作
																				window.location.href='${pageContext.request.contextPath}/login/'
																				});
																			}else{
																				layer.msg(msg);
																			}
																			
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
	</div>

	<%@ include file="../common/footer.jspf"%>
</body>
</html>
