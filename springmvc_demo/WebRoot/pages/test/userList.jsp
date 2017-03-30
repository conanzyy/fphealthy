<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>用户列表</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
	font-family:Microsoft YaHei;
	font-size: 14px;
}
table {
	
	font-family:Microsoft YaHei;
	font-size: 16px;
}
-->
</style>
<script type="text/javascript">
	function newUser(){
		
		var url = "${path}/system/check_uid.action";
		$.ajax({
			url : url,
			data : {"uid":uid},
			type : 'post',
			dataType : "json",
			cache : false,		
			error : function(textStatus, errorThrown) {
				alert("系统繁忙，请您稍后重新操作。 ");
			},
			success : function(data) {
				if(data.jsonData=="suc"){
					
			    }
			    
			}
		});
	}
	
  
</script>
</head>

<body>
<div align="center">
<h1>Hello Friday's members</h1>
<br/>
<table width="600px" border="0">
<tr align="left">
<td><a href="add">添加</a></td>
</tr>
</table>
<table width="600px" border="0">
<tr align="left">
<th>id</th>
<th>姓名</th>
<th>密码</th>
<th>邮箱</th>
<th>操作</th>
</tr>
<c:forEach items="${users}" var="list">
<tr align="left">
<td>${list.value.userId}</td>
<td>${list.value.userName}</td>
<td>${list.value.password}</td>
<td>${list.value.email}</td>
<td><a href="${list.value.userId}/update">修改</a>&nbsp;&nbsp;<a href="${list.value.userId}/delete">删除</a></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>
