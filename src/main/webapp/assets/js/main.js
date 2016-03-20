(function($, app) {

	$(function() {
		// Follow
		var auth=app.auth;
		$('a[rel=follow]').click(function() {
			var that = $(this);
			var id = that.attr('data-id');
			if (!auth.isAuthced()) {
				auth.showLogin();
				return false;
			}

			if (parseInt(id) > 0) {
				jQuery.getJSON(app.base + '/api/fans/follow', {
					'id' : id
				}, function(ret) {
					if (ret.code == 0) {
						that.text("已关注");
					} else {
						layer.msg(ret.message, {
							icon : 2
						});
					}
				});
			}
		});

		$('a[rel=follow]').each(function() {
			var that = $(this);
			var id = that.attr('data-id');

			if (parseInt(id) > 0) {
				jQuery.getJSON(app.base + '/api/fans/checkFollow', {
					'id' : id
				}, function(ret) {
					if (ret.code == 0) {
						that.text("已关注");
					}
				});
			}
		});
	})

})(window.jQuery, window.app)
