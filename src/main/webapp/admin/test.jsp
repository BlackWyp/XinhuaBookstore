<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <a href="/XinhuaBookstore/servlet/BookServlet?type=getAllBooksForManager">所有图书</a>
    <img alt="000" src="\XinhuaBookstore\upload\images\bookPicture\1549961578455.jpg"/>
  <p><img src="\XinhuaBookstore\upload\images\bookIntroduction\20190212\1549977883536031270.jpg" title="1549977883536031270.jpg" alt="__anchovy_girls_und_panzer_drawn_by_fuu_fuu__f9ef319d010d86e87cedd5a9c08f6fda.jpg"/></p>
  <p><img src="/XinhuaBookstore/upload/images/bookIntroduction/20190212/1549977883536031270.jpg" title="1549977883536031270.jpg" alt="__anchovy_girls_und_panzer_drawn_by_fuu_fuu__f9ef319d010d86e87cedd5a9c08f6fda.jpg"/></p>
  <p><img src="/XinhuaBookStore/upload/images/bookIntroduction/20190212/1549977883536031270.jpg" title="1549977883536031270.jpg" alt="__anchovy_girls_und_panzer_drawn_by_fuu_fuu__f9ef319d010d86e87cedd5a9c08f6fda.jpg"/></p>
  </body>
</html>
