<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/CommonHeader.jsp" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>Rosetta Stone</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="<%=basePath%>assets/metro/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>assets/metro/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>assets/metro/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>assets/metro/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>assets/metro/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>assets/metro/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>assets/metro/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="<%=basePath%>assets/metro/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="<%=basePath%>assets/metro/css/error.css" rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="<%=basePath%>assets/metro/image/favicon.ico" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-404-3">
	<div class="page-inner">
		<img src="<%=basePath%>assets/metro/image/earth.jpg" alt="">
	</div>
	<div class="container error-404">
		<h1>Oops</h1>
		<h2>Houston, we have a problem.</h2>
		<p>
			Actually, <font>the page you are looking for does not exist.</font>
			<br/>
			Please retry or find administrator.
		</p>
		<p>
			<a href="https://login-dev.dev.rosettastone.com/#/launchpad">Return home</a>
			<br>
		</p>
	</div>
	<script src="<%=basePath%>assets/jquery/jquery-2.1.1.min.js" type='text/javascript'></script>
	<script type="text/javascript">
		var msg = "${it.msg}";
		console.log(msg)
		$(document).ready(function(){
			if (msg != "")
				$("font").text(msg);
		});
	</script>
</body>
<!-- END BODY -->

</html>