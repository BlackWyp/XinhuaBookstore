<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<base href="<%=basePath%>">

	<title>后台-添加书本</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./admin/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="./admin/lib/vue.min.js"></script>
</head>

<body>
	<form action="/XinhuaBookstore/servlet/BookServlet?type=addBook" method="post">
		<input type="text" name="categoryName">
		<input type="text" name="bookName">
		<input type="text" name="imgUrl">
		<input type="text" name="caption">
		<input type="text" name="introduction">
		<input type="text" name="price">
		<input type="submit">
	</form>
</body>

</html>