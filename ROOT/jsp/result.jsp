<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
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
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<title>GreatSo</title>
<style type=text/css>
*{ padding:0px; margin:0px; font-family:微软雅黑; font-size:14px; }
body{width:100%}
div{font-size:12px;font-family:arial}.baidu{font-size:14px;line-height:24px;font-family:arial} a,a:link{color:#0000cc;}
.baidu span{color:#6f6f6f;font-size:12px} a.more{color:#008000;}a.blk{color:#000;font-weight:bold;}
.sdiv{
	margin-top:30px;
	width:100%;
	text-align:center;
}
.sdiv input{
	width: 23%;
	height: 30px;
	margin: 0 8px;
}
.simg{
	margin:30 auto;
	text-align: center;
	display:block;
	width: 350px;
	height: 200px;
}
.simg img{
	margin:0 auto;
	vertical-align:middle;
}
.showdiv{
	margin-top: 15px;
	margin-left: 40px;
	font-size: 17px;
}
.showdiv div{
	margin-top: 15px;
	font-size: 16px;
	color: #949294;
	text-align: center;
}
.result{
	font-size: 1.4em;
}
</style>
</head>
<body>
<script type=text/javascript src="js/mfsapi.js"></script>
<script>
	$(function(){
		var width=$(window).width();
		var imgwidth=$(".simg").first().width();
		$(".simg").first().css("margin-left",(width-imgwidth)/2+"px");
		$("#s_img").show();
	});
	function submitmethod(){
		if($.trim($("#A").val())==""){
			$("#A").focus();
			return false;
		}
		if($.trim($("#B").val())==""){
			$("#B").focus();
			return false;
		}
		if($.trim($("#A").val())==$.trim($("#B").val())){
			$("#B").focus();
			return false;
		}
		return true;
	}
</script>
	<div class="simg">
	<img src="img/front/shuye.jpg" width="350" height="200" style="display: none;" id="s_img"></img>
	</div>
	<form class="form-search" class="sform" action="getResult.do" method="post" onsubmit="return submitmethod();">
		<div class="sdiv">
			<input id="A" class="input-medium search-query" name="Astr" required="required" type="text" autofocus="autofocus" placeholder="No leaves are exactly the same" />
			<input id="B" class="input-medium search-query" name="Bstr" required="required" type="text" placeholder="but we can find the relation"/>
			<button class="btn"  type="submit">GreatSo</button>
		</div>
	</form>
	<div class="showdiv">
		<c:if test="${empty showlist}">
			<div class="sorry">Sorry,We can't find the relation!</div>
		</c:if>
		<c:forEach items="${showlist}" var="alllist">
			<c:forEach items="${alllist}" var="list" varStatus="xh">
				<c:choose>  
					<c:when test="${xh.last}">
						<a href="http://www.baidu.com/s?wd=${list}" target="_blank"><span class="result">${list}</span></a> 
					</c:when>  
					<c:otherwise>
						<a href="http://www.baidu.com/s?wd=${list}" target="_blank"><span class="result">${list}</span></a>----
					 </c:otherwise>  
				</c:choose>  
			</c:forEach>
			<br/>
		</c:forEach>
	</div>
</body>
</html>