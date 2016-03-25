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
					<article class="article">
						<header class="article-header">
							<h1 class="article-title">热门标签</h1>
						</header>
						<p class="article-meta"></p>
						<div class="article-entry">
							<div class="tags-cloud">
								<c:forEach var="tag" items="${tags}">
									<a class="tag-name tag-name-size-0" title="0"
										href="${pageContext.request.contextPath }/welfare/tag/${tag.name}">${tag.name}</a>
								</c:forEach>
							</div>
						</div>
					</article>
				</div>
				<%@ include file="../common/rightWidget.jspf"%>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jspf"%>
</body>
</html>
