<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../common/resource.jspf"%>
<title>福利</title>
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
			<div class="row container-fluid projects">
				<%@ include file="/WEB-INF/web/common/userNotice.jspf"%>
				<div class="col-md-12">
					<div class="row container-fluid">
						<div class="row">
							<%@ include file="../common/usernav.jspf"%>
							<div class="col-md-10">
								<div class="container-fluid">
									<div class="row">
										<div class="col-md-3" style="text-align:left">
											<img src="${pageContext.request.contextPath }${user.avatarUrl}">
										</div>
										<div class="col-md-9">
											<div class="container-fluid">
												<div class="col-md-12">
													<h4>基本信息</h4>
													<table class="table">
														<tr style="">
															<td>用户名:</td>
															<td>${user.username}</td>
														</tr>
														<tr>
															<td>昵称:</td>
															<td>${userInfo.nickname }</td>
														</tr>
														<tr>
															<td>注册时间:</td>
															<td>${userInfo.registerTime }</td>
														</tr>
													</table>
												</div>
											</div>

										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<h2>个人详细信息</h2>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<table class="table">
												<tr style="">
													<td>手机号:</td>
													<td>${userInfo.phone }</td>
												</tr>
												<tr>
													<td>邮箱:</td>
													<td>${userInfo.email }</td>
												</tr>
												<tr>
													<td>个人说明:</td>
													<td>${userInfo.signature}</td>
												</tr>
											</table>
										</div>
									</div>
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
	</div>
	<%@ include file="../common/footer.jspf"%>
</body>
</html>
