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

<title>后台-图书管理</title>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="./admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="./admin/lib/vue.min.js"></script>
</head>

<body>
	<div id="test">{{message}}</div>
	<button onclick="vm.message = $('#myButton').text()"  id="myButton">来点我</button>
</body>
<script type="text/javascript">
	var vm = new Vue({
		el : '#test',
		data : {
			message : 'fuck you'
		}
	});
</script>
</html>

