package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import service.BookService;

public class BookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		BookService bookService = new BookService();
		List<Book> books = null;
		String name = null;
		String id = null;
		Integer bookId = null;
		String categoryName = null;
		String bookName = null;
		String imgUrl = null;
		String caption = null;
		String introduction = null;
		double price = 0;
		if (type != null) {
			switch (type) {
			/* 前台 */
			case "getAllBooks":
				books = bookService.getAllBooks();
				request.setAttribute("books", books);
				getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
				break;
			case "getBooksByName":
				name = request.getParameter("name");
				books = bookService.getBooksByName(name);
				request.setAttribute("books", books);
				getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
				break;
			case "getBookById":
				id = request.getParameter("id");
				books = bookService.getBookById(Integer.parseInt(id));
				request.setAttribute("books", books);
				getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
				break;

			/* 后台 */
			case "getAllBooksForManager":
				books = bookService.getAllBooks();
				request.setAttribute("books", books);
				getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
				break;
			case "getBooksByNameForManager":
				name = request.getParameter("name");
				books = bookService.getBooksByName(name);
				request.setAttribute("books", books);
				getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
				break;
			case "getBookByIdForManager":
				id = request.getParameter("id");
				books = bookService.getBookById(Integer.parseInt(id));
				request.setAttribute("books", books);
				getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
				break;
			case "updateBook":
				bookId = Integer.parseInt(request.getParameter("id"));
				categoryName = request.getParameter("categoryName");
				bookName = request.getParameter("bookName");
				imgUrl = request.getParameter("imgUrl");
				caption = request.getParameter("caption");
				introduction = request.getParameter("introduction");
				price = Double.parseDouble(request.getParameter("price"));
				bookService.updateBook(bookId, categoryName, bookName, imgUrl, caption, introduction, price);
				break;
			case "deleteById":
				id = request.getParameter("id");
				bookService.deleteBookById(Integer.parseInt(id));
				break;
			case "addBook":
				categoryName = request.getParameter("categoryName");
				bookName = request.getParameter("bookName");
				imgUrl = request.getParameter("imgUrl");
				caption = request.getParameter("caption");
				introduction = request.getParameter("introduction");
				price = Double.parseDouble(request.getParameter("price"));
				bookService.addBook(categoryName, bookName, imgUrl, caption, introduction, price);
				break;
			default:
				break;
			}
		}
	}

}
