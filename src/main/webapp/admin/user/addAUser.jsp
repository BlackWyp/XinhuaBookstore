<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="tools.WebProperties" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>后台-增加普通用户</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui.admin/skin/default/skin.css"
          id="skin"/>
    <link rel="stylesheet" type="text/css" href="/XinhuaBookstore/admin/static/h-ui.admin/css/style.css"/>

    <!--/meta 作为公共模版分离出去-->
    <script type="text/javascript" src="/XinhuaBookstore/admin/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/XinhuaBookstore/admin/lib/vue.min.js"></script>
</head>
<body>
    <article class="page-container">
        <form id="MyForm" name="MyForm" class="form form-horizontal"  method="post">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>用户类型：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select name="role">
                        <option value="normal">普通用户</option>
                        <option value="manager">管理员</option>
                    </select>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>用户名：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" placeholder="" id="userName" name="userName" >
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>密码：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="password" class="input-text" placeholder="" id="password" name="password" >
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <input id="submitForm" type="button" name="submit" class="btn btn-primary radius" value="提交">
                </div>
            </div>
        </form>
    </article>
</body>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/XinhuaBookstore/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/XinhuaBookstore/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/XinhuaBookstore/admin/static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        function valName(){
            var userName=$("#userName").val();
            if(userName==null&&userName==""){
                alert("用户名不能为空");
                return false;
            } else if(userName.length<6){
                alert("用户名太短");
                return false;
            }
            return true;
        }

        function valPassword(){
            var password=$("#password").val();
            if(password==null&&password==""){
                alert("密码不能为空");
                return false;
            } else if(password.length<6){
                alert("密码太短");
                return false;
            }
            return true;
        }

        $("#submitForm").click(function () {
            var bCanSubmit=false;
            bCanSubmit=valName()&&valPassword();
            if(bCanSubmit){
                $.ajax({
                    url:"/XinhuaBookstore/servlet/UserServlet?type=addAUser",
                    type:"post",
                    data: $("#MyForm").serialize(),
                    dataType:"json",
                    cache:false,
                    error:function(textStatus, errorThrown){
                        alert("系统ajax交互错误: " + textStatus);
                    },
                    success:function(data,textStatus){
                        if(data.result==1){
                            window.location.href = '/XinhuaBookstore/servlet/UserServlet?type=getAllUsers';
                        }
                        else if(data.result==0){
                            alert("数据库操作失败");
                        }
                        else if(data.result==-1){
                            alert("该用户名已存在");
                        }
                    }
                });
            }
        });
    });
</script>
</html>
