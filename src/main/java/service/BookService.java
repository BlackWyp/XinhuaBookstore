package service;

import java.util.List;

import bean.Book;
import dao.BookDao;

public class BookService {
	public List<Book> getAllBooks() {
		BookDao bookDao=new BookDao();
		List<Book> books=bookDao.getAllBooks();
		return books;
	}
	
	public List<Book> getBooksByName(String name) {
		BookDao bookDao=new BookDao();
		return bookDao.getBooksByName(name);
	}
	public Book getBookById(Integer id) {
		BookDao bookDao=new BookDao();
		return bookDao.getBookById(id);
	}
}
