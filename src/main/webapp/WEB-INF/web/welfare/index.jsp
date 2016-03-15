<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>福利</title>
<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/layout.css" rel="stylesheet">
<link href="<%=basePath%>/css/style.css" rel="stylesheet">
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
	<jsp:include page="/WEB-INF/web/common/newnav.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">


			<div class="row container-fluid projects">

				<div class="col-md-8">

					<div class="content">
						<c:forEach var="welfare" items="${results}">
							<article class="excerpt  cate1 auth1">
								<h2>
									<a href="welfare/${welfare.id}" title="${welfare.title}">${welfare.title}</a>
								</h2>
								<div class="info">
									<span class="spndate"><fmt:formatDate
											value="${welfare.publishTime }" type="both" /></span><span
										class="spnname"><a href="u/${welfare.author.id}">${welfare.author.nickname }</a></span><span
										class="spncomm"><a
										href="welfare/${welfare.id}#comments" title="查看10的评论">10条评论</a></span><span
										class="spnview">${welfare.viewCount}次浏览</span>
								</div>

								<div class="note">
									<p>${welfare.content}</p>
									<p class="readmore">
										<a href="welfare/${welfare.id}" title="${welfare.title}">阅读全文</a>
									</p>
								</div>
							</article>
						</c:forEach>

						<div class="pull-right">
							<nav>
								<ul class="pagination">
								</ul>
							</nav>
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

			

		</div>
	</div>
<%@ include file="../common/footer.jspf"%>
</body>
</html>
