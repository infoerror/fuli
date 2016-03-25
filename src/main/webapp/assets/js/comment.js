(function($, layer, app) {

	function Welfare(id, context) {
		if (typeof id == 'undefined')
			throw new Error('must pass id through new Welfare(id)');
		this.id = id;
		this.loadCommentsApi = app.base + '/api/welfare/' + this.id
				+ '/comments';
		this.confirmCommentApi = app.base + '/api/welfare/' + this.id
				+ '/comment';
		this.userId= context.userId;
		this.nickname = context.nickname;
		this.avatarUrl = context.avatarUrl;
		this.rootCommentCount=0;
		this.currentPage=1;
	}
	;

	Welfare.createCommentElements = function(data,context) {

		if (typeof data == 'undefined') {
			return '';
		}

		var rs = "";
		for (var i = 0; i < data.length; i++) {
			rs = rs + '<div class="comment" id="cmt' + data[i].id + '" data-userId="'+data[i].userId+'" data-username="'+data[i].nickname+'"';
			if(typeof context!='undefined' && typeof context.rootId!='undefined'){
				rs=rs+' data-rootId="'+context.rootId+'"';
			}else{
				rs=rs+' data-rootId="'+data[i].id+'"';
			}
			
			rs=rs+'>';
			rs = rs + '<div class="c-avatar">';
			rs = rs + '<img class="avatar" src="' + app.base
					+ data[i].avatarUrl + '" width="45" height="45" />';
			rs = rs + '</div>';
			rs = rs + '<div class="c-main" id="div-comment-' + data[i].id
					+ '">';

			rs = rs + '<div class="c-meta"><span class="c-author"><a href="'
					+ data[i].userId + '" rel="nofollow" target="_blank">'
					+ data[i].nickname + '</a>';
			if (typeof data[i].reply != 'undefined') {
				if (data[i].reply.userId != data[i].userId) {
					rs = rs + ' 回复: <a href="" style="color:#2d64b3">@'
							+ data[i].reply.nickname + '</a>';
				}
			}

			rs = rs + '</span><span class="c-time">' + data[i].commentTime
					+ '</span>';
			rs = rs + '</div>';

			rs = rs + '<div class="msgarticle">' + data[i].content;
			rs = rs
					+ Welfare.createCommentElements(data[i].comments,
							{rootId:data[i].id,commentCount:data[i].commentCount});
			rs = rs + '<a style="display:none;" id="AjaxCommentEnd'
					+ data[i].id + '"></a>';
			rs = rs + '</div>';

			rs = rs + '</div>';

			rs = rs + '<div class="c-footer">';

			rs = rs
					+ '<a class="comment-reply-link" href="#frmSumbit" onclick="echo('
					+ data[i].id + ')">回复</a>';
			rs = rs + '</div>';

			rs = rs + '</div>';
		}
		
		if(typeof context!='undefined' && typeof context.commentCount!='undefined'){
			if(context.commentCount>data.length)
			rs = rs + '<a class="sub-more" data-rootId="'+context.rootId+'">查看剩下的'+(context.commentCount-data.length)+'条评论</a>';
		}
		return rs;
	};
	
	Welfare.prototype.addCommentToPage=function(data){
		var me =this;
		var apd0 = Welfare.createCommentElements(data.data);
		this.rootCommentCount+=data.data.length;
		$("#comment_list").append(apd0);
		$(".sub-more").click(function(){
			var rootId=$(this).attr("data-rootId");
			$(this).remove();
			$.ajax({
				url : app.base+'/api/welfare/comment/'+rootId+'/remainingSubcomments',
				type : 'get',
				cache : false,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					 $('#div-comment-'+rootId+'>.msgarticle').append($(Welfare.createCommentElements(data,rootId)));
				},
				error : function() {
					alert("暂时无法链接服务器，请联系管理员");
				}
			});
			
		})
		
	    if(data.commentCount>this.rootCommentCount){
	    	$("#comment_list").append('<a id="root_more">加载更多评论</a>');
	    	$('#root_more').click(function(){
	    		$(this).remove();
	    		me.currentPage++;
	    		me.loadComments(me.currentPage);
	    	});
	    }else{
	    	$("#comment_list").append($('<div>评论已加载完毕</div>'))	    	
	    }
		
	};

	Welfare.prototype.loadComments = function(currentPage) {
        var me=this;
		var commentPage = {
			currentPage : currentPage
		};
		$.ajax({
			url : this.loadCommentsApi,
			data : JSON.stringify(commentPage),
			type : 'post',
			cache : false,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				me.addCommentToPage(data);
			},
			error : function() {
				alert("暂时无法链接服务器，请联系管理员");
			}
		});

	};

	Welfare.prototype.confirmComment = function(reply, content,isroot) {
		var auth = app.auth;
		if (!auth.isAuthced()) {
			auth.showLogin();
			return;
		}
		if (content.length < 10) {
			layer.msg("评论内容不能少于10个字哦!");
		}
		var comment = {
			content : content,
			replyId : reply.replyId
		};

		var me = this;
		$.ajax({
			url : this.confirmCommentApi,
			data : JSON.stringify(comment),
			type : 'post',
			cache : false,
			dataType : 'json',
			contentType : 'application/json',
			success : function(result) {
				var code = result.code;
				if (code == 0) {
					var data =[ {
						id:result.data.id,
					    userId:me.userId,
						nickname : me.nickname,
						avatarUrl : me.avatarUrl,
						content : content,
						
					}];
					$('.respond').remove();
					if(typeof isroot=='undefined' || false==isroot){
						data[0].reply={
							nickname : reply.nickname,
							userId : reply.userId
						};
					 $('#div-comment-'+reply.rootId+'>.msgarticle').append($(Welfare.createCommentElements(data,reply.rootId)));
					}else{
				      
					  $("#comment_list").prepend($(Welfare.createCommentElements(data,reply.rootId)));
					}
					
				}
				
				layer.msg(result.msg);
			},
			error : function() {
				alert("暂时无法链接服务器，请联系管理员");
			}
		});
	};
	app.Welfare = Welfare;

})(window.jQuery, window.layer, window.app)
