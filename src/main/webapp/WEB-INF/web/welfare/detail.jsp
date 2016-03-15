<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>福利</title>
<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>/js/bootstrap.js"></script>
<link href="<%=basePath%>/css/layout.css" rel="stylesheet">
<link href="<%=basePath%>/css/style.css" rel="stylesheet">
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
	<jsp:include page="/WEB-INF/web/common/newnav.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			<div class="row">
				<div class="main clearfix">
					<!-- left -->
					<div class="col-md-9 side-left">

						<div class="shadow-box">
							<h1 class="post-title">${welfare.title }</h1>
							<div class="clearfix post-other">
								<span class="pull-left author"> <a target="_blank"
									href="/ta/97" class="author-name">${welfare.author.nickname }</a>
								</span>
								<time class="pull-left time">${welfare.publishTime }</time>
								<span class="pull-left time">浏览: ${welfare.viewCount }</span>
								<ul class="tags">
									<li><a href="/tag/穷懒/" class="tag tag-sm">穷懒</a></li>
									<li><a href="/tag/阅读/" class="tag tag-sm">阅读</a></li>
								</ul>
								<span class="pull-right action-box"></span>
							</div>
							<div class="post-frame">
								<div class="thumbs">
									<div class="single-post-media">
										<a href="/store/orig/2016/0308/08230554ql5a.jpg"
											title="你不只是穷，你还很懒"> </a>
									</div>
									<!-- .post-thumbnail -->
								</div>
								<div class="post-content">
								        ${welfare.content }
								</div>

								<div class="tc mt40">
									<a href="javascript:void(0);" class="icon-ding dib" rel="favor"
										data-id="1306"></a>
								</div>

								<div class="post-footer">
										<!-- Share Button END -->
									</div>
								</div>
							</div>
						</div>
						
					<!-- right sidebar-->
					<div class="col-md-3 side-right hidden-xs hidden-sm">
						<ul class="list-group about-user">
							<li class="list-group-item user-card">
								<div class="ava">
									<a href="/ta/97"> <img
										src="<%=basePath %>${welfare.author.imageUri }" class="img-circle">
									</a>
								</div>
								<div class="user-info">
									<div class="nk mb10">${welfare.author.nickname }</div>
									<div class="mb6">
										<a rel="follow" data-id="97" href="javascript:void(0);"
											class="btn btn-success btn-xs">+ 关注</a>
									</div>
								</div>
							</li>

							<li class="list-group-item">
								<div class="user-datas">
									<ul>
										<li><strong>13</strong><span>发布</span></li>
										<li class="noborder"><strong>3</strong><span>评论</span></li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
					</div>
					<!-- post/end -->

				</div>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer.jspf"%>
</body>
</html>
