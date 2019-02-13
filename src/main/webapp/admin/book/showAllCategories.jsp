<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>后台-类别管理</title>


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
		<table id="categoryTable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>类别</th>
					<th>删除</th>
					<th>修改</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.categories}" var="category">
					<tr>
						<td>${category}</td>
						<td><a href="/XinhuaBookstore/servlet/CategoryServlet?type=deleteCategory&name=${category}">删除</a></td>
						<td><a href="/XinhuaBookstore/admin/book/editCategory.jsp?name=${category}">修改</a></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<form name="myForm" id="name" class="form form-horizontal" action="/XinhuaBookstore/servlet/CategoryServlet?type=addCategory" method="post">
			<label class="form-label col-xs-4 col-sm-2">添加类别：</label>
			<input name="name" type="text">&nbsp;&nbsp;
			<input type="submit" name="submit" class="btn btn-primary radius" value="提交">
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#categoryTable').dataTable({
			language : {
				"sProcessing" : "处理中...",
				"sLengthMenu" : "显示 _MENU_ 项结果",
				"sZeroRecords" : "没有匹配结果",
				"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索:",
				"sUrl" : "",
				"sEmptyTable" : "表中数据为空",
				"sLoadingRecords" : "载入中...",
				"sInfoThousands" : ",",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上页",
					"sNext" : "下页",
					"sLast" : "末页"
				},
				"oAria" : {
					"sSortAscending" : ": 以升序排列此列",
					"sSortDescending" : ": 以降序排列此列"
				}
			}
		});
	});
</script>
</html>

