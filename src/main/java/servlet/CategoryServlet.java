package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CategoryService;

public class CategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		CategoryService categoryService = new CategoryService();
		if ("getAllCategory".equals(type)) {
			List<String> categories = categoryService.getAllCategory();
			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		} else if ("deleteCategory".equals(type)) {
			String name = request.getParameter("name");
			categoryService.deleteCategory(name);
			response.sendRedirect("/XinhuaBookstore/servlet/CategoryServlet?type=getAllCategoryForManager");
		} else if ("updateCategory".equals(type)) {
			String newName = request.getParameter("newName");
			String oldName = request.getParameter("oldName");
			categoryService.updateCategory(oldName, newName);
			response.sendRedirect("/XinhuaBookstore/servlet/CategoryServlet?type=getAllCategoryForManager");
		} else if ("addCategory".equals(type)) {
			String name = request.getParameter("name");
			categoryService.addCategory(name);
			response.sendRedirect("/XinhuaBookstore/servlet/CategoryServlet?type=getAllCategoryForManager");
		} else if ("searchCategory".equals(type)) {
			String name = request.getParameter("name");
			List<String> categories = categoryService.searchCategory(name);
			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		}
		
		/*后台*/
		else if("getAllCategoryForManager".equals(type)) {
			List<String> categories = categoryService.getAllCategory();
			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("/admin/book/showAllCategories.jsp").forward(request, response);
		}
	}

}
