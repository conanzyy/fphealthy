<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"  />
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui.js"></script>
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="css/style.css" rel="stylesheet" type="text/css"/>
		<title>GreatSo</title>
<style type=text/css>
div{font-size:12px;font-family:arial}.baidu{font-size:14px;line-height:24px;font-family:arial} a,a:link{color:#0000cc;}
.baidu span{color:#6f6f6f;font-size:12px} a.more{color:#008000;}a.blk{color:#000;font-weight:bold;}
</style>
</head>
<body>
<script type=text/javascript src="js/mfsapi.js"></script>
<script>
	$(function(){
		$("div.hidden img").hide();
	});
</script>
<div class="hidden">
<script language="JavaScript" type="text/JavaScript" src="http://news.baidu.com/ns?word=title%3A%E5%81%A5%E5%BA%B7&tn=newsfcu&from=news&cl=2&rn=10&ct=0"></script>
<script language="JavaScript" type="text/JavaScript" src="http://news.baidu.com/n?cmd=1&class=mil&pn=1&tn=newsbrofcu"></script>
</div>
</body>
</html>