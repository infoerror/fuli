<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>最新福利</title>
<%@ include file="../common/resource.jspf"%>
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
			<div class="row container-fluid">
				<div class="col-md-9">
					<div class="welfare-list">
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
										<a href="${pageContext.request.contextPath }/welfare/${welfare.id}" title="${welfare.title}">阅读全文</a>
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
				<%@ include file="../common/rightWidget.jspf"%>
			</div>
		</div>
	</div>
<%@ include file="../common/footer.jspf"%>
</body>
</html>
