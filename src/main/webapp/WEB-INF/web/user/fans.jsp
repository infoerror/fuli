<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的粉丝</title>
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

								<div class="shadow-box">
									<div class="filter">
										<ul class="">
											<li><a href="/home/follows" class="active">我的关注</a></li>
											<li><a href="/home/fans">我的粉丝</a></li>
										</ul>
									</div>
									<!-- tab panes -->
									<div class="stream-list">
										<div id="loop-601" class="stream-item">
											<div class="blog-rank">
												<div title="似水流年" class="user">
													<a href="/ta/601"> <img
														src="/store/ava/000/00/06/01_100.jpg" class="img-circle">
													</a>
												</div>
											</div>
											<div class="summary"></div>
										</div>


									</div>
								</div>

								<ul class="pagination" id="data_page">
									<c:forEach begin="${startPage}" end="${endPage }" var="page">
										<li><a data-key="${page}">${page}</a></li>
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
