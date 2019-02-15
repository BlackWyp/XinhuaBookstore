<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>登录</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>

    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>

    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="col-6" style="margin-left:auto; margin-right:auto;">
        <form id="loginForm" name="loginForm" method="post">
            <div class="form-group">
                <label for="userName">
                    用户名：
                </label>
                <input class="form-control" type="text" name="userName" id="userName" placeholder="用户名至少需要8个字符,且要字母开头"  />
            </div>
            <div class="form-group">
                <label for="password">
                    密码：
                </label>
                <input class="form-control" type="password" name="password" id="password"  placeholder="密码只能输入6-20个字母、数字、下划线" />
            </div>
            <div class="form-group">
                <label for="userName">
                    验证码：
                </label>
                <input class="form-control" type="text" name="checkCode" id="checkCode" placeholder="输入如图所示的验证码"  />
                <img  id="checkImg" src="/XinhuaBookstore/servlet/ImageCheckCodeServlet?" class="hand" />
            </div>
            <div class="form-group">
                <button id="submitForm" type="button" class="btn btn-primary btn-lg">
                    登录
                </button>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    function valName(){
        //var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
        var str1=document.getElementById("userName").value;//获取文本框的内容

        if(document.getElementById("userName").value==null || document.getElementById("userName").value==""){
            alert("用户名不能为空");
            return false;
        }else if(str1.length>=6){
            return true;
        }else{
            alert("用户名长度要大于6");
            return false;
        }
    }

    function valPassword(){
        var str = document.getElementById("password").value;
        var pattern=/^(\w){6,20}$/;

        if(document.getElementById("password").value==null || document.getElementById("password").value==""){
            alert("密码不能为空");
            return false;
        }else if(str.match(pattern)==null){
            alert("密码只能输入6-20个字母、数字、下划线");
            return false;
        }else{
            return true;
        }
    }

    function valCheckCode(){
        if(document.getElementById("checkCode").value==null || document.getElementById("checkCode").value==""){
            alert("验证码不能为空");
            return false;
        }
        else{
            return true;
        }
    }

    function getNewCheckCode(){
        document.getElementById("checkImg").src="/XinhuaBookstore/servlet/ImageCheckCodeServlet?"+Math.random();
    }
    $(document).ready(function(){
        //设置点击事件的处理函数
        $("#checkImg").click(function(){
            getNewCheckCode();
        });
        $("#submitForm").click(function(){
            var canSubmit=false;
            canSubmit=valName();
            canSubmit=canSubmit&valPassword();
            canSubmit=canSubmit&valCheckCode();
            if(canSubmit){
                $.ajax({
                    url:"/XinhuaBookstore/servlet/UserServlet?type=login",
                    type:"post",
                    data:$("#loginForm").serialize(),
                    dataType:"json",
                    cache:false,
                    error:function(textStatus, errorThrown){
                        alert("系统ajax交互错误: " + textStatus);
                    },
                    success:function(data,textStatus){
                        if(data.result==1){
                            window.location.href = '/XinhuaBookstore/index.jsp';
                        }
                        else{
                            getNewCheckCode();
                            if(data.result==0){
                                alert("用户不存在");
                            }
                            else if(data.result==-1){
                                alert("密码错误");
                            }
                            else if(data.result==-2){
                                alert("验证码错误");
                            }
                            else if(data.result==-3){
                                alert("验证码过期，请刷新验证码");
                            }
                            else if(data.result==-100){
                                alert("系统出错");
                            }
                        }
                    }
                });
            }
            else{
                getNewCheckCode();
            }
        });
    });
</script>
</html>
