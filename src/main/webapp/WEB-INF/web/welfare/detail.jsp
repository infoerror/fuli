<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>福利详情-${welfare.title }</title>
<%@ include file="../common/resource.jspf"%>
<script src="${pageContext.request.contextPath}/assets/js/comment.js"></script>
<script type="text/javascript">
 	/* $(function(){
 	 	$("#comments").html('<img src="${pageContext.request.contextPath}/assets/images/load.gif" alt="加载中..." align="middle"/>');
 	}); */
 	var $respond;
 	var cp;
 	var welfare= new app.Welfare(${welfare.id},{userId:${logined_user.id==null?0:logined_user.id},nickname:'${logined_user.nickname}',avatarUrl:'${logined_user.avatarUrl}'});
 	$(function(){
 		$respond= $('.respond').first();
 		
 	})
	 	function echo(v) {
 		
 		    $data=$('#cmt'+v);
 	 		$data.append($respond);
 	 		$('#cmt'+v+' .respond').show();
 	 		$('#cancel-reply').css("display","inline");
  	 		$('#reply_id').val(v);
  	 		$('#user_id').val($data.attr('data-userId'));
  	 		$('#root_id').val($data.attr('data-rootId'));
  	 		$('#nickname').val($data.attr('data-username'));
 	 	    $('#confirm_reply').click(function(){
				 var replyId=$('#reply_id').val();
				 var userId=$('#user_id').val();
				 var nickname=$('#nickname').val();
				 var content =$('#content').val();
				 var rootId =$('#root_id').val();
				 var reply={replyId:replyId,userId:userId,nickname:nickname,rootId:rootId};
				 welfare.confirmComment(reply,content);
			 })
 	 		cp=v;
 	 	};
 	 	function unecho() {
 	 		$('#cmt'+cp+' .respond').remove();
 	 	}	 	
 	
 	
 </script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
</head>

<body>
	<div class="respond" style="display:none">
		<form method="post" target="_self" id="frmSumbit">
			<h3 class="base-tit">
				发表评论 <a onclick="unecho()" style="display:none"
					href="#divCommentPost" id="cancel-reply" rel="nofollow">取消回复</a>
			</h3>
			<div class="comt">
				<div class="comt-box">
					<textarea
						onkeydown="if(event.ctrlKey&amp;&amp;event.keyCode==13){document.getElementById('submit').click();};"
						tabindex="5" rows="4" id="content" name="content"
						class="comt-area"></textarea>
				</div>

				<div class="comt-ctrl">
					<input type="button" id="confirm_reply" value="发表评论" tabindex="5"
						id="submit" name="submit" class="comt-submit">
					<div class="comt-tips">
						<input type="hidden" id="reply_id" value="0"> <input
							type="hidden" id="user_id" value="0"> <input
							type="hidden" id="root_id" value="0"> <input
							type="hidden" id="nickname" value="0">
					</div>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../common/loginAlert.jspf"%>
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
								<div class="post-content">${welfare.content }</div>

								<div class="tc mt40">
									<a href="javascript:void(0);" class="icon-ding dib" rel="favor"
										data-id="1306"></a>
								</div>

							</div>
							<div style="margin:-15px 0 15px 0;" class="db_post"></div>
							<!-- comment/start -->
							<div class="post-footer">

								<div id="postcomments">
									<h3 id="comments" class="base-tit">网友评论</h3>
									<div id="respond" style="">
										<form method="post" target="_self">
											<h3 class="base-tit">
												发表评论 <a onclick="unecho()" style="display:none"
													href="#divCommentPost" id="cancel-root-reply" rel="nofollow">取消回复</a>
											</h3>
											<div class="comt">
												<div class="comt-box">
													<textarea
														onkeydown="if(event.ctrlKey&amp;&amp;event.keyCode==13){document.getElementById('submit').click();};"
														tabindex="5" rows="4" name="content"
														class="comt-area"></textarea>
												</div>

												<div class="comt-ctrl">
													<input type="button" id="confirm_root_reply" value="发表评论"
														tabindex="5" name="submit" class="comt-submit">
													<div class="comt-tips">
														<input type="hidden" id="reply_id" value="0"> <input
															type="hidden" id="user_id" value="0"> <input
															type="hidden" id="root_id" value="0"> <input
															type="hidden" id="nickname" value="0">
													</div>
												</div>
											</div>
										</form>
									</div>
									<ins id="AjaxCommentBegin" style="display:none;"></ins>
									<div id="comment_list"></div>
									<script type="text/javascript">
									
									   $(function(){
										 welfare.loadComments(1);
										 $('#confirm_root_reply').click(function(){
											welfare.confirmComment({},$('#respond .comt-area').val(),true); 
										 });
										 
									 });
									 
									 
									
									</script>

								</div>
							</div>
							<!-- comment/end -->

						</div>
					</div>

					<!-- right sidebar-->
					<div class="col-md-3 side-right hidden-xs hidden-sm">
						<ul class="list-group about-user">
							<li class="list-group-item user-card">
								<div class="ava">
									<a href="/ta/97"> <img
										src="${pageContext.request.contextPath }${welfare.author.avatarUrl }"
										class="img-circle">
									</a>
								</div>
								<div class="user-info">
									<div class="nk mb10">${welfare.author.nickname }</div>
									<div class="mb6">
										<a rel="follow" data-id="${welfare.author.id }"
											href="javascript:void(0);" class="btn btn-success btn-xs">+
											关注</a>
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
