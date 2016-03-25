  
(function(jQuery,app){
	 app.auth= {
			 
	        isAuthced: function () {
	            return (app.loginToken.length > 0);
	        },
	        showLogin : function () {
	            var that = this;
	            $('#loginalert').modal();

	            $('#alt_login').unbind().click(function () {
	                that.doPostLogin();
	            });
	        },
	        doPostLogin: function () {
	            var un = $('#alt_un').val();
	            var pw = $('#alt_pw').val();
	            jQuery.post(app.base + '/api/login/confirm', {'username': un, 'password': pw}, function (ret) {
	                if (ret && ret.code == 0) {
	                    //$('#loginalert').modal('hide');
	                    //window.app.login = ret.data.id;
	                    window.location.reload();
	                } else {
	                    $('#login_warning').text(ret.message).show();
	                }
	            });
	        }
	    };
})(window.jQuery,window.app);

