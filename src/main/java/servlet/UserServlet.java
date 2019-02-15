package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import tools.Tool;
import bean.User;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		HttpSession session=request.getSession();
		UserService userService = new UserService();
		if ("getAllUsers".equals(type)) {
			request.setAttribute("users", userService.getAllUsers());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/showAllUsers.jsp");
			dispatcher.forward(request, response);
		} else if ("deleteUserById".equals(type)) {
			userService.deleteUserById(Integer.parseInt(request.getParameter("userId")));
			response.sendRedirect("/XinhuaBookstore/servlet/UserServlet?type=getAllUsers");
		} else if ("getUserToUpdate".equals(type)) {
			request.setAttribute("user", userService.getUserById(Integer.parseInt(request.getParameter("userId"))));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/updateUser.jsp");
			dispatcher.forward(request, response);
		} else if ("updateUser".equals(type)) {
			Integer result = userService.updateUserById(request);
			Tool.returnIntResult(response, result);
		} else if ("addAUser".equals(type)) {
			Integer result = userService.addAUser(request);
			Tool.returnIntResult(response, result);
		}else if("login".equals(type)){
			//返回-3表示验证码过期,-2表示验证码错误，0表示不存在该用户/电子邮箱/手机号,1表示成功，-1表示密码错误,-100表示出错
			Integer result=-3;
			User user=new User();
			String sessionCheckCode= (String) session.getAttribute("checkCode");
			String checkCode=request.getParameter("checkCode");
			if(sessionCheckCode==null){
				result=-3;	//验证码过期
			}else{
				if(sessionCheckCode.equals(checkCode)){
					user.setUserName(request.getParameter("userName"));
					user.setPassword(request.getParameter("password"));
					result=userService.login(user,request.getSession());
				}else{
					result=-2;	//验证码错误
				}
			}
			Tool.returnIntResult(response,result);
		}
	}

}
