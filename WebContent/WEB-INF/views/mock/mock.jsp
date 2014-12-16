<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/CommonHeader.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en-US">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=2.0; user-scalable=yes;">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="Rosetta Stone">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,FF=3,chrome=1" />
<title>Mock up page</title>

<link href='<%=basePath%>assets/bootstrap/css/bootstrap.min.css' media='screen' rel='stylesheet' type='text/css' >
<link href='<%=basePath%>assets/bootstrap/css/bootstrap-theme.min.css' media='screen' rel='stylesheet' type='text/css' >
<link href='https://raw.githubusercontent.com/leo108/jsonFormater/master/jsonFormater.css' type='text/css'>

<style>
	div.json {
		width:40%;
		float:left;
		margin-right:5%
	}
</style>
</head>
<body>
	<button class="btn btn-lg btn-default" type="button">Test</button>
	<p>The TMM url is: <br/><font id="tmmurl"></font></p>
	<p></p>
		<div class="json">
			<p>User Information</p>
			<div id="userinfo"></div>
		</div>
		<div class="json">
			<p>Organization</p>
			<div id="orginfo"></div>
		</div>

<script src="<%=basePath%>assets/jquery/jquery-2.1.1.min.js" type='text/javascript'></script>
<script src="<%=basePath%>assets/angular/angular.min.js" type='text/javascript'></script>
<script src="<%=basePath%>assets/bootstrap/js/bootstrap.min.js" type='text/javascript'></script>
<script src="https://raw.githubusercontent.com/leo108/jsonFormater/master/jsonFormater.js" type='text/javascript'></script>
<script>
	var status = '${it.status}',
		userinfo = String2JSon('${it.user}'),
		orginfo = String2JSon('${it.org}'),
		tmmurl = '${it.tmmurl}';
 	console.log("status:", status);
 	console.log("user:", userinfo);
 	console.log("org:",orginfo);
 	console.log("tmmurl:", tmmurl);
	
	$(document).ready(function(){
		$("#tmmurl").html(tmmurl);
		
	    var options = {
	        dom : '#userinfo' //对应容器的css选择器
	    };
	    var jf = new JsonFormater(options); //创建对象
	    jf.doFormat(userinfo); //格式化json
	    
	    options.dom = "#orginfo";
	    jf = new JsonFormater(options);
	    jf.doFormat(orginfo);
	    
	    var flag = status==1 && userinfo.email && orginfo.name?true :false;
	    var msg = "error because of " + 
	    	(!userinfo.email?"user is null.":!orginfo.name?"organization is null.": "unknown reason.");
	    $("button").html(flag?"Go to TMM portal":"Go to error page")
	    	.click(function(v){
    			location.href = flag?tmmurl:"error?msg="+msg;
    	});
	});
	
	function String2JSon(str){
		if (str!="")
			return $.parseJSON(str);
		return {}
	}
</script>
</body>
</html>