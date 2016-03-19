<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>福利详情-${welfare.title }</title>
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
										src="${pageContext.request.contextPath }${welfare.author.imageUri }" class="img-circle">
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

	<%@ include file="../common/footer.jspf"%>
</body>
</html>
