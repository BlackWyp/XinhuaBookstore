<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tools.WebProperties"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->

<link rel="stylesheet" type="text/css" href="./admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="./admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="./admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="./admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="./admin/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
<!--/meta 作为公共模版分离出去-->
<script type="text/javascript" src="./admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="./admin/lib/vue.min.js"></script>
<script src='<%=WebProperties.config.getString("ueditConfigJs")%>' type="text/javascript"></script>
<script src='<%=WebProperties.config.getString("ueditJs")%>' type="text/javascript"></script>
<script src='<%=WebProperties.config.getString("ueditLang")%>' type="text/javascript"></script>
</head>

<body>
	<article class="page-container">
	<form id="MyForm" name="MyForm" class="form form-horizontal" action="/XinhuaBookstore/servlet/BookServlet?type=updateBook" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
		<input type="hidden" id="bookId" name="bookId" value="${requestScope.book.bookId}">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>类别：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select name="categoryName" form="MyForm">
					<c:forEach items="${requestScope.categories}" var="category">
						<option value="${category}">${category}</option>
					</c:forEach>
				</select>
				
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>书名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="bookName" name="bookName" value="${requestScope.book.bookName}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图片：</label>&nbsp;&nbsp;<br> <img src="${requestScope.book.imgUrl}" style="width:200px;"/> <input type="file" name="picture">
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">简介：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" value="${requestScope.book.caption}" class="input-text" placeholder="" id="caption" name="caption">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">详细介绍：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<script id="content" type="text/plain" style="width:800px;height:500px;">
					${requestScope.book.introduction}
				</script>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="price" name="price" value="${requestScope.book.price}">
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<input type="submit" name="submit" class="btn btn-primary radius" value="提交">
			</div>
		</div>
	</form>
	</article>

</body>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="./admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="./admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="./admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="./admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="./admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="./admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="./admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="./admin/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript">
	function checkForm() {
		var price = $('#price').val();
		var re = /^\d+\.?\d{0,2}$/;
		if (!re.test(price)) {
			alert("价格请输入数字，小数点后保留2位");
			return false;
		}
		return true;
	}
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('content');
</script>
</html>