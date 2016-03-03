<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>

<meta charset="utf-8">
<title>风之博客</title>
<link href="<%=basePath%>/css/bootstrap.css" rel="stylesheet">
<script src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>/js/bootstrap.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
s
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
		<div class="row">
			<div class="col-md-9">

				<ol class="breadcrumb">
					<li>文章标签</li>
					<li class="active">${at.typename }</li>
				</ol>

				<c:forEach items="${articlesOfType }" var="article">
					<h4>
						<a href="/article/showarticle/${article.aid }" target="new"
							style="text-decoration:none; color:#373434">${article.title }</a>
					</h4>
					<pre>${article.content }</pre>
					<span class="label label-default">阅读 ${article.visittime }</span>

					<span class="label label-default">栏目:${article.section.name }</span>
					<span class="label label-default">${article.publishtime }</span>

					<span class="pull-right"><a class="btn btn-default"
						href="/article/showarticle/${article.aid }" target="new">View
							details &raquo;</a></span>
					<h6 class="page-header"></h6>

				</c:forEach>
				<nav>
					<ul class="pagination">

					</ul>
				</nav>

			</div>
			<div class="col-md-3">

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">标签(${at.typename })·热门文章</div>
					<div class="panel-body">
						<ul class="list-group">

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
