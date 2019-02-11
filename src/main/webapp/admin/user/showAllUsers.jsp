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

<title>后台-用户管理</title>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui.admin/css/style.css"/>
    
    
</head>

<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span
            class="c-gray en">&gt;</span> 查看用户 <a class="btn btn-success radius r"
                                                  style="line-height:1.6em;margin-top:3px"
                                                  href="javascript:location.replace(location.href);" title="刷新"><i
                class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form action="#" method="post">
        <div class="text-c">
            <input type="text" name="userName" id="userName" placeholder=" 用户名" style="width:250px" class="input-text">
            <button name="search" id="search" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>
                搜索
            </button>
        </div>
    </form>
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"> <a
                    class="btn btn-primary radius" data-title="添加用户" data-href="manager_user_add.php"
                    onclick="Hui_admin_tab(this)" href="javascript:;"><i
                        class="Hui-iconfont">&#xe600;</i> 添加用户</a></span> <span
                class="r">共有数据：<strong>1</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="120">ID</th>
                <th width="120">用户名</th>
                <th width="120">密码</th>
                <th width="120">邮箱</th>
                <th width="120">电话号码</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody>
                <tr class="text-c">
                    <td><input type="checkbox" value="" name=""></td>
                    <td>1</td>
                    <td>black0403</td>
                    <td>123456</td>
                    <td>331625548@qq.com</td>
                    <td>18207504461</td>
                    <td class="f-14 td-manage">
                        <a style="text-decoration:none" class="ml-5"
                           href="manager_user_update.php?user_id=<?php echo $user['user_id']; ?>" title="编辑"><i
                                    class="Hui-iconfont">&#xe6df;</i></a>
                        <a style="text-decoration:none" class="ml-5"
                           href="manager_user_delete.php?user_id=<?php echo $user['user_id']; ?>" title="删除"><i
                                    class="Hui-iconfont">&#xe6e2;</i></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/XinhuaBookstore/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/XinhuaBookstore/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/XinhuaBookstore/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/XinhuaBookstore/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/XinhuaBookstore/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/XinhuaBookstore/admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/XinhuaBookstore/admin/lib/laypage/1.2/laypage.js"></script>
<!-- Popper js -->
<script src="/XinhuaBookstore/js/popper.min.js"></script>
<!-- Plugins js -->
<script src="/XinhuaBookstore/js/plugins.js"></script>
<!-- Classy Nav js -->
<script src="/XinhuaBookstore/js/classy-nav.min.js"></script>
<!-- Active js -->
<script src="/XinhuaBookstore/js/active.js"></script>
<script type="text/javascript">

    /*资讯-添加*/
    function article_add(title, url, w, h) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*资讯-编辑*/
    function article_edit(title, url, id, w, h) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*资讯-删除*/
    function article_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function (data) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });
        });
    }
</script>
</body>
<script type="text/javascript">

</script>
</html>

