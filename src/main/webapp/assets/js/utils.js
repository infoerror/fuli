var app = {};
(function(app) {

	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	app.base = localObj.protocol + "//" + localObj.host + "/" + contextPath;

})(app);
