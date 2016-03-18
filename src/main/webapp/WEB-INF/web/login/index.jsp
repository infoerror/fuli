<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="../common/resource.jspf"%>
<title>福利</title>
<script src="<%=basePath%>/js/2016/register.js"></script>
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
		<div class="row container-fluid projects">
			<div class="col-md-8">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="username"
								id="username" placeholder="请输入用户名">
						</div>
						<div class="col-sm-4">
							<label style="color:red" id="e_username"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" name="password"
								id="password" placeholder="请输入密码">

						</div>
						<div class="col-sm-4">
							<label style="color:red" id="e_password"></label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="captcha"
								id="captcha" placeholder="请输入验证码">
						</div>
						<div class="col-sm-3">
							<img alt="captcha" id="captcha_img"
								src="<%=basePath%>/login/showCaptcha">
						</div>
						<div class="col-sm-4">
							<label style="color:red" id="e_captcha"></label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button id="login_button" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>




			</div>
			<div class="col-md-4">



				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">标签云</div>
					<div class="panel-body"></div>
				</div>

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">热门文章</div>
					<div class="panel-body">
						<ul class="list-group">
						</ul>
					</div>

				</div>
			</div>
		</div>

	</div>
</div>
<%@ include file="../common/footer.jspf"%>
	<script>
		$(document).ready(

		function() {

			$("#captcha_img").click(function() {
				changeImg();
			});

			$("#username").blur(function() {
				checkUsername();
			}).focus(function() {
				if ($("#password").val().length == 0) {
					hidePasswordError();
				}
				hideUsernameError();
			});

			$("#password").blur(function() {
				checkPassword();
			}).focus(function() {
				hideConfirmPasswordError();
				hidePasswordError();
			});


			$("#captcha").get(0).addEventListener("input", function(o) {
				check();
				currentState = 0;
			}, false);

			$("#captcha").blur(function() {
				checkCaptcha();
			}).focus(function() {
				hideCaptchaError();
			});

			$("#login_button").click(function(event) {
				
				event.preventDefault();
				if (checkLoginSubmit()) {
					login();
				}

			});

		});
	</script>
</body>
</html>
