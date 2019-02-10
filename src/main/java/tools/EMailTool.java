package tools;


import org.apache.commons.mail.SimpleEmail;

public class EMailTool {
	static public String emailHost="smtp.163.com";
	static public String emailUserEmail="blackwu43@163.com";
	static public String emailUserName="blackwu43@163.com";
	static public String emailPassword="DGUTwyp0403";
	static public String domain="http://localhost:8080/xinhuaBookstore";//本网站域名
	
	static public SimpleEmail simpleEmail;
	
	static public Integer sendReturnPassword(String to, Integer rand){
		Integer result = -1;
		SimpleEmail simpleEmail=new SimpleEmail();
		//设置字符编码方式
		simpleEmail.setCharset("UTF-8");
		try {
			//设置SMTP邮件服务器，比如:smtp.163.com
			simpleEmail.setHostName(emailHost);
			//设置登入认证服务器的 用户名 和 授权密码 （发件人））
			simpleEmail.setAuthentication(emailUserName,emailPassword);
			//设置发送源邮箱
			simpleEmail.setFrom(emailUserEmail);
			//设置收件人可以是多个：simpleEmail.addTo("xxx@qq.com", "sky-xuyi");
			simpleEmail.addTo(to);				
			//设置主题
			simpleEmail.setSubject("找回密码");			
			//设置邮件内容
			String url=domain+"/newPassword.jsp?findPasswordRand="+rand.toString();
			simpleEmail.setMsg("找回密码--请复制以下网址，并在浏览器地址栏粘贴并访问："+url);
			//发送邮件
			simpleEmail.send();
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	//返回1代表发送成功，返回-1代表发送失败
	static public Integer emailForRegister(String to, Integer rand){
		Integer result = -1;
		SimpleEmail simpleEmail=new SimpleEmail();
		//设置字符编码方式
		simpleEmail.setCharset("UTF-8");
		try {
			//设置SMTP邮件服务器，比如:smtp.163.com
			simpleEmail.setHostName(emailHost);
			//设置登入认证服务器的 用户名 和 授权密码 （发件人））
			simpleEmail.setAuthentication(emailUserName,emailPassword);
			//设置发送源邮箱
			simpleEmail.setFrom(emailUserEmail);
			//设置收件人可以是多个：simpleEmail.addTo("xxx@qq.com", "sky-xuyi");
			simpleEmail.addTo(to);				
			//设置主题
			simpleEmail.setSubject("注册");			
			//设置邮件内容
			String url=domain+"/servlet/UserServlet?type=emailRegister&registerRand="+rand.toString();
			simpleEmail.setMsg("注册--请复制以下网址，并在浏览器地址栏粘贴并访问："+url);
			//发送邮件
			simpleEmail.send();
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
