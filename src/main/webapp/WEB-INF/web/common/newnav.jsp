<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div class="navbar-inverse">
	<div class="navbar-header">
		<a class="navbar-brand" href="<%=basePath%>">风之博客</a>
	</div>

	<form class="navbar-form navbar-left" action="/article/search"
		method="post">
		<div class="form-group">
			<input name="term" id="term" type="text" placeholder="输入你的关键词"
				class="form-control">
		</div>
		<button type="submit" class="btn btn-success">Search</button>
	</form>

	<div id="navbar" class="navbar-collapse collapse">

		<ul class="nav navbar-nav navbar-right">
			<li class="active"><a href="<%=basePath%>">首页</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">文章<span class="caret"></span></a>
				<ul class="dropdown-menu">
					
				</ul></li>
			<li><a href="/flagment/showflagments/0">碎语</a></li>
			<li><a href="/picture/listatblog/0">美图</a></li>
			<li><a href="/forward/message">留言</a></li>
			<li><a href="/forward/about">关于</a></li>
			<li><a href="/mail/takeui">订阅</a></li>
		</ul>
	</div>
</div>