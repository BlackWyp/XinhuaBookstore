<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'editCategory.jsp' starting page</title>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="./admin/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./admin/lib/vue.min.js"></script>
<script type="text/javascript" src="./admin/lib/jquery.dataTables.min.js"></script>
<link type="text/css" rel="styleSheet" href="./admin/css/jquery.dataTables.min.css" />
<style type="text/css">
td {
	text-align: center;
}
</style>
</head>

<body>
	<div style="margin-left:10%;width:80%;">
		<form name="myForm" id="name" class="form form-horizontal" action="/XinhuaBookstore/servlet/CategoryServlet?type=updateCategory" method="post">
			<input name="oldName" type="hidden" value="${param.name}">
			<label class="form-label col-xs-4 col-sm-2">原类别名称：</label>${param.name}<br>
			<label class="form-label col-xs-4 col-sm-2">新类别名称：</label><input name="newName" type="text"><br>
			<input type="submit" name="submit" class="btn btn-primary radius" value="提交">
		</form>
	</div>
  </body>
</html>
