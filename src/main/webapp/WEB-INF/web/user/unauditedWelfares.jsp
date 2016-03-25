<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>未通过的福利</title>
<%@ include file="/WEB-INF/web/common/resource.jspf"%>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
<script charset="utf-8" type="text/javascript"
	src="http://changyan.sohu.com/upload/changyan.js"></script>

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
							<%@ include file="/WEB-INF/web/common/usernav.jspf"%>
							<div class="col-md-10">

								<ol class="breadcrumb">
									<li class="active">未审核福利</li>
								</ol>

								<table class="table">
									<thead>
										<tr>
											<th>标题</th>
											<th>发表时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="welfare_data">
										<c:forEach items="${welfares}" var="welfare">
											<tr>
												<td><a href="/">${welfare.title }</a></td>
												<td><fmt:formatDate
											value="${welfare.publishTime }" type="both" /></td>
												<td><a href="">更新</a> <a href="">删除</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<ul class="pagination" id="data_page">
									<c:forEach begin="${startPage}" end="${endPage }" var="page">
										<li${currentPage==page?' class="active"':''}><a href="${pageContext.request.contextPath }/user/unauditedWelfares/${page}">${page}</a></li>
									</c:forEach>
								</ul>
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
