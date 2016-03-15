<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div class="top-wrap">
    <nav role="navigation" class="navbar navbar-inverse">
    	<!--[if lt IE 9]>
<div class="alert alert-danger alert-dismissible fade in" role="alert" style="margin-bottom:0">
      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
      <strong>您正在使用低版本浏览器，</strong> 在本页面的显示效果可能有差异。
        建议您升级到
        <a href="http://www.google.cn/intl/zh-CN/chrome/" target="_blank">Chrome</a>
        或以下浏览器：
        <a href="www.mozilla.org/en-US/firefox/‎" target="_blank">Firefox</a> /
        <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> /
        <a href="http://www.opera.com/" target="_blank">Opera</a> /
        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie" target="_blank">Internet Explorer 9+</a>
</div>
<![endif]-->
    	<div class="container">
    		<div class="navbar-header">
    			<button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
    				<span class="sr-only">Toggle navigation</span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    			</button>
    		</div>
    		<div class="navbar-collapse collapse" id="navbar">
    			<ul class="nav navbar-nav">
										
										<li>
    					<a target="_self" href="/g/blog">流量</a>
    				</li>
										<li>
    					<a target="_self" href="/g/video">话费</a>
    				</li>
										<li>
    					<a target="_self" href="/g/ask">抽奖</a>
    				</li>
										<li>
    					<a target="_self" href="/tags">发现</a>
    				</li>
										<li>
    					<a target="_self" href="/gallery?g=2">最新</a>
    				</li>
										
    			</ul>
    			
    			<ul class="nav navbar-nav navbar-right sign">
    				    				<li><a class="signin" href="/login">登录</a></li>
                    <li><a class="signup" href="/reg">注册</a></li>
    				    			</ul>
    		</div>
    	</div>
    </nav>
</div>