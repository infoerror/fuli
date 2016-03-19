<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>激活邮箱</title>
<%@ include file="../common/resource.jspf"%>
<script src="${pageContext.request.contextPath }/assets/js/register.js"></script>
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
				<div class="col-md-8">
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading">请验证您的邮箱!</div>
						<div class="panel-body">
							<label id="message"></label> <br> <a
								href="javascript:void(0)" id="sendEmailForRegister">点击发送注册邮件</a>
							<a href="${pageContext.request.contextPath }/login/"
								style="display:none" id="a_login">点击登录网站</a>
						</div>
					</div>
					<%@ include file="../common/rightWidget.jspf"%>
				</div>
			</div>
		</div>
	</div>
	<script>

	function active() {
		$.ajax({
			type : 'POST',
			url :  '${pageContext.request.contextPath}/api/register/sendEmailForRegister',
			dataType : 'json',
			complete : function(data) {
				var jsonData = eval("(" + data.responseText + ")");
				if (jsonData.code== 0) {
					$('#a_login').show();
					$("#sendEmailForRegister").hide();
					$("#message").text(jsonData.msg);
				}else{
					$("#message").text(jsonData.error);	
				}
			}
		});
	}
		$(document).ready(

		function() {
			$('#sendEmailForRegister').click(function() {
				active();
			})

		})
	</script>
	<%@ include file="../common/footer.jspf"%>
</body>
</html>
