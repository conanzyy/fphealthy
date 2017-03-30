<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
</head>

<body>
<div align="center">
<h1>Hello Friday's members</h1>
<br/>
<sf:form method="post" modelAttribute="user">
<table width="600px" border="0">
	<tr>
		<td align="right" width="20%" nowrap="nowrap">用户id：</td>
		<td align="left" width="80%" nowrap="nowrap">
		<sf:input path="userId" size="60"/>
		</td>
	</tr>
	<tr>
		<td align="right" width="20%" nowrap="nowrap">用户名：</td>
		<td align="left" width="80%" nowrap="nowrap">
		<sf:input path="userName" size="60" />
		</td>
	</tr>
	<tr>
		<td align="right" width="20%" nowrap="nowrap">密   码：</td>
		<td align="left" width="80%" nowrap="nowrap">
		<sf:password path="password" size="60" />
		</td>
	</tr>
	<tr>
		<td align="right" width="20%" nowrap="nowrap">邮   箱：</td>
		<td align="left" width="80%" nowrap="nowrap">
		<sf:input path="email" size="60" />
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<input type="submit" value="保存"/>
		</td>
	</tr>
</table>
</sf:form>
</div>
</body>
</html>
