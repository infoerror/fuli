<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/web/common/common.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="/WEB-INF/web/common/resource.jspf"%>
<script
	src="${pageContext.request.contextPath }/assets/vendors/jcrop/jquery.jcrop.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/assets/js/avatar.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/assets/vendors/layer/layer.js" type="text/javascript"></script>
<title>修改用户信息</title>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->

</head>
<body>
	<jsp:include page="/WEB-INF/web/common/newnav.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			<%@ include file="../common/userNotice.jspf"%>
			<div class="row container-fluid projects">
				<div class="col-md-12">
					<div class="row container-fluid">
						<div class="row">
							<%@ include file="../common/usernav.jspf"%>
							<div class="col-md-10">
								<div class="panel panel-default stacked">
									<div class="panel-heading">
										<ul class="nav nav-pills account-tab">
											<li><a href="modifyBasicInfo">基本信息</a></li>
											<li class="active"><a href="avatar">修改头像</a></li>
											<li><a href="password">修改密码</a></li>
										</ul>
									</div>
									<div class="panel-body">
										<div id="message"></div>
										<form method="post" action="${pageContext.request.contextPath }/api/user/modifyAvatar" class="form-horizontal">
											<input type="hidden" value="" name="x" id="x"> <input
												type="hidden" value="" name="y" id="y"> <input
												type="hidden" value="" name="width" id="width"> <input
												type="hidden" value="" name="height" id="height"> <input
												type="hidden" value="" name="path" id="path">

											<div class="upload-btn">
												<label> <span>点击选择一张图片</span> <input type="file"
													title="点击添加图片" accept="image/*" name="file" id="upload_btn">
												</label>
											</div>
											<div class="update_ava">
												<img alt="[Jcrop Example]" id="target"
													src="${pageContext.request.contextPath }${imageUri }">
											</div>

											<div class="form-group">
												<div class="text-center">
													<button class="btn btn-primary" id="confirmModify">提交</button>
												</div>
											</div>
										</form>
									</div>
									<!-- /panel-content -->
									
										<script type="text/javascript">
									$(function() {
										$("#confirmModify").click(function(event){
											 event.preventDefault();
											 var info={
													x:$('#x').val(),
													y:$('#y').val(),
													width:$('#width').val(),
													height:$('#height').val(),
													path:$('#path').val()
													 
											 };
											 $.ajax({
													url : "${pageContext.request.contextPath}/api/user/modifyAvatar", // 跳转到 action
													data : JSON.stringify(info) ,
													type : 'post',
													cache : false,
													dataType : 'json',
													contentType : 'application/json',
													success : function(result) {
														layer.msg(result.msg);
													},
													error : function() {
														alert("暂时无法链接服务器，请联系管理员")
													}
												});
											 
										})
									});
								</script>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>

	var jcrop_api;
	var jcrop_init = false;
	
	function getRandom() {
		var dim = jcrop_api.getBounds();
		return [
			Math.round(Math.random() * dim[0]),
			Math.round(Math.random() * dim[1]),
			Math.round(Math.random() * dim[0]),
			Math.round(Math.random() * dim[1])
		];
    };
	
	function showCoords(c) {
		$('#x').val(c.x);
		$('#y').val(c.y);
		$('#width').val(c.w);
		$('#height').val(c.h);
	};
  
	function initJcrop() {
		$('#target').Jcrop({
			boxWidth:300,
      		aspectRatio: 100 / 100,
			onChange:   showCoords,
      		onSelect:   showCoords,
			allowSelect: false
		},function(){
    		jcrop_api = this;
    		jcrop_api.animateTo([100,100,300,300]);
		});
	}
	
		var upload_url = '${pageContext.request.contextPath}/api/upload/avatar?scale=true&size=300';
		$(function(){
			$('#upload_btn').change(function() {
				$(this).upload(upload_url, function(data) {
					if (data.code >= 0) {
						var path =  '${pageContext.request.contextPath}'+data.data;
						$("#target").attr("src", path);
						$("#path").val(data.data);

						if (!jcrop_init) {
							initJcrop();
							jcrop_init = true;
						} else {
							jcrop_api.setImage(path, function() {
								this.animateTo(getRandom());
							});
						}
					}
				});
			});		
		});
		
	
	</script>
	<%@ include file="../common/footer.jspf"%>
</body>
</html>
