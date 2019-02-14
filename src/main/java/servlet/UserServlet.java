package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

public class UserServlet extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
        UserService userService=new UserService();
		if("getAllUsers".equals(type)){
			request.setAttribute("users", userService.getAllUsers());
			RequestDispatcher dispatcher =request.getRequestDispatcher("/admin/user/showAllUsers.jsp");
			dispatcher .forward(request, response);
		}
		else if("deleteUserById".equals(type)){
            userService.deleteUserById(Integer.parseInt(request.getParameter("userId")));
            response.sendRedirect("/XinhuaBookstore/servlet/UserServlet?type=getAllUsers");
        }
		else if("getUserToUpdate".equals(type)){
            request.setAttribute("user", userService.getUserById(Integer.parseInt(request.getParameter("userId"))));
            RequestDispatcher dispatcher =request.getRequestDispatcher("/admin/user/updateUser.jsp");
            dispatcher .forward(request, response);
        }
		else if("updateUser".equals(type)){
            Integer result=userService.updateUserById(request);
			Tool.returnIntResult(response,result);
        }else if("addAUser".equals(type)){
			Integer result=userService.addAUser(request);
			Tool.returnIntResult(response,result);
		}
	}

}
