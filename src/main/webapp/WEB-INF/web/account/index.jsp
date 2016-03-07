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

		<div class="bc-social">
			<div class="container">
				<ul class="bc-social-buttons">
					<li class="social-qq"><i class="fa fa-qq"></i> <span
						id="qqgroup">QQ1320020962&</span></li>

					<li class="social-weibo"><a href="http://115.28.1.19"
						title="IT詹士锋微博" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'masthead', 'click', 'masthead-新浪微博'])"><i
							class="fa fa-weibo"></i> 新浪微博：@IT詹士锋</a></li>

					<li class="social-weibo"><a href="http://115.28.1.19"
						title="IT詹士锋微博" target="_blank"
						onclick="_hmt.push(['_trackEvent', 'masthead', 'click', 'masthead-新浪微博'])"><i
							class="fa fa-weibo"></i> 新浪微博：@张广通_通广张</a></li>
				</ul>
			</div>
		</div>

		<div class="row container-fluid projects">

			<div class="projects-header page-header">

				<h3>禁止黄赌毒，从我做起！</h3>
			</div>

			<div class="col-md-12">
				<div class="row container-fluid">
				   <div class="row">
				   <div class="col-md-3">

						<ul class="nav nav-list">
							<li class="nav-header">操作列表</li>
							<li><a href="#">我的福利</a></li>
							<li><a href="#">我的余额</a></li>
							<li><a href="#">审核列表</a></li>
							<li><a href="<%=basePath%>/account/publishWelfare">发福利</a></li>
						</ul>
					</div>
					<div class="col-md-9">
					功能区！
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

</body>
</html>