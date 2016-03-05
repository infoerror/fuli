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
<script src="<%=basePath%>/js/2016/register.js"></script>
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

				<h3>起风了，唯有努力生存。。</h3>
				<p>本xx后台采用的框架有Bootstrap3.0+jsp+springmvc+spring3+mybaits+lucene，运行环境是ubuntu+tomcat7.0</p>
			</div>

			<div class="col-md-8">

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">请验证您的邮箱!</div>
					<div class="panel-body">
					 <label id="message"></label>
					 <br>
					 <a href="javascript:void(0)" id="sendEmailForRegister">点击发送注册邮件</a>
					 <a href="<%=basePath %>/login/" style="display:none" id="a_login">点击登录网站</a>
					</div>
				</div>


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
		$(document).ready(

		  function() {
              $('#sendEmailForRegister').click(function(){
            	   active();
              })
              
              
		  }
		)
		</script>
</body>
</html>