package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		System.out.println(type);
		/* 前台 */
		if ("getAllBooks".equals(type)) {
			System.out.println("getAllBooks");
			List<Book> books = bookService.getAllBooks();
			request.setAttribute("books", books);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		} else if ("getBooksByName".equals(type)) {
			System.out.println("getBooksByName");
			String name = request.getParameter("name");
			List<Book> books = bookService.getBooksByName(name);
			request.setAttribute("books", books);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		} else if ("getBookById".equals(type)) {
			System.out.println("getBookById");
			String id = request.getParameter("id");
			List<Book> books = bookService.getBookById(Integer.parseInt(id));
			request.setAttribute("books", books);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		} else if ("getAllBooksForManager".equals(type)) {
			System.out.println("getAllBooksForManager");
			List<Book> books = bookService.getAllBooks();
			request.setAttribute("books", books);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		} else if ("getBooksByNameForManager".equals(type)) {
			System.out.println("getBooksByNameForManager");
			String name = request.getParameter("name");
			List<Book> books = bookService.getBooksByName(name);
			request.setAttribute("books", books);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		} else if ("getBookByIdForManager".equals(type)) {
			System.out.println("getAllBooks");
			String id = request.getParameter("id");
			List<Book> books = bookService.getBookById(Integer.parseInt(id));
			request.setAttribute("books", books);
			getServletContext().getRequestDispatcher("xxx.jsp").forward(request, response);
		} else if ("updateBook".equals(type)) {
			System.out.println("updateBook");
			Integer bookId = Integer.parseInt(request.getParameter("id"));
			String categoryName = request.getParameter("categoryName");
			String bookName = request.getParameter("bookName");
			String imgUrl = request.getParameter("imgUrl");
			String caption = request.getParameter("caption");
			String introduction = request.getParameter("introduction");
			double price = Double.parseDouble(request.getParameter("price"));
			bookService.updateBook(bookId, categoryName, bookName, imgUrl, caption, introduction, price);
		} else if ("deleteById".equals(type)) {
			System.out.println("deleteById");
			String id = request.getParameter("id");
			bookService.deleteBookById(Integer.parseInt(id));
			System.out.println("addBook");
			String categoryName = request.getParameter("categoryName");
			String bookName = request.getParameter("bookName");
			String imgUrl = request.getParameter("imgUrl");
			String caption = request.getParameter("caption");
			String introduction = request.getParameter("introduction");
			double price = Double.parseDouble(request.getParameter("price"));
			bookService.addBook(categoryName, bookName, imgUrl, caption, introduction, price);
		}
	}

}
