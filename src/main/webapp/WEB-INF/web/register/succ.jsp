<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>注册成功</title>
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
	<div class="container">
			<div class="col-md-8">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">注册成功!</div>
					<div class="panel-body">
					 <a href="${pageContext.request.contextPath }/login/" id="a_login">立刻登录网站!</a>
					</div>
				</div>
			</div>
		</div>
	<script>
		$(document).ready(
		  function() {
              $('#sendEmailForRegister').click(function(){
            	   active();
              })
		  }
		)
		</script>
<%@ include file="../common/footer.jspf"%>
</body>
</html>
